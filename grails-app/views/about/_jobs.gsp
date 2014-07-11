<g:each in="${xjobInstanceList}" status="i" var="xjobInstance">
	<tr>
		<td><span><a href="<g:createLink controller='index' action='getJob' id='${xjobInstance?.id}' />">${xjobInstance?.xname}</span></td>
		<td><g:message code="custom.job.location.1" /></td>
		<td><g:message code="custom.job.company.1" /></td>
		<td>${formatDate(date:xjobInstance?.lastUpdated, formatName:'custom.date.format')}</td>
	</tr>
</g:each>