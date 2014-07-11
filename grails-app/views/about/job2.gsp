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
		<div class="jobslist">
			<table cellpadding="0" cellspacing="0" border="0" width="95%;">
				<thead>
					<th width="40%"><g:message code="custom.job.title" /></th>
					<th width="20%"><g:message code="custom.job.location" /></th>
					<th width="20%"><g:message code="custom.job.company" /></th>
					<th width="15%"><g:message code="custom.job.updated" /></th>
				</thead>
				<tbody>
					<g:include controller="index" action="getJobList" />
				</tbody>
			</table>
		</div>
		<div class="hline"></div>
	</div>
</body>
</html>
