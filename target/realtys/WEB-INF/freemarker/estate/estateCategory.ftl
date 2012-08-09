<h2 title="Категория недвижимости - продажа, аренда, ...">Категория недвижимости:</h2>
<ul id="estateCategory">
    <li>
        <input id="addEstateCategory" type="text" url="<@spring.url '/admin/estate/addEstateCategory'/>" style="width: 50px;" /><input type="button" value="Добавить" id="addEstateCategoryBtn"  size="20"/>
    </li>
<#list estateCategoryList as estateCategory>
    <li class="estateCategoryList" id="estateCategory${estateCategory.estateCategoryId}">${estateCategory.name}</li>
</#list>
</ul>
