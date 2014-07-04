<%@ page import="com.cyberoller.pinying.ProductGoods" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="layout" content="manager">
		<g:set var="entityName" value="${message(code: 'xclassOfAdmin.label', default: 'XclassOfAdmin')}" />
		<title>
			${message(code:'product.image.upload.title')}
			~
			<g:message code="global.app.name" default="PinYing"/>
		</title>

		<script type="text/javascript">
			$(document).ready(function() {
				$(document).ajaxError(function(event, request, settings) {
					alert("AJAX error!");
				});
				$("#btnUploadImg").click(function(){
					$("form").submit();
				});
			});
		</script>

	</head>
	<body>
		<nav:set scope="manager"/>
		<nav:set path="manager/productGoods"/>
		
		<div id="import-XclassOfAdmin" class="content scaffold-list" role="manager">
			<h1>
				${message(code:'product.image.upload')}
			</h1>
			
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

			<g:uploadForm action="uploadImage">
				<g:hiddenField name="id" value="${params?.id}" />
				<g:hiddenField name="type" value="${params?.type}" />
				<input type="file" name="file" id="file" class="upload-file-input-file" />
				<a href="javascript:void(0)" id="btnUploadImg">
					 ${message(code:'product.label.upload')}
				</a>
			</g:uploadForm>
		</div>
	</body>
</html>
