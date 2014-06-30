<div class="hpFocus">
	<ul class="slides">
		<g:each in="${kvInstanceList}" status="i" var="kvInstance">
		<li>
			<a href="javascript:void(0)" title="" target="_blank">
				<img src="<g:resource dir='images/pinying/images' file='${kvInstance?.ximage}' />" width="764" height="374" alt="" />
			</a>
		</li>
		</g:each>
	</ul>
</div>