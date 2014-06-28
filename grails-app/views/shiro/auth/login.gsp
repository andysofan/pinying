<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="login" />
	<title>
		<g:message code="login.page.title" />
		~
		<g:message code="global.app.name" />
	</title>
</head>
<body>

	<g:if test="${flash.message}">
		<div class="message">
			<g:message code="${flash.message}" args="${flash.args}" default="${flash.default}"/>
		</div>
	</g:if>
	<g:form action="signIn" class="pure-form pure-form-aligned">
	
		<input type="hidden" name="targetUri" value="${targetUri}" />
		
		<fieldset>
			<legend><g:message code="login.page.title" default="Login" /></legend>
			
			<!--username-->
			<div class="pure-control-group">
				<label for="username">
					<g:message code="login.page.username" default="Username" />
				</label>
				<input type="text" name="username" value="${username}" placeholder="${message(code:'login.page.username', default : 'Username')}"/>
        	</div>
        	<!--password-->
        	<div class="pure-control-group">
				<label for="password">
					<g:message code="login.page.password" default="Password" />
				</label>
				<input type="password" name="password" value="" placeholder="${message(code:'login.page.password', default : 'Password')}"/>
			</div>	
			<!--remember me-->
			<div class="pure-controls">
				<label for="rememberMe" class="pure-checkbox">
					<g:checkBox name="rememberMe" value="${rememberMe}" />
					<g:message code="login.page.rememberMe" default="Remember me?" />
				</label>

				<button type="submit" class="pure-button pure-button-primary">
					<g:message code="login.page.signIn" default="Sign in" />
				</button>
			</div>
		</fieldset>		
	</g:form>
</body>
</html>
