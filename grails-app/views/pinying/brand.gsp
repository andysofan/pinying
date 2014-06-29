<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="pinying"/>
	<title>品牌专区~Best Gift</title>
	<g:set var="order" value="${params?.order?:'asc'}"/>
	<g:set var="order" value="${(order == 'asc')?'desc':'asc'}"/>
	<g:set var="sort" value="${params?.sort?:'xquantity'}"/>
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


			var href="<g:createLink controller='index' action='brand' params='[brandId:params?.brandId, offset:params?.offset?:0,sort:sort, order:order]' />";
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
			<img alt="" src="<g:resource dir="images/pinying/images/banner" file="banner08.jpg" />" width="760" height="115" />
		</div>
	</div>
	<div class="mt10">
		<div class="searMain clearFix">
			<div class="hline fl"></div>
			<label class="fl">默认排序：</label>
			<ul class="fl phList">
				<li class="${sort == 'xquantity'?'arrl':''}">
					<a href="<g:createLink controller='index' action='brand' params='[brandId:params?.brandId, offset:params?.offset?:0,sort:'xquantity', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name ]' />" title="">
						销量<em class="spriteicon ${(order == 'asc')?'up':'down'}"></em>
					</a>
				</li>
				<li class="${sort == 'xpopularity'?'arrl':''}">
					<a href="<g:createLink controller='index' action='brand' params='[brandId:params?.brandId, offset:params?.offset?:0,sort:'xpopularity', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name]' />" title="">
						人气<em class="spriteicon ${(order == 'asc')?'down':'up'}"></em>
					</a>
				</li>
				<li class="${sort == 'dateCreated'?'arrl':''}">
					<a href="<g:createLink controller='index' action='brand' params='[brandId:params?.brandId, offset:params?.offset?:0,sort:'dateCreated', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name]' />" title="">
						最新<em class="spriteicon ${(order == 'asc')?'down':'up'}"></em>
					</a>
				</li>
				<li class="${sort == 'xprice'?'arrl':''}">
					<a href="<g:createLink controller='index' action='brand' params='[brandId:params?.brandId, offset:params?.offset?:0,sort:'xprice', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name]' />" title="">
						总价<em class="spriteicon ${(order == 'asc')?'down':'up'}"></em>
					</a>
				</li>
			</ul>
			<div class="fm-price fl">
				<input id="s_marketMinPrice" type="text" data-value="￥" class="txt fl inputFocus" value="${params?.marketMinPrice?:'￥'}" />
				<span class="fl">-</span>
				<input id="s_marketMaxPrice" type="text" data-value="￥" class="txt fl inputFocus" value="${params?.marketMaxPrice?:'￥'}" />
				<span class="fl">|</span>
				<input id="s_name" type="text" data-value="请输入关键字" class="keywords fl inputFocus" value="${params?.name}" />								            <button type="button" class="i fl" onclick="search()">确定</button>
			</div>
		</div>
		<div class="glagrPage">
			<g:render template="/pinying/productListByBrandId" />
		</div>
	</div>
</body>
</html>
