<g:if test="${hotestProductInstance}">
<p>
	<a href="${createLink(controller:'index', action:'product', id:hotestProductInstance.id)}" title="">
		<img src="<g:resource dir="images/pinying/images" file="${hotestProductInstance?.thumbnail}" />" alt="" width="184" height="113"  />
	</a>
</p>
</g:if>