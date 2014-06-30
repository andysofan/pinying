<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="pinying"/>
	<title>收藏~Best Gift</title>
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
				s_name = (s_name == '请输入关键字')?'':s_name;

			var href="<g:createLink controller='favorite' action='index' params='[brandId:params?.brandId, offset:params?.offset?:0,sort:sort, order:order]' />";
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
			<label class="fl">所有选择：</label>
			<div id="appenhtml" class="fl w705">
				<ul class="fl">
					<g:if test="${params?.categoryId}">
						<li category="${params?.categoryId}">
							<a href="${createLink(action:'index')}" title="">
								<g:include controller="index" action="getCategoryName" params="[categoryId:params?.categoryId]" />
								<em class="filter_cancel">X</em>
							</a>
						</li>
					</g:if>
					<g:if test="${params?.brandId}">
						<li category="${params?.brandId}">
							<a href="${createLink(action:'index', params:[categoryId:params?.categoryId])}" title="">
								<g:include controller="index" action="getBrandName" params="[brandId:params?.brandId]" />
								<em class="filter_cancel">X</em>
							</a>
						</li>
					</g:if>
					<li class="itearch" id="itearch">
						<span>共有<em class="bgcolor" id="filter_total">${productInstanceCount?:0}</em>个产品</span>
					</li>
				</ul>
			</div>
		</div>
		<div class="listSearch clearFix mt10 filter" id="c_1">
			<label class="fl">分类</label>
			<g:include action="getCategoryList" />
		</div>
		<div class="listSearch clearFix mt10 filter" id="c_2">
			<label class="fl">品牌</label>
			<g:include action="getBrandList" params="[categoryId:params?.categoryId]" />
		</div>
		<div class="listSearch clearFix mt10 filter" id="c_3">
			<label class="fl">价格</label>
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
			<label class="fl">默认排序：</label>
			<ul class="fl phList">
				<li class="${sort == 'xquantity'?'arrl':''}">
					<a href="<g:createLink action='index' params='[brandId:params?.brandId, categoryId:params?.categoryId, offset:params?.offset?:0,sort:'xquantity', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name ]' />" title="">
						销量<em class="spriteicon ${(order == 'asc')?'up':'down'}"></em>
					</a>
				</li>
				<li class="${sort == 'xpopularity'?'arrl':''}">
					<a href="<g:createLink action='index' params='[brandId:params?.brandId, categoryId:params?.categoryId, offset:params?.offset?:0,sort:'xpopularity', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name]' />" title="">人气<em class="spriteicon ${(order == 'asc')?'down':'up'}"></em></a>
				</li>
				<li class="${sort == 'dateCreated'?'arrl':''}">
					<a href="<g:createLink action='index' params='[brandId:params?.brandId, categoryId:params?.categoryId, offset:params?.offset?:0,sort:'dateCreated', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name]' />" title="">最新<em class="spriteicon ${(order == 'asc')?'down':'up'}"></em></a>
				</li>
				<li class="${sort == 'xprice'?'arrl':''}">
					<a href="<g:createLink action='index' params='[brandId:params?.brandId, categoryId:params?.categoryId, offset:params?.offset?:0,sort:'xprice', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name]' />" title="">总价<em class="spriteicon ${(order == 'asc')?'down':'up'}"></em></a>
				</li>
			</ul>
			<div class="fm-price fl">
				<input id="s_marketMinPrice" type="text" data-value="￥" class="txt fl inputFocus" value="${params?.marketMinPrice?:'￥'}" />
				<span class="fl">-</span>
				<input id="s_marketMaxPrice" type="text" data-value="￥" class="txt fl inputFocus" value="${params?.marketMaxPrice?:'￥'}" />
				<span class="fl">|</span>
				<input id="s_name" type="text" data-value="请输入关键字" class="keywords fl inputFocus" value="${params?.name}" />		            
				<button type="button" class="i fl" onclick="search()">确定</button>
			</div>
		</div>
		<div class="glagrPage">
			<g:render template="/favorite/productList" />
		</div>
	</div>
</body>
</html>
