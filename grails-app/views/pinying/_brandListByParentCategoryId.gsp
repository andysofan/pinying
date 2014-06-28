<ul class="fr">
	<g:each in="${brandListByParentCategoryId}" status="i" var="brandInstance">
	<li class="${(params.int('brandId') == brandInstance.id)?'active':''}">
		<a href="${createLink(action:'category', id:params?.parentCategoryId, params:[subCategoryId:params?.subCategoryId, brandId:brandInstance.id])}" title="">${brandInstance.name}</a>
	</li>
	</g:each>
</ul>