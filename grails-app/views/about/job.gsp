<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="pinying" />
	<link rel="stylesheet" href="${resource(dir:"css/pinying", file:"static.css")}" />
	<title>
		<g:message code="menu.job.label" />
		~
		Best Gift
	</title>
</head>
<body>
	<div class="aboutconpany mt10">
		<div class="hline"></div>
		<div class="breadCrumb">
			<a href="${createLink(controller:'about', action:'company')}" title="${message(code:'menu.about.label')}">${message(code:'menu.about.label')}</a>
			<em>&gt;</em>
			<span><g:message code="menu.job.label" /></span>
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
			<div class="fl jobs">
				<ul class="fl">
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.1" /></a></li>
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.2" /></a></li>
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.3" /></a></li>
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.4" /></a></li>
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.5" /></a></li>
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.6" /></a></li>
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.7" /></a></li>
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.8" /></a></li>
				</ul>
				<ul class="fl ml50">
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.9" /></a></li>
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.10" /></a></li>
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.11" /></a></li>
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.12" /></a></li>
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.13" /></a></li>
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.14" /></a></li>
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.15" /></a></li>
					<li><a href="${createLink(controller:'about', action:'job2')}" title=""><g:message code="custom.job.16" /></a></li>
				</ul>
			</div>
		</div>
		<p class="mt70"><img alt="" src="${resource(dir:'images/pinying/images/banner', file:'banner04.jpg')}" width="754" height="206" /></p>
	</div>
</body>
</html>
