<%@ page import="com.cyberoller.pinying.ProductGoods" %>



<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'xname', 'error')} required">
	<label for="xname">
		<g:message code="productGoods.xname.label" default="Xname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="xname" maxlength="200" required="" value="${productGoodsInstance?.xname}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'category', 'error')} ">
	<label for="category">
		<g:message code="productGoods.category.label" default="Category" />
		
	</label>
	<g:select id="category" name="category.id" from="${com.cyberoller.pinying.ProductCategory.list()}" optionKey="id" value="${productGoodsInstance?.category?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'brand', 'error')} ">
	<label for="brand">
		<g:message code="productGoods.brand.label" default="Brand" />
		
	</label>
	<g:select id="brand" name="brand.id" from="${com.cyberoller.pinying.Brand.list()}" optionKey="id" value="${productGoodsInstance?.brand?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'xthumbnail', 'error')} ">
	<label for="xthumbnail">
		<g:message code="productGoods.xthumbnail.label" default="Xthumbnail" />
		
	</label>
	<g:textField name="xthumbnail" value="${productGoodsInstance?.xthumbnail}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'ximage1', 'error')} ">
	<label for="ximage1">
		<g:message code="productGoods.ximage1.label" default="Ximage1" />
		
	</label>
	<g:textField name="ximage1" value="${productGoodsInstance?.ximage1}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'ximage2', 'error')} ">
	<label for="ximage2">
		<g:message code="productGoods.ximage2.label" default="Ximage2" />
		
	</label>
	<g:textField name="ximage2" value="${productGoodsInstance?.ximage2}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'ximage3', 'error')} ">
	<label for="ximage3">
		<g:message code="productGoods.ximage3.label" default="Ximage3" />
		
	</label>
	<g:textField name="ximage3" value="${productGoodsInstance?.ximage3}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'xishot', 'error')} ">
	<label for="xishot">
		<g:message code="productGoods.xishot.label" default="Xishot" />
		
	</label>
	<g:checkBox name="xishot" value="${productGoodsInstance?.xishot}" />

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'xquantity', 'error')} required">
	<label for="xquantity">
		<g:message code="productGoods.xquantity.label" default="Xquantity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="xquantity" type="number" value="${productGoodsInstance.xquantity}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'xpopularity', 'error')} required">
	<label for="xpopularity">
		<g:message code="productGoods.xpopularity.label" default="Xpopularity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="xpopularity" type="number" value="${productGoodsInstance.xpopularity}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'xpackage', 'error')} required">
	<label for="xpackage">
		<g:message code="productGoods.xpackage.label" default="Xpackage" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="xpackage" required="" value="${productGoodsInstance?.xpackage}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'xmaterial', 'error')} required">
	<label for="xmaterial">
		<g:message code="productGoods.xmaterial.label" default="Xmaterial" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="xmaterial" required="" value="${productGoodsInstance?.xmaterial}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'xsize', 'error')} required">
	<label for="xsize">
		<g:message code="productGoods.xsize.label" default="Xsize" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="xsize" required="" value="${productGoodsInstance?.xsize}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'xcolor', 'error')} required">
	<label for="xcolor">
		<g:message code="productGoods.xcolor.label" default="Xcolor" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="xcolor" required="" value="${productGoodsInstance?.xcolor}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'xdesc', 'error')} required">
	<label for="xdesc">
		<g:message code="productGoods.xdesc.label" default="Xdesc" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="xdesc" required="" value="${productGoodsInstance?.xdesc}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'xorderIndex', 'error')} required">
	<label for="xorderIndex">
		<g:message code="productGoods.xorderIndex.label" default="Xorder Index" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="xorderIndex" type="number" value="${productGoodsInstance.xorderIndex}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'xprice', 'error')} required">
	<label for="xprice">
		<g:message code="productGoods.xprice.label" default="Xprice" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="xprice" value="${fieldValue(bean: productGoodsInstance, field: 'xprice')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'xrecommend', 'error')} ">
	<label for="xrecommend">
		<g:message code="productGoods.xrecommend.label" default="Xrecommend" />
		
	</label>
	<g:checkBox name="xrecommend" value="${productGoodsInstance?.xrecommend}" />

</div>

<div class="fieldcontain ${hasErrors(bean: productGoodsInstance, field: 'xstatus', 'error')} required">
	<label for="xstatus">
		<g:message code="productGoods.xstatus.label" default="Xstatus" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="xstatus" type="number" value="${productGoodsInstance.xstatus}" required=""/>

</div>

