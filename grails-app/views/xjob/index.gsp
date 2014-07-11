
<%@ page import="com.cyberoller.pinying.Xjob" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="manager">
		<g:set var="entityName" value="${message(code: 'xjob.label', default: 'Xjob')}" />
		<title>
			<g:message code="default.list.label" args="[entityName]" />
			~
			<g:message code="global.app.name" />	
		</title>
	</head>
	<body>
		<nav:set scope="manager" />
		<nav:set path="manager/xjob" />
		<a href="#list-xjob" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="list-xjob" class="content scaffold-list" role="main">
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
					
						<g:sortableColumn property="xcode" title="${message(code: 'xjob.xcode.label', default: 'Xcode')}" />
					
						<g:sortableColumn property="xname" title="${message(code: 'xjob.xname.label', default: 'Xname')}" />
					
						<g:sortableColumn property="xquantity" title="${message(code: 'xjob.xquantity.label', default: 'Xquantity')}" />
					
						<g:sortableColumn property="xduty" title="${message(code: 'xjob.xduty.label', default: 'Xduty')}" />
					
						<g:sortableColumn property="xrequire" title="${message(code: 'xjob.xrequire.label', default: 'Xrequire')}" />
					
						<g:sortableColumn property="xisActive" title="${message(code: 'xjob.xisActive.label', default: 'Xis Active')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${xjobInstanceList}" status="i" var="xjobInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${xjobInstance.id}">${fieldValue(bean: xjobInstance, field: "xcode")}</g:link></td>
					
						<td>${fieldValue(bean: xjobInstance, field: "xname")}</td>
					
						<td>${fieldValue(bean: xjobInstance, field: "xquantity")}</td>
					
						<td>${fieldValue(bean: xjobInstance, field: "xduty")}</td>
					
						<td>${fieldValue(bean: xjobInstance, field: "xrequire")}</td>
					
						<td><g:formatBoolean boolean="${xjobInstance.xisActive}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${xjobInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
