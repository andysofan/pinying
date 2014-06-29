<ul class="clearFix">
	<g:each in="${recommendProductList}" status="i" var="productInstance">
	<li>
		<a href="#" title="">
			<img alt="" src="<g:resource dir="images/pinying/images" file="${productInstance?.thumbnail}" />" width="196" height="102" />
		</a>
		<div class="price">
			<p class="name">${productInstance?.name}</p>
			<p class="num">¥${productInstance?.price} RMB </p>
		</div>
	</li>
	</g:each>
</ul>