
<%@ page import="com.cyberoller.pinying.ProductCategory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="manager">
		<g:set var="entityName" value="${message(code: 'productCategory.label', default: 'ProductCategory')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-productCategory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<nav:set scope="manager" />
		<nav:set path="manager/productCategory" />

		<div id="show-productCategory" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list productCategory">
			
				<g:if test="${productCategoryInstance?.xname}">
				<li class="fieldcontain">
					<span id="xname-label" class="property-label"><g:message code="productCategory.xname.label" default="Xname" /></span>
					
						<span class="property-value" aria-labelledby="xname-label"><g:fieldValue bean="${productCategoryInstance}" field="xname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productCategoryInstance?.parent}">
				<li class="fieldcontain">
					<span id="parent-label" class="property-label"><g:message code="productCategory.parent.label" default="Parent" /></span>
					
						<span class="property-value" aria-labelledby="parent-label"><g:link controller="productCategory" action="show" id="${productCategoryInstance?.parent?.id}">${productCategoryInstance?.parent?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${productCategoryInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="productCategory.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${productCategoryInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${productCategoryInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="productCategory.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${productCategoryInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${productCategoryInstance?.subs}">
				<li class="fieldcontain">
					<span id="subs-label" class="property-label"><g:message code="productCategory.subs.label" default="Subs" /></span>
					
						<g:each in="${productCategoryInstance.subs}" var="s">
						<span class="property-value" aria-labelledby="subs-label"><g:link controller="productCategory" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${productCategoryInstance?.xlevel}">
				<li class="fieldcontain">
					<span id="xlevel-label" class="property-label"><g:message code="productCategory.xlevel.label" default="Xlevel" /></span>
					
						<span class="property-value" aria-labelledby="xlevel-label"><g:fieldValue bean="${productCategoryInstance}" field="xlevel"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productCategoryInstance?.xorderIndex}">
				<li class="fieldcontain">
					<span id="xorderIndex-label" class="property-label"><g:message code="productCategory.xorderIndex.label" default="Xorder Index" /></span>
					
						<span class="property-value" aria-labelledby="xorderIndex-label"><g:fieldValue bean="${productCategoryInstance}" field="xorderIndex"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productCategoryInstance?.xstatus}">
				<li class="fieldcontain">
					<span id="xstatus-label" class="property-label"><g:message code="productCategory.xstatus.label" default="Xstatus" /></span>
					
						<span class="property-value" aria-labelledby="xstatus-label"><g:fieldValue bean="${productCategoryInstance}" field="xstatus"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:productCategoryInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${productCategoryInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
