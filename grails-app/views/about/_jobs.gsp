<g:each in="${xjobInstanceList}" status="i" var="xjobInstance">
	<tr>
		<td><a href="<g:createLink controller='index' action='getJob' id='${xjobInstance?.id}' />">${xjobInstance?.xname}</td>
		<td><g:message code="custom.job.location.1" /></td>
		<td><g:message code="custom.job.company.1" /></td>
		<td>${formatDate(date:xjobInstance?.lastUpdated, formatName:'custom.date.format')}</td>
	</tr>
</g:each>