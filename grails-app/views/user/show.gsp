
<%@ page import="com.cyberoller.pinying.shiro.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="manager">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
	
		<nav:set scope="manager" />
		<nav:set path="manager/user" />
	
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="show-user" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">
			
				<g:if test="${userInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="user.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${userInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.fullname}">
				<li class="fieldcontain">
					<span id="fullname-label" class="property-label"><g:message code="user.fullname.label" default="Fullname" /></span>
					
						<span class="property-value" aria-labelledby="fullname-label"><g:fieldValue bean="${userInstance}" field="fullname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="user.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${userInstance}" field="email"/></span>
					
				</li>
				</g:if>
						
				<g:if test="${userInstance?.errortimes}">
				<li class="fieldcontain">
					<span id="errortimes-label" class="property-label"><g:message code="user.errortimes.label" default="Errortimes" /></span>
					
						<span class="property-value" aria-labelledby="errortimes-label"><g:fieldValue bean="${userInstance}" field="errortimes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.isBlocked}">
				<li class="fieldcontain">
					<span id="isBlocked-label" class="property-label"><g:message code="user.isBlocked.label" default="Is Blocked" /></span>
					
						<span class="property-value" aria-labelledby="isBlocked-label"><g:formatBoolean boolean="${userInstance?.isBlocked}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.isActive}">
				<li class="fieldcontain">
					<span id="isActive-label" class="property-label"><g:message code="user.isActive.label" default="Is Active" /></span>
					
						<span class="property-value" aria-labelledby="isActive-label"><g:formatBoolean boolean="${userInstance?.isActive}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.createdBy}">
				<li class="fieldcontain">
					<span id="createdBy-label" class="property-label"><g:message code="user.createdBy.label" default="Created By" /></span>
					
						<span class="property-value" aria-labelledby="createdBy-label"><g:link controller="user" action="show" id="${userInstance?.createdBy?.id}">${userInstance?.createdBy?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.lastModifiedBy}">
				<li class="fieldcontain">
					<span id="lastModifiedBy-label" class="property-label"><g:message code="user.lastModifiedBy.label" default="Last Modified By" /></span>
					
						<span class="property-value" aria-labelledby="lastModifiedBy-label"><g:link controller="user" action="show" id="${userInstance?.lastModifiedBy?.id}">${userInstance?.lastModifiedBy?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="user.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${userInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="user.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${userInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.permissions}">
				<li class="fieldcontain">
					<span id="permissions-label" class="property-label"><g:message code="user.permissions.label" default="Permissions" /></span>
					
						<span class="property-value" aria-labelledby="permissions-label"><g:fieldValue bean="${userInstance}" field="permissions"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.roles}">
				<li class="fieldcontain">
					<span id="roles-label" class="property-label"><g:message code="user.roles.label" default="Roles" /></span>
					
						<g:each in="${userInstance.roles}" var="r">
						<span class="property-value" aria-labelledby="roles-label"><g:link controller="role" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:userInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${userInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
