<%@ page import="com.cyberoller.pinying.ProductGoods" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="manager">
		<g:set var="entityName" value="${message(code: 'productGoods.label', default: 'ProductGoods')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<nav:set scope="manager" />
		<nav:set path="manager/productGoods" />

		<a href="#edit-productGoods" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="edit-productGoods" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${productGoodsInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${productGoodsInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:productGoodsInstance, action:'update']" method="PUT" >
				<g:hiddenField name="version" value="${productGoodsInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
