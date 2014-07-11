<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="pinying"/>
	<title><g:message code="custom.page.search.title" />~Best Gift</title>
	<g:set var="order" value="${params?.order?:'asc'}"/>
	<g:set var="order" value="${(order == 'asc')?'desc':'asc'}"/>
	<g:set var="sort" value="${params?.sort}"/>
	<script type="text/javascript">
		function search(){
			var s_marketMinPrice = $("#s_marketMinPrice").val().replace(/\r\n/g, "");
				s_marketMinPrice = parseInt(s_marketMinPrice, 10);
				s_marketMinPrice = isNaN(s_marketMinPrice)?'':s_marketMinPrice;
			var s_marketMaxPrice = $("#s_marketMaxPrice").val().replace(/\r\n/g, "");
				s_marketMaxPrice = parseInt(s_marketMaxPrice, 10);
				s_marketMaxPrice = isNaN(s_marketMaxPrice)?'':s_marketMaxPrice;
			var s_name = $("#s_name").val().replace(/\r\n/g, "");
				s_name = (s_name == '')?'':s_name;

			var href="<g:createLink controller='search' action='index' params='[brandId:params?.brandId, offset:0,sort:sort, order:order]' />";
				href+="&marketMinPrice=" + s_marketMinPrice;
				href+="&marketMaxPrice=" + s_marketMaxPrice;
				href+="&name=" + s_name;

			window.location.href = href;
		}
	</script>
</head>
<body>

	<div class="searTop mt10">
		<div class="boxpreleft clearFix">
			<label class="fl"><g:message code="custom.page.category.01" /></label>
			<div id="appenhtml" class="fl w705">
				<ul class="fl">
					<g:if test="${params?.categoryId && params.int('categoryId') > 0}">
						<li category="${params?.categoryId}">
							<a href="${createLink(action:'index')}" title="">
								<g:include controller="index" action="getCategoryName" params="[categoryId:params?.categoryId]" />
								<em class="filter_cancel">X</em>
							</a>
						</li>
					</g:if>
					<g:if test="${params?.brandId  && params.int('brandId') > 0}">
						<li category="${params?.brandId}">
							<a href="${createLink(action:'index', params:[categoryId:params?.categoryId])}" title="">
								<g:include controller="index" action="getBrandName" params="[brandId:params?.brandId]" />
								<em class="filter_cancel">X</em>
							</a>
						</li>
					</g:if>
					<li class="itearch" id="itearch">
						<span><g:message code="custom.page.category.02" /><em class="bgcolor" id="filter_total">${productInstanceCount?:0}</em><g:message code="custom.page.category.03" /></span>
					</li>
				</ul>
			</div>
		</div>
		<div class="listSearch clearFix mt10 filter" id="c_1">
			<label class="fl"><g:message code="custom.page.category.04" /></label>
			<g:include action="getCategoryList" />
		</div>
		<div class="listSearch clearFix mt10 filter" id="c_2">
			<label class="fl"><g:message code="custom.page.category.05" /></label>
			<g:include action="getBrandList" params="[categoryId:params?.categoryId]" />
		</div>
		<div class="listSearch clearFix mt10 filter" id="c_3">
			<label class="fl"><g:message code="custom.page.category.06" /></label>
			<ul class="fr">
				<li>
					<a href="${createLink(action:'index', id:params?.id, params:[subCategoryId:params?.subCategoryId, brandId:params?.brandId, marketMinPrice: 50,marketMaxPrice:100, name:params?.name, offset:params?.offset,sort:sort, order:order])}" title="">50-100</a>
				</li>
				<li>
					<a href="${createLink(action:'index', id:params?.id, params:[subCategoryId:params?.subCategoryId, brandId:params?.brandId, marketMinPrice: 200,marketMaxPrice:300, name:params?.name, offset:params?.offset,sort:sort, order:order])}" title="">200-300</a>
				</li>
				<li>
					<a href="${createLink(action:'index', id:params?.id, params:[subCategoryId:params?.subCategoryId, brandId:params?.brandId, marketMinPrice: 300,marketMaxPrice:400, name:params?.name, offset:params?.offset,sort:sort, order:order])}" title="">300-400</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="mt10">
		<div class="searMain clearFix">
			<div class="hline fl"></div>
			<label class="fl"><g:message code="custom.page.category.07" /></label>
			<ul class="fl phList">
				<li class="${sort == 'xquantity'?'arrl':''}">
					<a href="<g:createLink action='index' params='[brandId:params?.brandId, categoryId:params?.categoryId, offset:params?.offset?:0,sort:'xquantity', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name ]' />" title="">
						<g:message code="custom.page.category.08" /><em class="spriteicon ${(order == 'asc')?'up':'down'}"></em>
					</a>
				</li>
				<li class="${sort == 'xpopularity'?'arrl':''}">
					<a href="<g:createLink action='index' params='[brandId:params?.brandId, categoryId:params?.categoryId, offset:params?.offset?:0,sort:'xpopularity', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name]' />" title=""><g:message code="custom.page.category.09" /><em class="spriteicon ${(order == 'asc')?'down':'up'}"></em></a>
				</li>
				<li class="${sort == 'dateCreated'?'arrl':''}">
					<a href="<g:createLink action='index' params='[brandId:params?.brandId, categoryId:params?.categoryId, offset:params?.offset?:0,sort:'dateCreated', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name]' />" title=""><g:message code="custom.page.category.10" /><em class="spriteicon ${(order == 'asc')?'down':'up'}"></em></a>
				</li>
				<li class="${sort == 'xprice'?'arrl':''}">
					<a href="<g:createLink action='index' params='[brandId:params?.brandId, categoryId:params?.categoryId, offset:params?.offset?:0,sort:'xprice', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name]' />" title=""><g:message code="custom.page.category.11" /><em class="spriteicon ${(order == 'asc')?'down':'up'}"></em></a>
				</li>
			</ul>
			<div class="fm-price fl">
				<input id="s_marketMinPrice" type="text" data-value="" class="txt fl inputFocus" value="${params?.marketMinPrice?:''}" />
				<span class="fl">-</span>
				<input id="s_marketMaxPrice" type="text" data-value="" class="txt fl inputFocus" value="${params?.marketMaxPrice?:''}" />
				<span class="fl">|</span>
				<input id="s_name" type="text" data-value="" class="keywords fl inputFocus" value="${params?.name}" />		            
				<button type="button" class="i fl" onclick="search()"><g:message code="custom.page.category.12" /></button>
			</div>
		</div>
		<div class="glagrPage">
			<g:render template="/search/productList" />
		</div>
	</div>
</body>
</html>
