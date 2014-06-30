<ul class="fr">
	<g:each in="${categoryInstanceList}" status="i" var="categoryInstance">
	<li class="${(params.int('categoryId') == categoryInstance.id)?'active':''}">
		<a href="${createLink(action:'index', params:[categoryId:categoryInstance.id])}" title="">${categoryInstance.name}</a>
	</li>
	</g:each>
</ul>