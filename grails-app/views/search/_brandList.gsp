<ul class="fr">
	<g:each in="${brandInstanceList}" status="i" var="brandInstance">
	<li class="${(params.int('brandId') == brandInstance.id)?'active':''}">
		<a href="${createLink(action:'index', params:[categoryId:params?.categoryId, brandId:brandInstance.id])}" title="">${brandInstance.name}</a>
	</li>
	</g:each>
</ul>