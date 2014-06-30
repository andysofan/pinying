<%@ page import="com.cyberoller.pinying.ProductGoods" %>
<!DOCTYPE html>
<html>
	<head>

		<meta name="layout" content="manager">
		<g:set var="entityName" value="${message(code: 'xclassOfAdmin.label', default: 'XclassOfAdmin')}" />
		<title>
			上传图片
			~
			<g:message code="global.app.name" default="PinYing"/>
		</title>

		<script type="text/javascript">
			$(document).ready(function() {
				//AJAX 错误
				$(document).ajaxError(function(event, request, settings) {
					alert("AJAX错误!");
				});
				/*监听上传文件 onClick事件*/
				$("#btnUploadImg").click(function(){
					$("form").submit();
				});
			});
		</script>

	</head>
	<body>
		<nav:set scope="manager"/>
		<nav:set path="manager/kv"/>
		
		<div id="import-kv" class="content scaffold-list" role="manager">
			<h1>
				上传KV
			</h1>
			
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

			<!--若备库列表为空，显示上传组件-->
			<g:uploadForm action="uploadImage">
				<g:hiddenField name="id" value="${params?.id}" />
				<input type="file" name="file" id="file" class="upload-file-input-file" />
				<a href="javascript:void(0)" id="btnUploadImg">
					上传
				</a>
			</g:uploadForm>
		</div>

	</body>
</html>
