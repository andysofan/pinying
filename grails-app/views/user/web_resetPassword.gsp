<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="login" />
	<title>
		<g:message code="custom.auth.resetpassword" />
		~
		Best Gift
	</title>
</head>
<body>
	<div id="loginPanel">
		<div class="title clearFix">
			<label class="fl"><g:message code="custom.auth.resetpassword" /></label>
			<label class="reg fr"><g:message code="custom.page.login.data1" /><a href="${createLink(controller:'auth', action:'register')}"><g:message code="custom.page.login.data2" /> &gt;&gt;</a></label>
		</div>
		<div class="content" id="container">
			<g:if test="${flash.message}">
				<div class="base_error" role="status">${flash.message}</div>
			</g:if>
			<g:form action="webResetPassword">
				<input type="hidden" name="targetUri" value="${targetUri}" />
				<g:hiddenField name="id" value="${userInstance?.id}" />
				<g:hiddenField name="version" value="${userInstance?.version}" />
				<g:hiddenField name="username" value="${userInstance?.username}" />
				<table width="970" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td width="112" height="53" align="right"><g:message code="custom.page.login.data3" /></td>
							<td width="617">
								<input type="text" value="${userInstance.username}" disabled = 'disabled' class="text :required fl" name="username">
								<label class="info fl">&nbsp;</label>
							</td>
						</tr>
						<tr>
							<td width="112" height="53" align="right"><g:message code="user.new.password" /></td>
							<td width="617">
								<input type="password" value="" class="text :required :min_length;8 :max_length;30 fl" name="password" id="usrpassd" />
								<label class="info fl"><g:message code="custom.page.register.data7" /></label>
							</td>
						</tr>
						<tr>
							<td height="53" align="right"><g:message code="user.confire.password" /></td>
							<td>
								<input type="password" value="" class="text :required :same_as;usrpassd fl" name="passwordConfirm" id="reusrpassd" />
								<label class="info fl"><g:message code="custom.page.register.data7" /></label>
							</td>
						</tr>
						<tr>
							<td height="49">&nbsp;</td>
							<td class="pl20">
								<button type="submit" class="confirm-button pure-button-primary">
									
								</button>
						
							</td>
						</tr>
					</tbody>
				</table>
			</g:form>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			//V.submitRevalidateList=[$("input[name='captcha']")];
			V.validate($('#container form'));
		});	
    </script>
</body>
</html>
