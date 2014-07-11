<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="login" />
	<title>
		<g:message code="custom.page.register.title" />
		~
		Best Gift
	</title>
	<script type="text/javascript">
		$(document).ready(function(){
			V.validate($('#container form'));
		});	
    </script>
</head>
<body>
	<div id="loginPanel">
		<div class="title clearFix">
			<label class="fl"><g:message code="custom.page.register.data1" /></label>
			<label class="reg fr"><g:message code="custom.page.register.data2" /><a href="${createLink(controller:'auth', action:'login')}"><g:message code="custom.page.register.data3" /> &gt;&gt;</a></label>
		</div>
		<div class="content" id="container">
			<g:if test="${flash.message}">
				<div class="base_error" role="status">${flash.message}</div>
			</g:if>
			<g:form action="submitRegister" >
				<table width="970" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td width="112" height="53" align="right"><g:message code="custom.page.register.data4" /></td>
							<td width="617">
								<input type="text" value="${params?.email}" class="text :required :email fl" name="email" />
								<!-- <label class="info fl"><g:message code="custom.page.register.data4" /></label> -->
							</td>
						</tr>
						<tr>
							<td height="53" align="right"><g:message code="custom.page.register.data5" /></td>
							<td>
								<input type="text" value="${params?.username}" class="text :required fl" name="username" />
								<!-- <label class="info fl"><g:message code="custom.page.register.data5" /></label> -->
							</td>
						</tr>
						<tr>
							<td width="112" height="53" align="right"><g:message code="custom.page.register.data6" /></td>
							<td width="617">
								<input type="password" value="" class="text :required :min_length;8 :max_length;30 fl" name="password" id="usrpassd" />
								<label class="info fl"><g:message code="custom.page.register.data7" /></label>
							</td>
						</tr>
						<tr>
							<td height="53" align="right"><g:message code="custom.page.register.data8" /></td>
							<td>
								<input type="password" value="" class="text :required :same_as;usrpassd fl" name="password" id="reusrpassd" />
								<label class="info fl"><g:message code="custom.page.register.data7" /></label>
							</td>
						</tr>
						<tr>
							<td height="49">&nbsp;</td>
							<td>
								<input type="submit" value="" class="loginBtn" />
							</td>
						</tr>
					</tbody>
				</table>
			</g:form>
		</div>
	</div>

</body>
</html>
