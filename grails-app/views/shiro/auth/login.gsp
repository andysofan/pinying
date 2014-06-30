<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="login" />
	<title>
		登录
		~
		Best Gift
	</title>
</head>
<body>
	<div id="loginPanel">
		<div class="title clearFix">
			<label class="fl">登 录</label>
			<label class="reg fr">还没有帐号？<a href="${createLink(controller:'auth', action:'register')}">马上注册 &gt;&gt;</a></label>
		</div>
		<div class="content">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:form action="signIn">
				<input type="hidden" name="targetUri" value="${targetUri}" />
				<table width="970" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td width="112" height="53" align="right">用户名</td>
							<td width="617">
								<input type="text" value="${username}" class="text :email :max_length;60 :required fl" name="username">
								<label class="info fl">你可用email进行登录</label>
							</td>
						</tr>
						<tr>
							<td height="53" align="right">密码</td>
							<td>
								<input type="password" class="text :min_length;6 :max_length;30 :required fl" name="password">
								<label class="info fl">输入6-30位密码</label>
							</td>
						</tr>
						<tr>
							<td height="49">&nbsp;</td>
							<td>
								<button type="submit" class="pure-button pure-button-primary">
									登 录
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</g:form>
		</div>
	</div>
</body>
</html>
