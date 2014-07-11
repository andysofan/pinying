<div class="hpFocus">
	<ul class="slides">
		<g:each in="${kvInstanceList}" status="i" var="kvInstance">
		<li>
			<g:if test="${kvInstance?.xurl}">
			<a href="${kvInstance?.xurl}" title="">yes
				<img src="<g:resource dir='images/pinying/images' file='${kvInstance?.ximage}' />" width="764" height="374" alt="" />
			</a>
			</g:if>
			<g:else>
				<a href="javascript:void(0)" title="">no
					<img src="<g:resource dir='images/pinying/images' file='${kvInstance?.ximage}' />" width="764" height="374" alt="" />
				</a>
			</g:else>
		</li>
		</g:each>
	</ul>
</div>