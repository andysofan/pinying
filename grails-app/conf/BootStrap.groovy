import com.cyberoller.pinying.*
import com.cyberoller.pinying.shiro.*

import org.apache.shiro.crypto.hash.Sha512Hash
import org.apache.shiro.crypto.SecureRandomNumberGenerator

class BootStrap {

    def init = { servletContext ->
		//用户
		def adminUserInstance =  User.findByUsername("admin@py".trim().toUpperCase())
		if(!adminUserInstance){
			String salt = new SecureRandomNumberGenerator().nextBytes().toHex()	 //生成随机数
			adminUserInstance = new User(
				  username: "admin@py"
				, fullname : "admin@py"
				, email:"admin@py.com"
				, passwordHash: new Sha512Hash("12345678", salt).toHex()
				, passwordSalt:salt
				, errortimes : 0
				, isBlocked : false
				, isActive : true
			).save(flush:true)
		}
		//角色
		def adminRoleInstance = Role.findByName("manager".trim().toUpperCase())
		if(!adminRoleInstance){
			adminRoleInstance = new Role(
			  name:"manager"
			, description:"角色-开发者：拥有所有权限"
			).addToPermissions("*:*")
			.addToUsers(adminUserInstance)
			.save(flush:true);
		}
		//角色
		def userRoleInstance = Role.findByName("user".trim().toUpperCase())
		if(!userRoleInstance){
			userRoleInstance = new Role(
			  name:"user"
			, description:"角色-用户：拥有pinying, favority, product所有的权限"
			)
			.addToPermissions("pinying:*")
			.addToPermissions("favorite:*")
			.addToPermissions("product:*")
			.save(flush:true);
		}
    }
    def destroy = {
    }
}
