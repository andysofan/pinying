<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="pinying"/>
	<title>产品信息~Best Gift</title>
</head>
<body>
	<div class="aboutconpany mt10">
		<div class="hline"></div>
		<div class="list3">
			<div class="productdetail clearFix">
				<div class="fl pdleft">
					<div class="hpslides">
						<ul class="slides">
							<li>
								<a href="#" title="" target="_blank">
									<img src="<g:resource dir="images/pinying/images/banner" file="banner06.jpg" />" width="579" height="409" alt="">
								</a>
							</li>
							<li>
								<a href="#" title="" target="_blank">
									<img src="<g:resource dir="images/pinying/images/banner" file="banner06.jpg" />" width="579" height="409" alt="">
								</a>
							</li>
							<li>
								<a href="#" title="" target="_blank">
									<img src="<g:resource dir="images/pinying/images/banner" file="banner06.jpg" />" width="579" height="409" alt="">
								</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="fr pdright">
					<h3>产品细节：</h3>
					<ul>
						<li>
							<p>
								<img alt="" src="<g:resource dir="images/pinying/images/detail" file="list301.png" />" width="107" height="100" />
							</p>
							<p>精细转角</p>
						</li>
						<li>
							<p>
								<img alt="" src="<g:resource dir="images/pinying/images/detail" file="list302.png" />" width="107" height="100" />
							</p>
							<p>手工艺的屋顶</p>
						</li>
						<li>
							<p>
								<img alt="" src="<g:resource dir="images/pinying/images/detail" file="list303.png" />" width="107" height="100" />
							</p>
							<p>机器的裁剪</p>
						</li>
					</ul>
				</div>
			</div>
			<div class="detailinfo mt70">
				<ul>
					<li class="clearFix">
						<label>参考单价：</label>
						<span>${productInstance?.xprice}RMB</span>
						<label>包装：</label>
						<span>${productInstance?.xpackage}</span>
					</li>
					<li class="clearFix">
						<label>产品名称：</label>
						<span>${productInstance?.xname}</span>
						<label>材质：</label>
						<span>${productInstance?.xmaterial}</span>
					</li>
					<li class="clearFix">
						<label>尺寸：</label>
						<span>${productInstance?.xsize}</span>
						<label>颜色：</label>
						<span>${productInstance?.xcolor}</span>
					</li>
				</ul>
				<p class="mt10">产品描述：</p>
				<div class="dfinfo">
					${productInstance?.xdesc}
				</div>
				<div class="hline"></div>
			</div>
		</div>
	</div>
</body>
</html>