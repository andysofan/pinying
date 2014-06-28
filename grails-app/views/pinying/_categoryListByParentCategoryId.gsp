<ul class="fr">
	<g:each in="${categoryListByParentCategoryId}" status="i" var="categoryInstance">
	<li class="${(params.int('subCategoryId') == categoryInstance.id)?'active':''}">
		<a href="${createLink(action:'category', id:params?.parentCategoryId, params:[subCategoryId:categoryInstance.id])}" title="">${categoryInstance.name}</a>
	</li>
	</g:each>
</ul>