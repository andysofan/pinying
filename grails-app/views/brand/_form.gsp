<%@ page import="com.cyberoller.pinying.Brand" %>



<div class="fieldcontain ${hasErrors(bean: brandInstance, field: 'xname', 'error')} required">
	<label for="xname">
		<g:message code="brand.xname.label" default="Xname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="xname" cols="40" rows="5" maxlength="255" required="" value="${brandInstance?.xname}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: brandInstance, field: 'xorderIndex', 'error')} required">
	<label for="xorderIndex">
		<g:message code="brand.xorderIndex.label" default="Xorder Index" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="xorderIndex" type="number" value="${brandInstance.xorderIndex}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: brandInstance, field: 'xstatus', 'error')} required">
	<label for="xstatus">
		<g:message code="brand.xstatus.label" default="Xstatus" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="xstatus" type="number" value="${brandInstance.xstatus}" required=""/>

</div>

