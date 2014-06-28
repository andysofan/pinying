<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="pinying"/>
	<title>首页~Best Gift</title>
</head>
<body>
	<!-- 轮播图片 start -->
	<div class="scoll clearFix">
		<div class="hpFocus">
			<ul class="slides">
				<li>
					<a href="#" title="" target="_blank">
						<img src="${resource(dir:'images/pinying/images', file:'kv1.jpg')}" width="764" height="374" alt="">
					</a>
				</li>
				<li>
					<a href="#" title="" target="_blank">
						<img src="${resource(dir:'images/pinying/images', file:'kv2.jpg')}" width="764" height="374" alt="">
					</a>
				</li>
				<li>
					<a href="#" title="" target="_blank">
						<img src="${resource(dir:'images/pinying/images', file:'kv3.jpg')}" width="764" height="374" alt="">
					</a>
				</li>
				<li>
					<a href="#" title="" target="_blank">
						<img src="${resource(dir:'images/pinying/images', file:'kv4.jpg')}" width="764" height="374" alt="">
					</a>
				</li>
			</ul>
		</div>
	</div>
	<!-- 人气产品 -->
	<div class="productList">
		<g:include action="popularProductList" />
	</div>
</body>
</html>
