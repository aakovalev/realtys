<h2 title="Районы города">Районы города:</h2>
<ul id="district">
    <li>
        <input id="addDistrict" type="text" url="<@spring.url '/admin/estate/addDistrict'/>" style="width: 50px;" /><input type="button" value="Добавить" id="addDistrictBtn" size="20"/>
    </li>
<#list districtList as district>
    <li class="districtList" id="district${district.districtId}">${district.name}</li>
</#list>
</ul>