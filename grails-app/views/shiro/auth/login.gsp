<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="login" />
	<title>
		<g:message code="custom.page.login.title" />
		~
		Best Gift
	</title>
</head>
<body>
	<div id="loginPanel">
		<div class="title clearFix">
			<label class="fl"><g:message code="custom.page.login.title" /></label>
			<label class="reg fr"><g:message code="custom.page.login.data1" /><a href="${createLink(controller:'auth', action:'register')}"><g:message code="custom.page.login.data2" /> &gt;&gt;</a></label>
		</div>
		<div class="content" id="container">
			<g:if test="${flash.message}" >
				<div class="base_error" role="status">
					<g:message code="${flash.message}" args="${flash.args}" default="${flash.default}"/>
				</div>
			</g:if>
			<g:form action="signIn">
				<input type="hidden" name="targetUri" value="${targetUri}" />
				<table width="970" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td width="112" height="53" align="right"><g:message code="custom.page.login.data3" /></td>
							<td width="617">
								<input type="text" value="${username}" class="text :required fl" name="username">
								<label class="info fl">&nbsp;</label>
							</td>
						</tr>
						<tr>
							<td height="53" align="right"><g:message code="custom.page.login.data5" /></td>
							<td>
								<input type="password" class="text :min_length;8 :max_length;30 :required fl" name="password">
								<label class="info fl"><g:message code="custom.page.login.data6" /></label>
							</td>
						</tr>
						<tr>
							<td height="49">&nbsp;</td>
							<td class="pl20">
								<button type="submit" class="pure-button pure-button-primary" style="display: inline-block;float: left;">
									
								</button>
								<a href="${createLink(controller:'auth',action:'webForgetPassword')}" style="margin-left: 20px;float: left;display: inline-block;"><g:message code="user.foget.password.label" /></a>
							</td>
						</tr>
					</tbody>
				</table>
			</g:form>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			V.validate($('#container form'));
		});	
    </script>
</body>
</html>
