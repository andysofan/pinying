<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="pinying"/>
	<title>
		<g:if test="${params.int('brandId').toLong() >= -1L}">
			<g:message code="custom.page.menu.brand" />
		</g:if>
		<g:else>
			<g:message code="custom.page.menu.other" />
		</g:else>
		~Best Gift
	</title>
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


			var href="<g:createLink controller='index' action='brand' params='[brandId:params?.brandId, offset:0,sort:sort, order:order]' />";
				href+="&marketMinPrice=" + s_marketMinPrice;
				href+="&marketMaxPrice=" + s_marketMaxPrice;
				href+="&name=" + s_name;

			window.location.href = href;
		}
	</script>
</head>
<body>
	<div class="searTop mt10">
		<div class="banner mt5">
			<g:if test="${params.int('brandId').toLong() >= -1L}">
				<img alt="" src="<g:resource dir="images/pinying/images/banner" file="banner08.jpg" />" width="760" height="115" />
			</g:if>
			<g:else>
				<img alt="" src="<g:resource dir="images/pinying/images/banner" file="banner09.jpg" />" width="760" height="115" />
			</g:else>
		</div>
	</div>
	<div class="mt10">
		<div class="searMain clearFix">
			<div class="hline fl"></div>
			<label class="fl"><g:message code="custom.page.category.01" /></label>
			<ul class="fl phList">
				<li class="${sort == 'xquantity'?'arrl':''}">
					<a href="<g:createLink controller='index' action='brand' params='[brandId:params?.brandId, offset:params?.offset?:0,sort:'xquantity', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name ]' />" title="">
						<g:message code="custom.page.category.08" /><em class="spriteicon ${(order == 'asc')?'up':'down'}"></em>
					</a>
				</li>
				<li class="${sort == 'xpopularity'?'arrl':''}">
					<a href="<g:createLink controller='index' action='brand' params='[brandId:params?.brandId, offset:params?.offset?:0,sort:'xpopularity', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name]' />" title="">
						<g:message code="custom.page.category.09" /><em class="spriteicon ${(order == 'asc')?'down':'up'}"></em>
					</a>
				</li>
				<li class="${sort == 'dateCreated'?'arrl':''}">
					<a href="<g:createLink controller='index' action='brand' params='[brandId:params?.brandId, offset:params?.offset?:0,sort:'dateCreated', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name]' />" title="">
						<g:message code="custom.page.category.10" /><em class="spriteicon ${(order == 'asc')?'down':'up'}"></em>
					</a>
				</li>
				<li class="${sort == 'xprice'?'arrl':''}">
					<a href="<g:createLink controller='index' action='brand' params='[brandId:params?.brandId, offset:params?.offset?:0,sort:'xprice', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name]' />" title="">
						<g:message code="custom.page.category.11" /><em class="spriteicon ${(order == 'asc')?'down':'up'}"></em>
					</a>
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
			<g:render template="/pinying/productListByBrandId" />
		</div>
	</div>
</body>
</html>
