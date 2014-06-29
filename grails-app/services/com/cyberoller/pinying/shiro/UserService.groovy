package com.cyberoller.pinying.shiro

import org.springframework.transaction.annotation.Transactional

import org.apache.shiro.crypto.hash.Sha512Hash
import org.apache.shiro.crypto.SecureRandomNumberGenerator

class UserService {

	def messageSource


    def register(Object params) {
    	User.withTransaction{ status ->
			try{
				log.info "params?.password = ${params?.password}"
				if(params?.password){
					if(params["password"][0] != params["password"][1]){
						throw new RuntimeException("两次输入的密码不一致！");
					}
				}else{
					throw new RuntimeException("密码不能为空！");
				}
				//合并密码
				params["password"] = params["password"][0]

				String salt = new SecureRandomNumberGenerator().nextBytes().toHex()	 //生成随机数
				
				def roleInstance = Role.withCriteria(uniqueResult:true){
					eq("name", "user".toUpperCase())
				}
				
				if(!roleInstance){
					throw new RuntimeException("获取角色错误");
				}

				def userInstance = new User(
				//用户名
				  username:params?.username
				//全称
				, fullname:params?.username
				//邮箱
				, email:params?.email
				//密码
				, passwordHash:new Sha512Hash(params?.password, salt).toHex()
				//盐
				, passwordSalt:salt
				//错误次数
				, errortimes:0
				//禁用
				, isBlocked:false
				//启用？
				, isActive:true
				).addToRoles(roleInstance)
				//检查
				if(!userInstance.validate()){
					def errorMsg = ""
					userInstance.errors.allErrors.each{ errorInstance ->
						errorMsg = "${errorMsg};${messageSource.getMessage(errorInstance, Locale.ENGLISH)}"
					}
					errorMsg = errorMsg.encodeAsHTML()
					
					throw new RuntimeException("检查失败：${errorMsg}")
				}
				userInstance.save(flush:true)
			}catch(e){
				status.setRollbackOnly()
				throw new RuntimeException("注册用户失败:${e.getMessage()}")
			}
    	}
    }
}
