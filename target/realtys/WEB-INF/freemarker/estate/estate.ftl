<#include "../common/header.ftl">
<div id="content">
    <div id="userMenu">
        <!--a href="#" id="showClassListDiv" url="<@spring.url '/classes/getList'/>">Список классов</a> &nbsp;-->
    </div>
    <div id="search">
        <h2>Найти объявление по коду:</h2>
        <form id="searchForm" method="POST" action="<@spring.url '/admin/estate'/>">
            <label for="code">Код:</label><br />
            <input id="code" type="text" name="code">
            <input type="submit" value="Искать"><br />
        </form>
    </div>
     <div class="userList">
         <#include "estateCategory.ftl">
         <#include "estateType.ftl">
         <#include "district.ftl">
     </div>
     <div id="leftContainer">
         <#if foundEstate??>
            <h2>Результаты поиска:</h2>
            <div id="foundEstates">
                <#include "foundEstate.ftl">
            </div>
         </#if>
         <h2>Список объявлений:</h2>
         <div id="addEstate">
            <a href="#" id="addEstateBtn"><img src="<@spring.url '/resources/images/add.png'/>" />Добавить</a>
             <form id="addEstateForm" action="<@spring.url '/admin/estate/addEstate'/>" method="POST" enctype="multipart/form-data">
                 <label for="addCode">Код:</label><br />
                 <input id="addCode" type="text" name="code"><br />
                 <label for="addDate">Дата:</label><br />
                 <input id="addDate" type="text" name="dateStamp"><br />
                 <label for="addAdress">Адрес:</label><br />
                 <input id="addAdress" type="text" name="address"><br />
                 <label for="addSquare">Площадь:</label><br />
                 <input id="addSquare" type="text" name="square"><br />
                 <label for="addFloor">Этаж:</label><br />
                 <input id="addFloor" type="text" name="floor"><br />
                 <label for="addMaxFloor">Всего этажей:</label><br />
                 <input id="addMaxFloor" type="text" name="maxFloor"><br />
                 <label for="addPrice">Цена:</label><br />
                 <input id="addPrice" type="text" name="price"><br />
                 <label for="addRooms">Количество комнат:</label><br />
                 <select id="addRooms" name="rooms">
                     <option value="0">-</option>
                     <option value="1" selected="true">1</option>
                     <option value="2">2</option>
                     <option value="3">3</option>
                     <option value="4">4</option>
                     <option value="5">больше 4-х</option>
                     <option value="98">Комната</option>
                     <option value="99">Дом</option>
                 </select>
                 <br />
                 <label>Спецпредложение:</label><br />
                 <input id="addSpecial" type="radio" name="special" value="1"><label for="addSpecial">Да</label><br />
                 <input id="addSpecial2" type="radio" checked="checked" name="special" value="0"><label for="addSpecial2">Нет</label><br />
                 <label for="addEstateCategory">Категория недвижимости:</label><br />
                 <select id="addEstateCategory" name="estateCategoryId">
                     <#list estateCategoryList as estateCategory>
                         <option value="${estateCategory.estateCategoryId}">${estateCategory.name}</option>
                     </#list>
                 </select>
                 <br />
                 <label for="addEstateType">Тип недвижимости:</label><br />
                 <select id="addEstateType" name="estateTypeId">
                     <#list estateTypeList as estateType>
                         <option value="${estateType.estateTypeId}">${estateType.name}</option>
                     </#list>
                 </select>
                 <br />
                 <label for="addEstateDistrict">Район:</label><br />
                 <select id="addEstateDistrict" name="districtId">
                     <#list districtList as estateDistrict>
                         <option value="${estateDistrict.districtId}">${estateDistrict.name}</option>
                     </#list>
                 </select>
                 <br />
                 <label for="addText">Описание:</label><br />
                 <textarea class="tinymce" id="addText" name="description"></textarea><br />
                 <input type="button" value="Добавить" id="addEstateSubmit" /><input type="button" value="Закрыть" id="closeEstateSubmit" />
             </form>
         </div>
         <#if estateList?size == 0>
             <span id="emptyEstateList">Список объявлений пуст.</span>
         <#else>
            <div id="estateListDiv">
            <#include "estateList.ftl">
            </div>
         </#if>
     </div>
     <div class="clear"></div>
</div>
<#include "../common/footer.ftl">