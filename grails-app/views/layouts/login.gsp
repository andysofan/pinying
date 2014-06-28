<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">

		<!--自定义样式-->
		<link rel="stylesheet" href="${resource(dir: 'css/login', file: 'login.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css/login', file: 'pure-min.css')}" type="text/css">
		<!--自定义脚本-->
		<g:javascript library="jquery" plugin="jquery"/>
		<g:javascript src="login/my-numeric.js"/>
		<!--JQuery UI-->
		<jqui:resources />
		<!---->
		<script type="text/javascript">
			//改变窗体大小
			function resize(){
				//浏览器时下窗口可视区域高度
				var height = $(window).height();
				//页脚高度
				var footer_height = $(".footer").height()
				//主区域高度
				$(".main").css("min-height", height.sub(footer_height).sub(0).toString() + "px");
			}
			//页面加载
			$(document).ready(function(){
				//改变窗体大小
				resize();
			});
			//改变窗体大小
			$(window).resize(function() {
				resize();
			});
		</script>
		<g:layoutHead/>
	</head>
	<body>
		<div class="main">
			<!-- header -->
			<div class="header">
				<div class="blank_line"></div>
				<div class="global_title">
					<g:message code="global.app.name" default="XTEACH"/>
				</div>
				<div class="blank_line"></div>
			</div>
			<!-- end header -->
			
			<!-- content -->
			<g:layoutBody/>
			<!-- end content -->
		</div>
		<!-- footer -->
		<div class="footer">
			<div class="blank_line"></div>
		</div>
		<!--end footer -->
	</body>
</html>
