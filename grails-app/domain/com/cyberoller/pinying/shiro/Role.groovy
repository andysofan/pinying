//角色表

package com.cyberoller.pinying.shiro

class Role {

	//名称
    String name
	//描述
    String description
	//创建日期
	Date dateCreated
	//修改日期
	Date lastUpdated

    static hasMany = [ users: User, permissions: String ]
    
    static belongsTo = User

    static constraints = {
        name(nullable:false, blank:false, unique:true, maxSize:50)
        description(nullable:true, blank:true, maxSize:200)
    }
    
	def beforeValidate(){
    	//用户名大写
    	name = name?.trim()?.toUpperCase()
    }    

    
    String toString(){
    	return "${name}"
    }
    
    static mapping = {
    	table "shiro_role"
    }
}
