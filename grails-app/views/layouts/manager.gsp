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
  		<asset:stylesheet src="application.css"/>
		<asset:javascript src="application.js"/>

		<asset:stylesheet src="manager/custom.css"/>
		<asset:stylesheet src="manager/purecss/base-min.css"/>
		<asset:stylesheet src="manager/purecss/menus-min.css"/>
		<asset:stylesheet src="manager/purecss/menus-nr-min.css"/>

		<asset:javascript src="manager/my-numeric.js"/>
		<!--JQuery UI-->
		<jqui:resources />
		<!--jquery block ui-->
		<asset:javascript src="manager/jquery-blockui/JQueryBlockUI.js"/>
		<asset:javascript src="manager/jquery-blockui/jquery.blockUI.dialog.js"/>
		<!---->
		<script type="text/javascript">

			function resize(){

				var height = $(window).height();

				var footer_height = $(".footer").height()

				$(".main").css("min-height", height.sub(footer_height).sub(0).toString() + "px");
			}

			$(document).ready(function(){

				var url = "${createLink(controller: 'auth', action : 'signOut')}";
				$("a[href='"+url+"']").attr("href", "javascript:void(0)").click(function(){
					$.confirm({
						message: "Confirm",
						okText: "Confirm",
						cancelText: "Cancel",
						css: {
							"background-color": "white",
							"color": "red"
						},
						onOk: function () {
							window.location.href = "${createLink(controller: 'auth', action : 'signOut')}"
						},
                        onCancel: function () {
                            $.unblockUI();
                        }
					});
				});

				resize();
			});

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
				<div class="global_title"><g:message code="global.app.name" default="PinYing"/></div>
				<div class="blank_line"></div>
			</div>
			<!-- end header -->
			
			<shiro:isLoggedIn>
			<!-- menu -->
			<div id="mainMenu" class="pure-menu pure-menu-open pure-menu-horizontal">
				<nav:menu class="" custom="true" scope="${menuScope}">
					<li class="${item.data.icon ? item.data.icon : ''}">
						<p:callTag tag="g:link" attrs="${linkArgs + [class:active ? 'pure-menu-selected' : '']}">
							<nav:title item="${item}"/>
						</p:callTag>
						<g:if test="${item.children}">
							<nav:menu scope="${item.id}" custom="true" class="">
								<li class="${item.data.icon ? 'i_'+item.data.icon : ''}">
									<p:callTag tag="g:link" attrs="${linkArgs}">
										<nav:title item="${item}"/>
									</p:callTag>
								</li>
							</nav:menu>
						</g:if>
					</li>
				</nav:menu>
				<div class="blank_line"></div>
			</div>
			<!-- endmenu -->
			</shiro:isLoggedIn>

			<!-- content -->
			<g:layoutBody/>
			<!-- end content -->
		</div>
		<!-- footer -->
		<div class="footer">
			<div class="blank_line"></div>
		</div>
	</body>
</html>
