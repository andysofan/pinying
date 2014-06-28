//用户未启用异常
package com.cyberoller.pinying.shiro

import org.apache.shiro.authc.AuthenticationException

class AccountBlockedException extends AuthenticationException{
	public AccountBlockedException(String message){
        super(message)
    }
}