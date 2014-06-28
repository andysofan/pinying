<g:each in="${firstLevelCategoryInstanceList}" status="i" var="categoryInstance">
<li class="navi02 navi0">
    <a href="${createLink(action:'category', id:categoryInstance?.id)}" title="" class="i1">${categoryInstance?.name}</a>
    <div class="naviHover" style="display:none;">
    	<g:include action="menuSecondLevelCategory" params="[categoryId:categoryInstance?.id?:-1]"/>
    </div>
</li>
</g:each>