<%@ page import="com.cyberoller.pinying.Xjob" %>



<div class="fieldcontain ${hasErrors(bean: xjobInstance, field: 'xcode', 'error')} required">
	<label for="xcode">
		<g:message code="xjob.xcode.label" default="Xcode" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="xcode" maxlength="2" required="" value="${xjobInstance?.xcode}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: xjobInstance, field: 'xname', 'error')} required">
	<label for="xname">
		<g:message code="xjob.xname.label" default="Xname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="xname" maxlength="100" required="" value="${xjobInstance?.xname}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: xjobInstance, field: 'xquantity', 'error')} required">
	<label for="xquantity">
		<g:message code="xjob.xquantity.label" default="Xquantity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="xquantity" type="number" value="${xjobInstance.xquantity}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: xjobInstance, field: 'xduty', 'error')} required">
	<label for="xduty">
		<g:message code="xjob.xduty.label" default="Xduty" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="xduty" cols="40" rows="5" maxlength="1000" required="" value="${xjobInstance?.xduty}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: xjobInstance, field: 'xrequire', 'error')} required">
	<label for="xrequire">
		<g:message code="xjob.xrequire.label" default="Xrequire" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="xrequire" cols="40" rows="5" maxlength="1000" required="" value="${xjobInstance?.xrequire}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: xjobInstance, field: 'xisActive', 'error')} ">
	<label for="xisActive">
		<g:message code="xjob.xisActive.label" default="Xis Active" />
		
	</label>
	<g:checkBox name="xisActive" value="${xjobInstance?.xisActive}" />

</div>

