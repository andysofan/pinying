<%@ page import="com.cyberoller.pinying.shiro.User" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="manager">
    
    <title>
		修改密码
		~
		<g:message code="global.app.name" default="PinYing"/>
	</title>
	
</head>
<body>

	<nav:set scope="manager" />
	<nav:set path="manager/user" />

    <div class="body">
    
        <h1>修改密码</h1>
        
        <g:if test="${flash.message}">
			<div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
        </g:if>
        
        <g:hasErrors bean="${userInstance}">
        <div class="errors">
            <g:renderErrors bean="${userInstance}" as="list" />
        </div>
        </g:hasErrors>
        
        <g:form method="post" >
            <g:hiddenField name="id" value="${userInstance?.id}" />
            <g:hiddenField name="version" value="${userInstance?.version}" />
            <div class="dialog">
            	<g:render template="/user/resetPassword"/>
            </div>
            <div class="buttons">
                <span class="button">
                    <g:actionSubmit class="save" action="reset" value="提交" />
                </span>
            </div>
        </g:form>
    </div>
</body>
</html>
