
<%@ page import="com.cyberoller.pinying.ProductCategory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="manager">
		<g:set var="entityName" value="${message(code: 'productCategory.label', default: 'ProductCategory')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>

		<nav:set scope="manager" />
		<nav:set path="manager/productCategory" />

		<a href="#list-productCategory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-productCategory" class="content scaffold-list" role="main">
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
					
						<g:sortableColumn property="xname" title="${message(code: 'productCategory.xname.label', default: 'Xname')}" />
					
						<th><g:message code="productCategory.parent.label" default="Parent" /></th>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'productCategory.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'productCategory.lastUpdated.label', default: 'Last Updated')}" />
					
						<g:sortableColumn property="xlevel" title="${message(code: 'productCategory.xlevel.label', default: 'Xlevel')}" />
					
						<g:sortableColumn property="xorderIndex" title="${message(code: 'productCategory.xorderIndex.label', default: 'Xorder Index')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${productCategoryInstanceList}" status="i" var="productCategoryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${productCategoryInstance.id}">${fieldValue(bean: productCategoryInstance, field: "xname")}</g:link></td>
					
						<td>${fieldValue(bean: productCategoryInstance, field: "parent")}</td>
					
						<td><g:formatDate date="${productCategoryInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${productCategoryInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: productCategoryInstance, field: "xlevel")}</td>
					
						<td>${fieldValue(bean: productCategoryInstance, field: "xorderIndex")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${productCategoryInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
