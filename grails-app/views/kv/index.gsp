
<%@ page import="com.cyberoller.pinying.Kv" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="manager">
		<g:set var="entityName" value="${message(code: 'kv.label', default: 'Kv')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>

		<nav:set scope="manager" />
		<nav:set path="manager/kv" />

		<a href="#list-kv" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="list-kv" class="content scaffold-list" role="main">
			<h1>
				<g:message code="default.list.label" args="[entityName]" />
				<a href="${createLink(action:'create')}" style="font-size:16px;">
					<g:message code="default.create.label" args="[entityName]" />
				</a>		
			</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="xname" title="${message(code: 'kv.xname.label', default: 'Xname')}" />
					
						<g:sortableColumn property="ximage" title="${message(code: 'kv.ximage.label', default: 'Ximage')}" />
						<g:sortableColumn property="xurl" title="${message(code: 'kv.xurl.label', default: 'URL')}" />
					
						<g:sortableColumn property="xorder" title="${message(code: 'kv.xorder.label', default: 'Xorder')}" />
					
						<g:sortableColumn property="xdesc" title="${message(code: 'kv.xdesc.label', default: 'Xdesc')}" />
					
						<g:sortableColumn property="xisActive" title="${message(code: 'kv.xisActive.label', default: 'Xis Active')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'kv.dateCreated.label', default: 'Date Created')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${kvInstanceList}" status="i" var="kvInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${kvInstance.id}">${fieldValue(bean: kvInstance, field: "xname")}</g:link></td>
					
						<td>${fieldValue(bean: kvInstance, field: "ximage")}</td>
						<td>${fieldValue(bean: kvInstance, field: "xurl")}</td>
					
						<td>${fieldValue(bean: kvInstance, field: "xorder")}</td>
					
						<td>${fieldValue(bean: kvInstance, field: "xdesc")}</td>
					
						<td><g:formatBoolean boolean="${kvInstance.xisActive}" /></td>
					
						<td><g:formatDate date="${kvInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${kvInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
