<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="pinying" />
	<link rel="stylesheet" href="${resource(dir:"css/pinying", file:"static.css")}" />

	<title>
		<g:message code="menu.brand.label" />
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
			<span><g:message code="menu.brand.label" /></span>
		</div>
		<div class="banner">
			<img alt="" src="${resource(dir:'images/pinying/images/banner', file:'banner03.jpg')}" width="747" height="286" />
		</div>
		<div class="tmain brandlist">
			<h2><g:message code="menu.brand.label" /></h2>
			<ul class="clearFix mt10">
				<li>
					<img alt="" src="${resource(dir:'images/pinying/images/brand', file:'1.jpg')}" width="144" height="63" />
				</li>
				<li>
					<img alt="" src="${resource(dir:'images/pinying/images/brand', file:'2.jpg')}" width="144" height="63" />
				</li>
				<li>
					<img alt="" src="${resource(dir:'images/pinying/images/brand', file:'3.jpg')}" width="144" height="63" />
				</li>
				<li class="lastbrand">
					<img alt="" src="${resource(dir:'images/pinying/images/brand', file:'4.jpg')}" width="144" height="63" />
				</li>
			</ul>
			<ul class="clearFix mt25">
				<li>
					<img alt="" src="${resource(dir:'images/pinying/images/brand', file:'5.jpg')}" width="144" height="63" />
				</li>
				<li>
					<img alt="" src="${resource(dir:'images/pinying/images/brand', file:'6.jpg')}" width="144" height="63" />
				</li>
				<li>
					<img alt="" src="${resource(dir:'images/pinying/images/brand', file:'7.jpg')}" width="144" height="63" />
				</li>
			</ul>
		</div>
	</div>
</body>
</html>
