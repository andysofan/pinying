<%@ page import="com.cyberoller.pinying.ProductGoods" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="manager">
		<g:set var="entityName" value="${message(code: 'productGoods.label', default: 'ProductGoods')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>

		<nav:set scope="manager" />
		<nav:set path="manager/productGoods" />

		<a href="#show-productGoods" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-productGoods" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list productGoods">
			
				<g:if test="${productGoodsInstance?.xname}">
				<li class="fieldcontain">
					<span id="xname-label" class="property-label"><g:message code="productGoods.xname.label" default="Xname" /></span>
					<span class="property-value" aria-labelledby="xname-label"><g:fieldValue bean="${productGoodsInstance}" field="xname"/></span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.category}">
				<li class="fieldcontain">
					<span id="category-label" class="property-label"><g:message code="productGoods.category.label" default="Category" /></span>
					<span class="property-value" aria-labelledby="category-label">
						<g:link controller="productCategory" action="show" id="${productGoodsInstance?.category?.id}">
							${productGoodsInstance?.category?.encodeAsHTML()}
						</g:link>
					</span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.brand}">
				<li class="fieldcontain">
					<span id="brand-label" class="property-label"><g:message code="productGoods.brand.label" default="Brand" /></span>
					<span class="property-value" aria-labelledby="brand-label">
						<g:link controller="brand" action="show" id="${productGoodsInstance?.brand?.id}">
							${productGoodsInstance?.brand?.encodeAsHTML()}
						</g:link>
					</span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.xthumbnail}">
				<li class="fieldcontain">
					<span id="xthumbnail-label" class="property-label"><g:message code="productGoods.xthumbnail.label" default="Xthumbnail" /></span>
					<span class="property-value" aria-labelledby="xthumbnail-label">
						<g:fieldValue bean="${productGoodsInstance}" field="xthumbnail"/>
					</span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.ximage1}">
				<li class="fieldcontain">
					<span id="ximage1-label" class="property-label"><g:message code="productGoods.ximage1.label" default="Ximage1" /></span>
					<span class="property-value" aria-labelledby="ximage1-label">
						<g:fieldValue bean="${productGoodsInstance}" field="ximage1"/>
					</span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.ximage2}">
				<li class="fieldcontain">
					<span id="ximage2-label" class="property-label"><g:message code="productGoods.ximage2.label" default="Ximage2" /></span>
					<span class="property-value" aria-labelledby="ximage2-label">
						<g:fieldValue bean="${productGoodsInstance}" field="ximage2"/>
					</span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.ximage3}">
				<li class="fieldcontain">
					<span id="ximage3-label" class="property-label"><g:message code="productGoods.ximage3.label" default="Ximage3" /></span>
					<span class="property-value" aria-labelledby="ximage3-label">
						<g:fieldValue bean="${productGoodsInstance}" field="ximage3"/>
					</span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.xishot}">
				<li class="fieldcontain">
					<span id="xishot-label" class="property-label"><g:message code="productGoods.xishot.label" default="Xishot" /></span>
					<span class="property-value" aria-labelledby="xishot-label">
						<g:formatBoolean boolean="${productGoodsInstance?.xishot}" />
					</span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.xquantity}">
				<li class="fieldcontain">
					<span id="xquantity-label" class="property-label"><g:message code="productGoods.xquantity.label" default="Xquantity" /></span>
					<span class="property-value" aria-labelledby="xquantity-label">
						<g:fieldValue bean="${productGoodsInstance}" field="xquantity"/>
					</span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.xpopularity}">
				<li class="fieldcontain">
					<span id="xpopularity-label" class="property-label"><g:message code="productGoods.xpopularity.label" default="Xpopularity" /></span>
					<span class="property-value" aria-labelledby="xpopularity-label"><g:fieldValue bean="${productGoodsInstance}" field="xpopularity"/></span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.xpackage}">
				<li class="fieldcontain">
					<span id="xpackage-label" class="property-label"><g:message code="productGoods.xpackage.label" default="Xpackage" /></span>
					<span class="property-value" aria-labelledby="xpackage-label"><g:fieldValue bean="${productGoodsInstance}" field="xpackage"/></span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.xmaterial}">
				<li class="fieldcontain">
					<span id="xmaterial-label" class="property-label"><g:message code="productGoods.xmaterial.label" default="Xmaterial" /></span>
					<span class="property-value" aria-labelledby="xmaterial-label"><g:fieldValue bean="${productGoodsInstance}" field="xmaterial"/></span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.xsize}">
				<li class="fieldcontain">
					<span id="xsize-label" class="property-label"><g:message code="productGoods.xsize.label" default="Xsize" /></span>
					<span class="property-value" aria-labelledby="xsize-label"><g:fieldValue bean="${productGoodsInstance}" field="xsize"/></span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.xcolor}">
				<li class="fieldcontain">
					<span id="xcolor-label" class="property-label"><g:message code="productGoods.xcolor.label" default="Xcolor" /></span>
					<span class="property-value" aria-labelledby="xcolor-label"><g:fieldValue bean="${productGoodsInstance}" field="xcolor"/></span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.xdesc}">
				<li class="fieldcontain">
					<span id="xdesc-label" class="property-label"><g:message code="productGoods.xdesc.label" default="Xdesc" /></span>
					<span class="property-value" aria-labelledby="xdesc-label"><g:fieldValue bean="${productGoodsInstance}" field="xdesc"/></span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="productGoods.dateCreated.label" default="Date Created" /></span>
					<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${productGoodsInstance?.dateCreated}" /></span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="productGoods.lastUpdated.label" default="Last Updated" /></span>
					<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${productGoodsInstance?.lastUpdated}" /></span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.xorderIndex}">
				<li class="fieldcontain">
					<span id="xorderIndex-label" class="property-label"><g:message code="productGoods.xorderIndex.label" default="Xorder Index" /></span>
					<span class="property-value" aria-labelledby="xorderIndex-label"><g:fieldValue bean="${productGoodsInstance}" field="xorderIndex"/></span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.xprice}">
				<li class="fieldcontain">
					<span id="xprice-label" class="property-label"><g:message code="productGoods.xprice.label" default="Xprice" /></span>
					<span class="property-value" aria-labelledby="xprice-label"><g:fieldValue bean="${productGoodsInstance}" field="xprice"/></span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.xrecommend}">
				<li class="fieldcontain">
					<span id="xrecommend-label" class="property-label"><g:message code="productGoods.xrecommend.label" default="Xrecommend" /></span>
					<span class="property-value" aria-labelledby="xrecommend-label"><g:formatBoolean boolean="${productGoodsInstance?.xrecommend}" /></span>
				</li>
				</g:if>
			
				<g:if test="${productGoodsInstance?.xstatus}">
				<li class="fieldcontain">
					<span id="xstatus-label" class="property-label"><g:message code="productGoods.xstatus.label" default="Xstatus" /></span>
					<span class="property-value" aria-labelledby="xstatus-label"><g:fieldValue bean="${productGoodsInstance}" field="xstatus"/></span>
				</li>
				</g:if>
			</ol>
			<g:form url="[resource:productGoodsInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${productGoodsInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link class="edit" action="upload" id="${productGoodsInstance?.id}" params="[type:'xthumbnail']" >\u4e0a\u4f20\u7f29\u5fae\u56fe</g:link>
					<g:link class="edit" action="upload" id="${productGoodsInstance?.id}" params="[type:'ximage1']" >\u4e0a\u4f20\u5927\u56fe1</g:link>
					<g:link class="edit" action="upload" id="${productGoodsInstance?.id}" params="[type:'ximage2']" >\u4e0a\u4f20\u5927\u56fe2</g:link>
					<g:link class="edit" action="upload" id="${productGoodsInstance?.id}" params="[type:'ximage3']" >\u4e0a\u4f20\u5927\u56fe3</g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
