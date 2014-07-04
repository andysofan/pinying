<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="pinying" />
	<link rel="stylesheet" href="${resource(dir:"css/pinying", file:"static.css")}" />
	<title>
		<g:message code="about.company.label" />
		~
		Best Gift
	</title>
</head>
<body>
	<div class="aboutconpany mt10">
		<div class="hline"></div>
		<div class="breadCrumb">
			<a href="${createLink(controller:'about', action:'company')}" title="<g:message code="menu.about.label" />"><g:message code="menu.about.label" /></a>
			<em>&gt;</em>
			<span><g:message code="about.company.label" /></span>
		</div>
		<div class="banner">
			<img alt="" src="${resource(dir:'images/pinying/images/banner', file:'banner01.jpg')}" width="754" height="151" />
		</div>
		<div class="hline"></div>
		<div class="info">
			<p><g:message code="custom.position.label"/></p>
			<p><g:message code="custom.position.detail"/></p>
		</div>
		<div class="hline"></div>
		<div class="cominfo clearFix">
			<div class="lof fl">
				<img alt="" src="${resource(dir:'images/pinying/images', file:'comlogo.jpg')}" width="167" height="160" />
			</div>
			<div class="fl lf">
				<p><g:message code="custom.company.short" /></p>
				<p><g:message code="custom.company.short.detail1" /></p>
				<p><g:message code="custom.company.short.detail2" /></p>
			</div>
		</div>
		<p class="mt70"><img alt="" src="${resource(dir:'images/pinying/images/banner', file:'banner05.jpg')}" width="756" height="108" /></p>
	</div>
</body>
</html>
