<dl>
<g:each in="${secondLevelCategoryInstanceList}" status="i" var="categoryInstance">
	<dd>
		<a href="${createLink(action:'category', id:params?.categoryId, params:[subCategoryId:categoryInstance?.id])}" title="">${categoryInstance?.name}</a>
	</dd>
</g:each>
</dl>