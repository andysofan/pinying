<%@ page import="com.cyberoller.pinying.ProductCategory" %>



<div class="fieldcontain ${hasErrors(bean: productCategoryInstance, field: 'xname', 'error')} required">
	<label for="xname">
		<g:message code="productCategory.xname.label" default="Xname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="xname" cols="40" rows="5" maxlength="255" required="" value="${productCategoryInstance?.xname}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productCategoryInstance, field: 'parent', 'error')} ">
	<label for="parent">
		<g:message code="productCategory.parent.label" default="Parent" />
		
	</label>
	<g:select id="parent" name="parent.id" from="${com.cyberoller.pinying.ProductCategory.list()}" optionKey="id" value="${productCategoryInstance?.parent?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productCategoryInstance, field: 'subs', 'error')} ">
	<label for="subs">
		<g:message code="productCategory.subs.label" default="Subs" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${productCategoryInstance?.subs?}" var="s">
    <li><g:link controller="productCategory" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="productCategory" action="create" params="['productCategory.id': productCategoryInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'productCategory.label', default: 'ProductCategory')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: productCategoryInstance, field: 'xlevel', 'error')} required">
	<label for="xlevel">
		<g:message code="productCategory.xlevel.label" default="Xlevel" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="xlevel" type="number" value="${productCategoryInstance.xlevel}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: productCategoryInstance, field: 'xorderIndex', 'error')} required">
	<label for="xorderIndex">
		<g:message code="productCategory.xorderIndex.label" default="Xorder Index" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="xorderIndex" type="number" value="${productCategoryInstance.xorderIndex}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: productCategoryInstance, field: 'xstatus', 'error')} required">
	<label for="xstatus">
		<g:message code="productCategory.xstatus.label" default="Xstatus" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="xstatus" type="number" value="${productCategoryInstance.xstatus}" required=""/>

</div>

