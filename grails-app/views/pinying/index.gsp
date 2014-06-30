<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="pinying"/>
	<title>首页~Best Gift</title>
</head>
<body>
	<!-- 轮播图片 start -->
	<div class="scoll clearFix">
		<g:include controller="index" action="getKvList" />
	</div>
	<!-- 人气产品 -->
	<div class="productList">
		<g:include action="popularProductList" />
	</div>
</body>
</html>
