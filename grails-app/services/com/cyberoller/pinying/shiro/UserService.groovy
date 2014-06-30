package com.cyberoller.pinying.shiro

import org.springframework.transaction.annotation.Transactional

import org.apache.shiro.crypto.hash.Sha512Hash
import org.apache.shiro.crypto.SecureRandomNumberGenerator

import org.apache.shiro.SecurityUtils

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

	/**
	 * 重置密码
	 */
    def resetPassword(User userInstance, String password, String passwordConfirm) {
		def result = false
		User.withTransaction{ status ->
			try{
				if(password != null && !password.trim().equals('') && password.size() >= 8 && password.equals(passwordConfirm)){
					//生成随机盐
					String salt = new SecureRandomNumberGenerator().nextBytes().toHex()	 //随机生成盐
					String passwordHash = new Sha512Hash(password, salt).toHex()//生成加密密码
					
					userInstance.passwordHash = passwordHash
					userInstance.passwordSalt = salt

					userInstance.save(flush:true)

					result = true
				}else{
					userInstance.errors.reject(
						'user.passwordHash.doesnotmatch',                                    // Error code within the grails-app/i18n/message.properties
						[] as Object[],                          // Groovy list cast to Object[]
						'[password does not match confirmation]')   // Default mapping string
					result = false
					throw new RuntimeException("user.passwordHash.doesnotmatch")
				}
			}catch(e){
				result = false
				status.setRollbackOnly()
				throw new RuntimeException("${e.getMessage()}")
			}
		}
		return result
    }

    /**
     *启用帐号
     **/
    def activeUser(Long id){
    	def result = false
    	try{
			def userInstance = User.get(id)
				userInstance.isActive = true
				userInstance.save(flush:true)
				
				result = true
    	}catch(e){
    		log.error "启用帐号${id}失败：${e.getMessage()}"
    	}
    	return result;
    }
    /**
     *停用帐号
     **/
    def inActiveUser(Long id){
    	def result = false
    	try{
			def userInstance = User.get(id)
				userInstance.isActive = false
				userInstance.save(flush:true)
				
				result = true
    	}catch(e){
    		log.error "停用帐号${id}失败：${e.getMessage()}"
    	}
    	return result;
    }
    /**
     *解钔帐号
     **/
    def unlockUser(Long id){
    	try{
			def userInstance = User.get(id)
				userInstance.isBlocked = false
				userInstance.save(flush:true)
    	}catch(e){
    		log.error "解锁帐号${id}失败：${e.getMessage()}"
    	}
    }
}
