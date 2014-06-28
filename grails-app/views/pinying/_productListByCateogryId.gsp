<div class="piIconbg">
	<g:if test="${productInstanceList}">
	<ul class="clearFix">
		<g:each in="${productInstanceList}" status="i" var="productInstance">
		<li>
			<div class="pimg pr">
				<a href="#" title="">
					<img alt="" src="<g:resource dir="images/pinying/images" file="${productInstance.thumbnail}" />" width="151" height="154" />
				</a>
				<div class="pricelist spriteicon">
					<p class="mt10">￥${productInstance.marketMinPrice}</p>
					<p>RMB</p>
				</div>
			</div>
			<p class="pitit"><a href="#" title="" class="bgcolor">${productInstance.name}</a></p>
			<a class="add" href="#" title="">收藏</a>
		</li>
		</g:each>
	</ul>
	<div class="pagelist mt10 clearFix">
		<div class="fr">
			<g:paginate total="${productInstanceCount?:0}" offset="0" max="8" params="[id: params?.id,subCategoryId:params?.subCategoryId, max:params?.max?:8, offset:params?.offset?:0,sort:'quantity', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name]" />
		</div>
	</div>
	</g:if>
</div>
<div class="hline"></div>