//用户表

package com.cyberoller.pinying.shiro

class User {

	//用户名
    String username
	//全称
    String fullname
    //邮箱
    String email
	//密码
    String passwordHash
	//盐
    String passwordSalt
    //错误次数
    Integer errortimes
    //禁用
    Boolean isBlocked
    //启用？
    Boolean isActive
	//创建人
	User createdBy
	//修改人
	User lastModifiedBy
    //创建日期
	Date dateCreated
	//修改日期
	Date lastUpdated
    
    static hasMany = [ roles: Role, permissions: String ]

    static constraints = {
		username(nullable:false, blank:false, unique:true, maxSize:20)
        fullname(nullable:false, blank:false, maxSize:35)
        email(nullable:false, blank:false, maxSize:100)
        passwordHash(nullable:true, blank:false, maxSize : 512)
        passwordSalt(nullable:true, blank:false, maxSize : 64)
        errortimes(nullable:false, range:0..3)
        isBlocked(nullable:false)
        isActive(nullable:false)
		createdBy(nullable:true)
		lastModifiedBy(nullable:true)
    }
    
	def beforeValidate(){
    	//用户名大写
    	username = username?.trim()?.toUpperCase()
    	//如果禁用为NULL，则更新为FALSE
    	if(this.isBlocked == null){
    		this.isBlocked = false
    	}
    	//如果启用为NULL，则更新为FALSE
    	if(this.isActive == null){
    		this.isActive = false
    	}
    }    
    
    String toString(){
    	return "${username}"
    }
    
    static mapping = {
    	table "shiro_user"
    }
}