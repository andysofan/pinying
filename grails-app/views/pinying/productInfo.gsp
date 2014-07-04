<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="pinying"/>
	<title><g:message code="custom.menu.productInfo" />~Best Gift</title>
	<link rel="stylesheet" href="${resource(dir:"css/pinying", file:"static.css")}" />
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
									<img src="<g:resource dir="images/pinying/images" file="${productInstance?.ximage1}" />" width="579" height="409" alt="">
								</a>
							</li>
							<li>
								<a href="#" title="" target="_blank">
									<img src="<g:resource dir="images/pinying/images" file="${productInstance?.ximage2}" />" width="579" height="409" alt="">
								</a>
							</li>
							<li>
								<a href="#" title="" target="_blank">
									<img src="<g:resource dir="images/pinying/images" file="${productInstance?.ximage3}" />" width="579" height="409" alt="">
								</a>
							</li>
						</ul>
					</div>
				</div>
			<div class="fr pdright">
				<h3><g:message code="custom.productInfo.detail" /></h3>
				<ul>
					<li>
						<p>
							<img alt="" src="<g:resource dir="images/pinying/images" file="${productInstance?.ximage1}" />" width="107" height="100" />
						</p>
						<p>&nbsp;</p>
					</li>
					<li>
						<p>
							<img alt="" src="<g:resource dir="images/pinying/images" file="${productInstance?.ximage2}" />" width="107" height="100" />
						</p>
						<p>&nbsp;</p>
					</li>
					<li>
						<p>
							<img alt="" src="<g:resource dir="images/pinying/images" file="${productInstance?.ximage3}" />" width="107" height="100" />
						</p>
						<p>&nbsp;</p>
					</li>
				</ul>
			</div>
			</div>

			<div class="detailinfo mt70">
				<ul>
					<li class="clearFix">
						<label><g:message code="custom.productInfo.detail.1" /></label>
						<span>${productInstance?.xprice}RMB</span>
						<label><g:message code="custom.productInfo.detail.2" /></label>
						<span>${productInstance?.xpackage}</span>
					</li>
					<li class="clearFix">
						<label><g:message code="custom.productInfo.detail.3" /></label>
						<span>${productInstance?.xname}</span>
						<label><g:message code="custom.productInfo.detail.4" /></label>
						<span>${productInstance?.xmaterial}</span>
					</li>
					<li class="clearFix">
						<label><g:message code="custom.productInfo.detail.5" /></label>
						<span>${productInstance?.xsize}</span>
						<label><g:message code="custom.productInfo.detail.6" /></label>
						<span>${productInstance?.xcolor}</span>
					</li>
				</ul>
				<p class="mt10"><g:message code="custom.productInfo.detail.7" /></p>
				<div class="dfinfo">
					${productInstance?.xdesc}
				</div>
				<div class="hline"></div>
			</div>
		</div>
	</div>
</body>
</html>
