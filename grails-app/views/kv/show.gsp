
<%@ page import="com.cyberoller.pinying.Kv" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="manager">
		<g:set var="entityName" value="${message(code: 'kv.label', default: 'Kv')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>

		<nav:set scope="manager" />
		<nav:set path="manager/kv" />

		<a href="#show-kv" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="show-kv" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list kv">
			
				<g:if test="${kvInstance?.xname}">
				<li class="fieldcontain">
					<span id="xname-label" class="property-label"><g:message code="kv.xname.label" default="Xname" /></span>
					
						<span class="property-value" aria-labelledby="xname-label"><g:fieldValue bean="${kvInstance}" field="xname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${kvInstance?.ximage}">
				<li class="fieldcontain">
					<span id="ximage-label" class="property-label"><g:message code="kv.ximage.label" default="Ximage" /></span>
					<span class="property-value" aria-labelledby="ximage-label"><g:fieldValue bean="${kvInstance}" field="ximage"/></span>
				</li>
				</g:if>

				<g:if test="${kvInstance?.xurl}">
				<li class="fieldcontain">
					<span id="xurl-label" class="property-label"><g:message code="kv.xurl.label" default="URL" /></span>
					
						<span class="property-value" aria-labelledby="xurl-label"><g:fieldValue bean="${kvInstance}" field="xurl"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${kvInstance?.xorder}">
				<li class="fieldcontain">
					<span id="xorder-label" class="property-label"><g:message code="kv.xorder.label" default="Xorder" /></span>
					
						<span class="property-value" aria-labelledby="xorder-label"><g:fieldValue bean="${kvInstance}" field="xorder"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${kvInstance?.xdesc}">
				<li class="fieldcontain">
					<span id="xdesc-label" class="property-label"><g:message code="kv.xdesc.label" default="Xdesc" /></span>
					
						<span class="property-value" aria-labelledby="xdesc-label"><g:fieldValue bean="${kvInstance}" field="xdesc"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${kvInstance?.xisActive}">
				<li class="fieldcontain">
					<span id="xisActive-label" class="property-label"><g:message code="kv.xisActive.label" default="Xis Active" /></span>
					
						<span class="property-value" aria-labelledby="xisActive-label"><g:formatBoolean boolean="${kvInstance?.xisActive}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${kvInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="kv.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${kvInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${kvInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="kv.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${kvInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:kvInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${kvInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link class="edit" action="upload" id="${kvInstance?.id}" >${message(code:'product.label.upload')}</g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
