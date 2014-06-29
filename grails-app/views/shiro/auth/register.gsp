<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="login" />
	<title>
		注册
		~
		Best Gift
	</title>
</head>
<body>
	<div id="loginPanel">
		<div class="title clearFix">
			<label class="fl">注 册</label>
			<label class="reg fr">已经注册了帐号？<a href="${createLink(controller:'auth', action:'signIn')}">马上登录 &gt;&gt;</a></label>
		</div>
		<div class="content">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:form action="submitRegister" >
				<table width="970" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td width="112" height="53" align="right">邮 箱</td>
							<td width="617">
								<input type="text" value="${params?.email}" class="text :required :email :max_length;60 :emailUnique fl" name="email" />
								<label class="info fl">请输入常用邮箱，通过验证后可用于登录和找回密码</label>
							</td>
						</tr>
						<tr>
							<td height="53" align="right">用 户 名</td>
							<td>
								<input type="text" value="${params?.username}" class="text :required :max_length;12 :nicknameUnique fl" name="username" />
								<label class="info fl">请输入用户昵称 系统唯一</label>
							</td>
						</tr>
						<tr>
							<td width="112" height="53" align="right">登录密码</td>
							<td width="617">
								<input type="password" value="" class="text :required :min_length;6 :max_length;30 fl" name="password"/>
								<label class="info fl">输入6-30位密码</label>
							</td>
						</tr>
						<tr>
							<td height="53" align="right">确认密码</td>
							<td>
								<input type="password" value="" class="text :required :same_as;usrpassd fl" name="password"/>
								<label class="info fl">输入6-30位密码</label>
							</td>
						</tr>
						<tr>
							<td height="49">&nbsp;</td>
							<td>
								<input type="submit" value="注 册" class="loginBtn" />
							</td>
						</tr>
					</tbody>
				</table>
			</g:form>
		</div>
	</div>
</body>
</html>
