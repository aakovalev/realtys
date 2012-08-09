<#include "../common/header.ftl">
<div id="content">
     <div class="userList">
     </div>
     <div id="leftContainer">
         <h2>Редактировать объявление:</h2>
         <div id="addEstate">
             <form id="editEstateForm" action="<@spring.url '/admin/estate/edit/'/>${estate.estateId?c}" method="POST" enctype="multipart/form-data">
                 <label for="addCode">Код:</label><br />
                 <input id="addCode" type="text" name="code" value="${estate.code}"><br />
                 <label for="addDate">Дата:</label><br />
                 <input id="addDate" type="text" name="dateStamp" value="${date}"><br />
                 <label for="addAdress">Адрес:</label><br />
                 <input id="addAdress" type="text" name="address" value="${estate.address}"><br />
                 <label for="addSquare">Площадь:</label><br />
                 <input id="addSquare" type="text" name="square" value="${estate.square}"><br />
                 <label for="addFloor">Этаж:</label><br />
                 <input id="addFloor" type="text" name="floor" value="${estate.floor}"><br />
                 <label for="addMaxFloor">Всего этажей:</label><br />
                 <input id="addMaxFloor" type="text" name="maxFloor" value="${estate.maxFloor}"><br />
                 <label for="addPrice">Цена:</label><br />
                 <input id="addPrice" type="text" name="priceStr" value="${estate.price}"><br />
                 <label for="addRooms">Количество комнат:</label><br />
                 <select id="addRooms" name="rooms">
                     <#if estate.rooms == 0>
                     <option value="0" selected="selected">-</option>
                     <#else>
                     <option value="0">-</option>
                     </#if>
                     <#if estate.rooms == 1>
                     <option value="1" selected="selected">1</option>
                     <#else >
                     <option value="1">1</option>
                     </#if>
                     <#if estate.rooms == 2>
                     <option value="2" selected="selected">2</option>
                     <#else >
                     <option value="2">2</option>
                     </#if>
                     <#if estate.rooms == 3>
                     <option value="3" selected="selected">3</option>
                     <#else >
                     <option value="3">3</option>
                     </#if>
                     <#if estate.rooms == 4>
                     <option value="4" selected="selected">4</option>
                     <#else >
                     <option value="4">4</option>
                     </#if>
                     <#if estate.rooms == 5>
                     <option value="5" selected="selected">больше 4-х</option>
                     <#else >
                     <option value="5">больше 4-х</option>
                     </#if>
                     <#if estate.rooms == 98>
                     <option value="98" selected="selected">Комната</option>
                     <#else >
                     <option value="98">Комната</option>
                     </#if>
                     <#if estate.rooms == 99>
                     <option value="99" selected="selected">Дом</option>
                     <#else >
                     <option value="99">Дом</option>
                     </#if>
                 </select><br />
                 <label for="addSpecial">Спецпредложение:</label><br />
                 <#if estate.special>
                 <input id="addSpecial" type="radio" checked="checked" name="special" value="1"><label for="addSpecial">Да</label><br />
                 <input id="addSpecial2" type="radio" name="special" value="0"><label for="addSpecial2">Нет</label><br />
                 <#else >
                 <input id="addSpecial" type="radio" name="special" value="1"><label for="addSpecial">Да</label><br />
                 <input id="addSpecial2" type="radio" checked="checked" name="special" value="0"><label for="addSpecial2">Нет</label><br />
                 </#if>
                 <label for="addEstateCategoryF">Катгория недвижимости:</label><br />
                 <select id="addEstateCategoryF" name="estateCategoryId">
                     <#list estateCategoryList as estateCategory>
                        <#if estateCategory.estateCategoryId == estate.estateCategory.estateCategoryId>
                            <option value="${estateCategory.estateCategoryId}" selected="selected">${estateCategory.name}</option>
                        <#else >
                            <option value="${estateCategory.estateCategoryId}">${estateCategory.name}</option>
                        </#if>
                     </#list>
                 </select>
                 <br />
                 <label for="addEstateTypeF">Тип недвижимости:</label><br />
                 <select id="addEstateTypeF" name="estateTypeId">
                     <#list estateTypeList as estateType>
                        <#if estateType.estateTypeId == estate.estateType.estateTypeId>
                            <option value="${estateType.estateTypeId}" selected="selected">${estateType.name}</option>
                        <#else >
                            <option value="${estateType.estateTypeId}">${estateType.name}</option>
                        </#if>
                     </#list>
                 </select><br />
                 <label for="addEstateDistrictF">Район:</label><br />
                 <select id="addEstateDistrictF" name="districtId">
                     <#list districtList as estateDistrict>
                        <#if estateDistrict.districtId == estate.district.districtId>
                            <option value="${estateDistrict.districtId}" selected="selected">${estateDistrict.name}</option>
                        <#else >
                            <option value="${estateDistrict.districtId}">${estateDistrict.name}</option>
                        </#if>
                     </#list>
                 </select>
                 <br />
                 <label for="addBenefits">Описание:</label><br />
                 <textarea class="tinymce" id="addBenefits" cols="30" name="benefits">${estate.benefits}</textarea><br />
                 <input type="submit" value="Сохранить" />
             </form>
         </div>
     </div>
     <div class="clear"></div>
</div>
<#include "../common/footer.ftl">