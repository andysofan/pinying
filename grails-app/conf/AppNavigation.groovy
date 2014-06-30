import org.apache.shiro.SecurityUtils

def loggedIn = { ->
    SecurityUtils.getSubject().getPrincipal() instanceof String
}

navigation = {
    //管理员
    manager {
        //主页
        home(controller:"home", action:"index", visible: loggedIn, order : 1000)
		//角色
		role(controller:"role", action:"index", visible: loggedIn, order : 1100)
		//用户
		user(controller:"user", action:"index", visible: loggedIn, order : 1200)
		//退出
		signOut(controller:"auth", action:"signOut", visible: loggedIn, order : 9999)
    }
}