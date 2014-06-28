//用户未启用异常
package com.cyberoller.pinying.shiro

import org.apache.shiro.authc.AuthenticationException

class AccountInactiveException extends AuthenticationException{
	public AccountInactiveException(String message){
        super(message)
    }
}