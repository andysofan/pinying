
<%@ page import="com.cyberoller.pinying.Xjob" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="manager">
		<g:set var="entityName" value="${message(code: 'xjob.label', default: 'Xjob')}" />
		<title>
			<g:message code="default.show.label" args="[entityName]" />
			~
			<g:message code="global.app.name" />	
		</title>
	</head>
	<body>
		<nav:set scope="manager" />
		<nav:set path="manager/xjob" />
		<a href="#show-xjob" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="show-xjob" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list xjob">
			
				<g:if test="${xjobInstance?.xcode}">
				<li class="fieldcontain">
					<span id="xcode-label" class="property-label"><g:message code="xjob.xcode.label" default="Xcode" /></span>
					
						<span class="property-value" aria-labelledby="xcode-label"><g:fieldValue bean="${xjobInstance}" field="xcode"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${xjobInstance?.xname}">
				<li class="fieldcontain">
					<span id="xname-label" class="property-label"><g:message code="xjob.xname.label" default="Xname" /></span>
					
						<span class="property-value" aria-labelledby="xname-label"><g:fieldValue bean="${xjobInstance}" field="xname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${xjobInstance?.xquantity}">
				<li class="fieldcontain">
					<span id="xquantity-label" class="property-label"><g:message code="xjob.xquantity.label" default="Xquantity" /></span>
					
						<span class="property-value" aria-labelledby="xquantity-label"><g:fieldValue bean="${xjobInstance}" field="xquantity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${xjobInstance?.xduty}">
				<li class="fieldcontain">
					<span id="xduty-label" class="property-label"><g:message code="xjob.xduty.label" default="Xduty" /></span>
					
						<span class="property-value" aria-labelledby="xduty-label">
							${raw(xjobInstance?.xduty?.replaceAll('\r\n', '<br>'))}
							
						</span>
					
				</li>
				</g:if>
			
				<g:if test="${xjobInstance?.xrequire}">
				<li class="fieldcontain">
					<span id="xrequire-label" class="property-label"><g:message code="xjob.xrequire.label" default="Xrequire" /></span>
					
						<span class="property-value" aria-labelledby="xrequire-label">
						${raw(xjobInstance?.xrequire?.replaceAll('\r\n', '<br>'))}</span>
					
				</li>
				</g:if>
			
				<g:if test="${xjobInstance?.xisActive}">
				<li class="fieldcontain">
					<span id="xisActive-label" class="property-label"><g:message code="xjob.xisActive.label" default="Xis Active" /></span>
					
						<span class="property-value" aria-labelledby="xisActive-label"><g:formatBoolean boolean="${xjobInstance?.xisActive}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${xjobInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="xjob.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${xjobInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${xjobInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="xjob.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${xjobInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:xjobInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${xjobInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
