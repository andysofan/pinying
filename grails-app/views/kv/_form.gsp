<%@ page import="com.cyberoller.pinying.Kv" %>

<div class="fieldcontain ${hasErrors(bean: kvInstance, field: 'xname', 'error')} required">
	<label for="xname">
		<g:message code="kv.xname.label" default="Xname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="xname" maxlength="100" required="" value="${kvInstance?.xname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: kvInstance, field: 'xurl', 'error')} required">
	<label for="xurl">
		<g:message code="kv.xurl.label" default="URL" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="xurl" maxlength="200" required="" value="${kvInstance?.xurl}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: kvInstance, field: 'xorder', 'error')} required">
	<label for="xorder">
		<g:message code="kv.xorder.label" default="Xorder" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="xorder" type="number" value="${kvInstance.xorder}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: kvInstance, field: 'xdesc', 'error')} ">
	<label for="xdesc">
		<g:message code="kv.xdesc.label" default="Xdesc" />
	</label>
	<g:textField name="xdesc" maxlength="100" value="${kvInstance?.xdesc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: kvInstance, field: 'xisActive', 'error')} ">
	<label for="xisActive">
		<g:message code="kv.xisActive.label" default="Xis Active" />
	</label>
	<g:checkBox name="xisActive" value="${kvInstance?.xisActive}" />
</div>