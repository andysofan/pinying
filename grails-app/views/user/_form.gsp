<%@ page import="com.cyberoller.pinying.shiro.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" maxlength="20" required="" value="${userInstance?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'fullname', 'error')} required">
	<label for="fullname">
		<g:message code="user.fullname.label" default="Fullname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fullname" maxlength="35" required="" value="${userInstance?.fullname}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="email" maxlength="100" required="" value="${userInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'errortimes', 'error')} required">
	<label for="errortimes">
		<g:message code="user.errortimes.label" default="Errortimes" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="errortimes" from="${0..3}" class="range" required="" value="${fieldValue(bean: userInstance, field: 'errortimes')}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'isBlocked', 'error')} ">
	<label for="isBlocked">
		<g:message code="user.isBlocked.label" default="Is Blocked" />
		
	</label>
	<g:checkBox name="isBlocked" value="${userInstance?.isBlocked}" />

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'isActive', 'error')} ">
	<label for="isActive">
		<g:message code="user.isActive.label" default="Is Active" />
		
	</label>
	<g:checkBox name="isActive" value="${userInstance?.isActive}" />

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'createdBy', 'error')} ">
	<label for="createdBy">
		<g:message code="user.createdBy.label" default="Created By" />
		
	</label>
	<g:select id="createdBy" name="createdBy.id" from="${com.cyberoller.pinying.shiro.User.list()}" optionKey="id" value="${userInstance?.createdBy?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastModifiedBy', 'error')} ">
	<label for="lastModifiedBy">
		<g:message code="user.lastModifiedBy.label" default="Last Modified By" />
		
	</label>
	<g:select id="lastModifiedBy" name="lastModifiedBy.id" from="${com.cyberoller.pinying.shiro.User.list()}" optionKey="id" value="${userInstance?.lastModifiedBy?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'permissions', 'error')} ">
	<label for="permissions">
		<g:message code="user.permissions.label" default="Permissions" />
		
	</label>
	

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'roles', 'error')} ">
	<label for="roles">
		<g:message code="user.roles.label" default="Roles" />
		
	</label>
	<g:select name="roles" from="${com.cyberoller.pinying.shiro.Role.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.roles*.id}" class="many-to-many"/>

</div>

