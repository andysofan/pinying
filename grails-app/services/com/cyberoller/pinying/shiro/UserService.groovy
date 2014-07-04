package com.cyberoller.pinying.shiro

import org.springframework.transaction.annotation.Transactional

import org.apache.shiro.crypto.hash.Sha512Hash
import org.apache.shiro.crypto.SecureRandomNumberGenerator

import org.apache.shiro.SecurityUtils
import java.text.SimpleDateFormat

class UserService {

	def messageSource

	def mailService


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

				sendEmail(params?.username, params?.email)
			}catch(e){
				status.setRollbackOnly()
				throw new RuntimeException("注册用户失败:${e.getMessage()}")
			}
    	}
    }

	def sendEmail(username, email){
		try{
			mailService.sendMail {
				async true
				to "${email}"
				subject "Hello ${username}"
				body "XXXXXXXXXXXXXXXX${username}XXXXXXXXXXXXXXXXXXXXXXXXX"
			}
		}catch(e){
			log.error "send email to ${email} failed."
		}
	}



	//忘记密码发送邮件
	def sendEmailForForgetPassword(username, email, password){
		/*
		StringBuffer buffer = new StringBuffer()
		buffer.append("尊敬的用户：\n").append("      您好！\n").append("        您在" + new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(System.currentTimeMillis()))
		buffer.append("提交找回密码请求\n")
		buffer.append("您的用户名为:${username}\n")
		buffer.append("密码为：${password}\n")
		buffer.append("        为了保证您帐号的安全性，登录后请及时修改您的账户密码。\n")
		buffer.append("        如果您误收到此电子邮件，则可能是其他用户在尝试帐号设置时的误操作，如果您并未发起该请\n")
		buffer.append("求，则无需再进行任何操作，并可以放心地忽略此电子邮件。\n")
		*/

		def html = """
		尊敬的用户：\n\t您好！\t您在${new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(System.currentTimeMillis())}提交找回密码请求\n
			您的用户名为:${username}\n
			密码为：${password}\n
			为了保证您帐号的安全性，登录后请及时修改您的账户密码。\n
			如果您误收到此电子邮件，则可能是其他用户在尝试帐号设置时的误操作，如果您并未发起该请\n
			求，则无需再进行任何操作，并可以放心地忽略此电子邮件。\n
		"""
		try{
			mailService.sendMail {
				async true
				to "${email}"
				subject "Hello ${username}"
				body "${html}"
			}
		}catch(e){
			log.error "send email to ${email} failed."
		}
	}


	//忘记密码
	def queryUserByEmailAndUsername( params ){
		def userInstance
		try{	
				userInstance = User.withCriteria(uniqueResult:true){
					and{
						eq("username",params.username)
						eq("email",params.email)
					}
				}
				if(!userInstance){
					throw new RuntimeException("用户不存在:${e.getMessage()}")
					return
				}
				def flagNumber = getRandomString(8)
				webResetPassword(userInstance,flagNumber,flagNumber)

				println(">>>>>>>>>>>>>>>>>>>>>>${params.username}:${params.email}:${flagNumber}<<<<<<<<<<<<<<<<<<<<<<<<<<")
				sendEmailForForgetPassword(userInstance.username, userInstance.email,flagNumber)
		}catch(e){
			throw new RuntimeException("用户不存在:${e.getMessage()}")
		}
		return userInstance
	}

	
	//length表示生成字符串的长度  
	def getRandomString(int length) { 
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
		Random random = new Random();     
		StringBuffer sb = new StringBuffer();     
		for (int i = 0; i < length; i++) {     
			int number = random.nextInt(base.length());     
			sb.append(base.charAt(number));     
		}     
		return sb.toString();     
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
	 * web重置密码页面 lxb
	 **/
	def resetPasswordPageService(){
		def userInstance
	try{
			userInstance = User.withCriteria(uniqueResult:true){
				eq("username", SecurityUtils.getSubject().getPrincipal())
			}
			println userInstance
			if(!userInstance){
				throw new RuntimeException("获取当前用户失败")
			}
		}catch(e){
			throw new RuntimeException("密码修改失败")
	}
	return userInstance
} 



	/**
	 * web重置密码
	 */
    def webResetPassword(User userInstance, String password, String passwordConfirm) {

		println "**********${password}:::${passwordConfirm}*********"
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
