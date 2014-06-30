
<%@ page import="com.cyberoller.pinying.Brand" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="manager">
		<g:set var="entityName" value="${message(code: 'brand.label', default: 'Brand')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<nav:set scope="manager" />
		<nav:set path="manager/brand" />

		<a href="#list-brand" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-brand" class="content scaffold-list" role="main">
			<h1>
				<g:message code="default.list.label" args="[entityName]" />
				<a href="${createLink(action:'create')}">
					+
				</a>				
			</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="xname" title="${message(code: 'brand.xname.label', default: 'Xname')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'brand.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'brand.lastUpdated.label', default: 'Last Updated')}" />
					
						<g:sortableColumn property="xorderIndex" title="${message(code: 'brand.xorderIndex.label', default: 'Xorder Index')}" />
					
						<g:sortableColumn property="xstatus" title="${message(code: 'brand.xstatus.label', default: 'Xstatus')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${brandInstanceList}" status="i" var="brandInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${brandInstance.id}">${fieldValue(bean: brandInstance, field: "xname")}</g:link></td>
					
						<td><g:formatDate date="${brandInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${brandInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: brandInstance, field: "xorderIndex")}</td>
					
						<td>${fieldValue(bean: brandInstance, field: "xstatus")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${brandInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
