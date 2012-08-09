<h2 title="Тип недвижимости - жилая, коммерческая ...">Тип недвижимости:</h2>
<ul id="estateType">
    <li>
        <input id="addEstateType" type="text" url="<@spring.url '/admin/estate/addEstateType'/>" style="width: 50px;" /><input type="button" value="Добавить" id="addEstateTypeBtn" />
    </li>
<#list estateTypeList as estateType>
    <li class="estateTypeList" id="estateType${estateType.estateTypeId}">${estateType.name}</li>
</#list>
</ul>
