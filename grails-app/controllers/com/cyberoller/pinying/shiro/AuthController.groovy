package com.cyberoller.pinying.shiro

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.web.util.SavedRequest
import org.apache.shiro.web.util.WebUtils

import grails.transaction.Transactional


class AuthController {

	def userService
    def shiroSecurityManager
    
	def beforeInterceptor = {
		log.debug "auth.action is ${actionName}, lang is ${params['lang']}"
		
    	params["lang"] = "zh_CN"
	}
	
    def index = { 
    	redirect(action: "login", params: params) 
    }

    def login = {
    	if(!params?.lang){
    		redirect action:"index"
    	}else{
        	render view : "/shiro/auth/login", model : [ username: params.username, rememberMe: (params.rememberMe != null), targetUri: params.targetUri]
    	}
    }

    def signIn = {
        def authToken = new UsernamePasswordToken(params.username?.toUpperCase(), params.password as String)

        // Support for "remember me"
        if (params.rememberMe) {
            authToken.rememberMe = true
        }
        
        // If a controller redirected to this page, redirect back
        // to it. Otherwise redirect to the root URI.
        def targetUri = params.targetUri ?: "/"
        
        // Handle requests saved by Shiro filters.
        SavedRequest savedRequest = WebUtils.getSavedRequest(request)
        if (savedRequest) {
            targetUri = savedRequest.requestURI - request.contextPath
            if (savedRequest.queryString) targetUri = targetUri + '?' + savedRequest.queryString
        }
        
        try{
            // Perform the actual login. An AuthenticationException
            // will be thrown if the username is unrecognised or the
            // password is incorrect.
            SecurityUtils.subject.login(authToken)

            log.info "Redirecting to '${targetUri}'."
            redirect(uri: targetUri)
        }
        catch (AuthenticationException ex){
            // Authentication failed, so display the appropriate message
            // on the login page.
            log.info "Authentication failure for user '${params.username}'."
            switch(ex){
            	case AccountInactiveException :
            		flash.message = "login.account.inactive"
            		flash.args = ["${params.username}"]
					flash.default = ex.getMessage()
            		break
            	case AccountBlockedException :
            		flash.message = "login.account.blocked"
            		flash.args = ["${params.username}"]
					flash.default = ex.getMessage()
            		break
            	default :
            		flash.message = "login.failed"
            		flash.args = null
					flash.default = ex.getMessage()
            }
            // Keep the username and "remember me" setting so that the
            // user doesn't have to enter them again.
            def m = [ username: params.username ]
            if (params.rememberMe) {
                m["rememberMe"] = true
            }

            // Remember the target URI too.
            if (params.targetUri) {
                m["targetUri"] = params.targetUri
                m["lang"] = "zh_CN"
            }

            // Now redirect back to the login page.
            redirect(action: "login", params: m)
        }
    }

    def signOut = {
        // Log the user out of the application.
        SecurityUtils.subject?.logout()
        webRequest.getCurrentRequest().session = null

        // For now, redirect back to the home page.
        redirect(uri: "/")
    }
    
    def register(){
		render view:"/shiro/auth/register"
    }
	
	//忘记密码页面
	def webForgetPassword(){
		render view:"/shiro/auth/web_forgetPassword"
	}
	
	//忘记密码
	@Transactional
	def forgetPasswordByWeb( params ){
		def userInstance
		try{
			userInstance = userService.queryUserByEmailAndUsername(params)
			if(!userInstance){
				throw new RuntimeException("用户不存在:${e.getMessage()}")
				return
			}
			redirect action:"login"
		}catch(e){
			throw new RuntimeException("用户不存在:${e.getMessage()}")
			render view:"/shiro/auth/web_forgetPassword"
		}
	}


    def submitRegister(){
    	try{
			userService.register(params)
			chain action : "signIn", params:[username:params?.username, password:params?.password]
    	}catch(e){
    		flash.message = e.getMessage()
    		render view:"/shiro/auth/register", model:[params:params]
    	}
    }

    def unauthorized = {
        render "You do not have permission to access this page."
    }
}
