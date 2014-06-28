<dl>
	<g:each in="${menuBrandList}" status="i" var="brandInstance">
	<dd>
		<a href="${createLink(action:'brand', params:[brandId:brandInstance.id])}">
			${brandInstance.name}
		</a>
	</dd>
	</g:each>
</dl>