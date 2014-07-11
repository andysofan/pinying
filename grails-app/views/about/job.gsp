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
			<a href="${createLink(controller:'about', action:'company')}" title="${message(code:'menu.about.label')}" >${message(code:'menu.about.label')}</a>
			<em>&gt;</em>
			<span><g:message code="menu.job.label" /></span>
		</div>
		<div class="banner">
			<img alt="" src="${resource(dir:'images/pinying/images/banner', file:'banner07.jpg')}" width="747" height="301"/>
		</div>
		<div class="jobdetail mt10">
			<h2>${xjobInstance?.xname}(${xjobInstance?.xquantity})</h2>
			<div class="mt10">
				<label><g:message code="xjob.xduty.label" default="Xduty" /></label>
				<ul>
					${xjobInstance?.xduty?.replaceAll('/\n/g', '<br/>')}
				</ul>
			</div>
			<div class="mt10">
				<label><g:message code="xjob.xrequire.label" default="Xrequire" /></label>
				<ul>
					${xjobInstance?.xrequire}
				</ul>
			</div>
			<div class="jobbtn clearFix">
				<a href="${createLink(controller:'about', action:'job2')}" title="" class="fr"><g:message code="custom.backto.page.jobs" /></a>
			</div>
		</div>
		<div class="hline"></div>
	</div>
</body>
</html>
