<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="pinying"/>
	<title>${title}~Best Gift</title>
	<g:set var="order" value="${params?.order?:'asc'}"/>
	<g:set var="order" value="${(order == 'asc')?'desc':'asc'}"/>
	<g:set var="sort" value="${params?.sort?:'quantity'}"/>
</head>
<body>
	<div class="searTop mt10">
		<div class="boxpreleft clearFix">
			<label class="fl">所有选择：</label>
			<div id="appenhtml" class="fl w705">
				<ul class="fl">
					<g:if test="${params?.subCategoryId}">
						<li category="${params?.subCategoryId}">
							<a href="${createLink(action:'category', id:params?.id)}" title="">
								<g:include action="getCategoryName" params="[categoryId:params?.subCategoryId]" />
								<em class="filter_cancel">X</em>
							</a>
						</li>
					</g:if>
					<g:if test="${params?.brandId}">
						<li category="${params?.brandId}">
							<a href="${createLink(action:'category', id:params?.id, params:[subCategoryId:params?.subCategoryId])}" title="">
								<g:include action="getBrandName" params="[brandId:params?.brandId]" />
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
			<g:include action="getCategoryListByParentCategoryId" params="[parentCategoryId:params?.id, subCategoryId : params?.subCategoryId]" />
		</div>
		<div class="listSearch clearFix mt10 filter" id="c_2">
			<label class="fl">品牌</label>
			<g:include action="getBrandListByParentCategoryId" params="[parentCategoryId:params?.id, brandId:params?.brandId]" />
		</div>
		<div class="listSearch clearFix mt10 filter" id="c_3">
			<label class="fl">价格</label>
			<ul class="fr">
				<li>
					<a href="${createLink(action:'category', id:params?.id, params:[subCategoryId:params?.subCategoryId, brandId:params?.brandId])}" title="">50-100</a>
				</li>
				<li>
					<a href="#" title="">200-300</a>
				</li>
				<li>
					<a href="#" title="">300-400</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="mt10">
		<div class="searMain clearFix">
			<div class="hline fl"></div>
			<label class="fl">默认排序：</label>
			<ul class="fl phList">
				<liclass="${sort == 'quantity'?'arrl':''}">
					<a href="<g:createLink action='brand' params='[brandId:params?.brandId, max:params?.max?:8, offset:params?.offset?:0,sort:'quantity', order:order, marketMinPrice: params?.marketMinPrice,marketMaxPrice:params?.marketMaxPrice, name:params?.name ]' />" title="">销量 <em class="spriteicon up"></em></a>
				</li>
				<li>
					<a href="#" title="">人气 <em class="spriteicon down"></em></a>
				</li>
				<li>
					<a href="#" title="">最新 <em class="spriteicon down"></em></a>
				</li>
				<li>
					<a href="#" title="">总价 <em class="spriteicon down"></em></a>
				</li>
			</ul>
			<div class="fm-price fl">
				<input id="inputprice" type="text" data-value="￥" class="txt fl inputFocus" value="￥" />
				<span class="fl">-</span>
				<input id="inputprice" type="text" data-value="￥" class="txt fl inputFocus" value="￥" />
				<span class="fl">|</span>
				<input id="inputprice" type="text" data-value="请输入关键字" class="keywords fl inputFocus" value="请输入关键字" />								                        	<button type="submit" class="i fl">确定</button>
			</div>
		</div>
		<div class="glagrPage">
			<g:render template="/pinying/productListByCateogryId" />
		</div>
	</div>
</body>
</html>
