<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordHash', 'error')} required">
	<label for="password">
		密码
		<span class="required-indicator">*</span>
	</label>
	<input type="password" name="password" maxLength="10" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordConfirm', 'error')} required">
	<label for="passwordConfirm">
		确认密码
		<span class="required-indicator">*</span>
	</label>
	<input type="password" name="passwordConfirm" maxLength="10" required=""/>
</div>

<br>