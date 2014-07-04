<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="login" />
	<title>
		<g:message code="user.forget.password" />
		~
		Best Gift
	</title>
</head>
<body>
	<div id="loginPanel">
		<div class="title clearFix">
			<label class="fl"></label>
			<label class="reg fr"><g:message code="custom.page.login.data1" /><a href="${createLink(controller:'auth', action:'register')}"><g:message code="custom.page.login.data2" /> &gt;&gt;</a></label>
		</div>
		<div class="content" id="container">
			<g:if test="${flash.message}">
				<div class="base_error" role="status">${flash.message}</div>
			</g:if>
			<g:form action="webForgetPassword">
				<input type="hidden" name="targetUri" value="${targetUri}" />
				<table width="970" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td height="53" align="right"><g:message code="custom.page.register.data5" /></td>
							<td>
								<input type="text" value="${params?.username}" class="text :required fl" name="username" />
								<!-- <label class="info fl"><g:message code="custom.page.register.data5" /></label> -->
							</td>
						</tr>
						<tr>
							<td width="112" height="53" align="right"><g:message code="custom.page.register.data4" /></td>
							<td width="617">
								<input type="text" value="${params?.email}" class="text :required :email fl" name="email" />
								<!-- <label class="info fl"><g:message code="custom.page.register.data4" /></label> -->
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
			V.validate($('#container form'));
		});	
    </script>
</body>
</html>
