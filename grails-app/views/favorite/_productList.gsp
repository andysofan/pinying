<g:if test="${productInstanceList}">
<div class="piIconbg">
	<ul class="clearFix">
		<div class="clearFix" id="page_list">
			<a class="fr spriteicon" id="batch_export_button" href="${createLink(controller:'favorite', action:'export')}">${message(code:'custom.page.favorite.export1')}</a>
		</div>
	<g:each in="${productInstanceList}" status="i" var="productInstance">
		<li>
			<div class="pimg pr">
				<a href="${createLink(controller:'index', action:'product', id:productInstance.id)}" title="">
					<img alt="" src="<g:resource dir="images/pinying/images" file="${productInstance.thumbnail}" />" width="151" height="154" />
				</a>
				<div class="pricelist spriteicon">
					<p class="mt10">${productInstance.price}</p>
					<p>RMB</p>
				</div>
			</div>
			<p class="pitit"><a href="${createLink(controller:'index', action:'product', id:productInstance.id)}" title="" class="bgcolor">${productInstance.name}</a></p>
			<shiro:isLoggedIn>
			<div class="clearFix">
				<a class="add fl" href="javascript:void(0)" onclick="return toggleFavorite(this, ${productInstance.id})" title="">
					<g:include controller="favorite" action="isFavorite" params="[productId:productInstance.id]" />
				</a>
				<a class="alone_export fr" href="${createLink(controller:'favorite', action:'export', id:productInstance.id)}">${message(code:'custom.page.favorite.export2')}</a>
				</shiro:isLoggedIn>
			</div>
		</li>
	</g:each>
	</ul>
	
	<div class="pagelist mt10 clearFix">
		<div class="fr">
			<g:paginate total="${productInstanceCount?:0}" offset="0" max="8" params="[brandId:params?.brandId, max:params?.max?:8, offset:params?.offset?:0,sort:sort, order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name]" />
		</div>
	</div>
</div>
</g:if>
<div class="hline"></div>