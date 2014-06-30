<%@ page import="com.cyberoller.pinying.Brand" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="manager">
		<g:set var="entityName" value="${message(code: 'brand.label', default: 'Brand')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<nav:set scope="manager" />
		<nav:set path="manager/brand" />

		<a href="#show-brand" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-brand" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list brand">
			
				<g:if test="${brandInstance?.xname}">
				<li class="fieldcontain">
					<span id="xname-label" class="property-label"><g:message code="brand.xname.label" default="Xname" /></span>
					
						<span class="property-value" aria-labelledby="xname-label"><g:fieldValue bean="${brandInstance}" field="xname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${brandInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="brand.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${brandInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${brandInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="brand.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${brandInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${brandInstance?.xorderIndex}">
				<li class="fieldcontain">
					<span id="xorderIndex-label" class="property-label"><g:message code="brand.xorderIndex.label" default="Xorder Index" /></span>
					
						<span class="property-value" aria-labelledby="xorderIndex-label"><g:fieldValue bean="${brandInstance}" field="xorderIndex"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${brandInstance?.xstatus}">
				<li class="fieldcontain">
					<span id="xstatus-label" class="property-label"><g:message code="brand.xstatus.label" default="Xstatus" /></span>
					
						<span class="property-value" aria-labelledby="xstatus-label"><g:fieldValue bean="${brandInstance}" field="xstatus"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:brandInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${brandInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
