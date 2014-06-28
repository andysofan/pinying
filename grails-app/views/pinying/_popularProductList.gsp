<g:if test="${popularProductList}">
<g:set var="size" value="${popularProductList.size()}" />
<p class="hline"></p>
	<div class="proBox">
	<g:each in="${popularProductList}" status="i" var="productInstance">
		<g:if test="${i == 0}">
			<ul class="clearFix">
		</g:if>
		<g:elseif test="${i % 4 == 0 && i < size - 1}">
			</ul>
			<ul class="clearFix">
		</g:elseif>
		<li class="${(i % 4 == 3)?'last':''}">
			<p>
				<a href="#" title="" class="prolistpic">
					<img alt="" src="<g:resource dir="images/pinying/images" file="${productInstance.thumbnail}" />" width="187" height="144" />
				</a>
			</p>
			<p class="mtb5"><a href="#" title="">${productInstance.name}</a></p>
		</li>
		<g:if test="${i == size - 1}">
			</ul>
		</g:if>
	</g:each>
	</div>
	<p class="hline"></p>
</g:if>