<#include "../common/header.ftl">
<#include "../common/left.ftl">
<#include "../common/right.ftl">
<#include "../common/content.ftl">

<script type="text/javascript">
    $(document).ready(function() {
        $('#uploadify').uploadify({
            'uploader' : "<@spring.url '/resources/'/>js/uploadify/uploadify.swf",
            'script'   :  "<@spring.url '/deliveryOrder/editImages/'/>${orderId?string.computer}",
            'folder'   : "<@spring.url '/resources/'/>thumbs",
            'cancelImg': "<@spring.url '/resources/images/'/>cancel.png",
            'multi'    : true,
            'method'   : 'post'
        });
    });
</script>

<script type="text/javascript">
    function checkNumberFields(element, evnt, message) {
        var str = $(element).val(new_str);
        var new_str = s = "";
        var validation_lbl_id = (element.id + "_validation_lbl");
        var warn_lbl = document.getElementById(validation_lbl_id);
        warn_lbl.innerText = "";

        for(var i=0; i < str.length; i++){

            s = str.substr(i,1);

            if(s!=" " && isNaN(s) == false){
                new_str += s;
            }
            else {
                warn_lbl.innerText = message;
            }
        }

        $(element).val(new_str);
    }
</script>

<h1>Заявка на сдачу квартиры</h1>

<form action="<@spring.url '/deliveryOrder'/>" method="post" style="margin-left: 5px;" id="order" id="deliveryForm">
    <input type="hidden" name="deliveryOrderId" value="${orderId?string.computer}" /><br />
    <label for="addRooms">Количество комнат *:</label><br/>
    <select id="addRooms" name="rooms">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">больше 4-х</option>
        <option value="0">Дом</option>
    </select><br/>
    <label for="district">Район:</label><br/>
    <select name="districtId" id="district" class="secondSel">
    <#list districtList as estateDistrict>
        <option value="${estateDistrict.districtId}">${estateDistrict.name}</option>
    </#list>
    </select><br />
    <label for="address">Адрес:</label><br/>
    <input id="address" name="address" type="text"/><br/>

    <label for="floor">Этаж (только цифры):</label><br />
    <input id="floor" name="floor"
        onblur="checkNumberFields(this, event, ' этаж, только цифры');"
        onkeyup="checkNumberFields(this, event, ' этаж, только цифры');"
        onchange="checkNumberFields(this, event, ' этаж, только цифры');"
        onkeypress="checkNumberFields(this, event, ' этаж, только цифры');">
    <span id="floor_validation_lbl" style="color: red; font-size: 9pt;"></span><br />

    <label for="maxFloor">Этажность (только цифры):</label><br />
    <input id="maxFloor" name="maxFloor"
        onblur="checkNumberFields(this, event, ' этажность, только цифры');"
        onkeyup="checkNumberFields(this, event, ' этажность, только цифры');"
        onchange="checkNumberFields(this, event, ' этажность, только цифры');"
        onkeypress="checkNumberFields(this, event, ' этажность, только цифры');">
    <span id="maxFloor_validation_lbl" style="color: red; font-size: 9pt;"></span><br />

    <label for="text">Описание (наличие техники, мебели, ремонт):</label><br/>
    <textarea id="text" name="text" rows="4" cols="25"></textarea><br/>

    <label for="price">Цена (только цифры):</label><br />
    <input id="price" name="price"
        onblur="checkNumberFields(this, event, ' цена, только цифры');"
        onkeyup="checkNumberFields(this, event, ' цена, только цифры');"
        onchange="checkNumberFields(this, event, ' цена, только цифры');"
        onkeypress="checkNumberFields(this, event, ' цена, только цифры');">
    <span id="price_validation_lbl" style="color: red; font-size: 9pt;"></span><br />

    <label for="period">Срок аренды (в месяцах, только цифры):</label><br />
    <input id="period" name="period"
        onblur="checkNumberFields(this, event, ' срок аренды в месяцах, только цифры');"
        onkeyup="checkNumberFields(this, event, ' срок аренды в месяцах, только цифры');"
        onchange="checkNumberFields(this, event, ' срок аренды в месяцах, только цифры');"
        onkeypress="checkNumberFields(this, event, ' срок аренды в месяцах, только цифры');">
    <span id="period_validation_lbl" style="color: red; font-size: 9pt;"></span><br />

    <label for="name">Имя:*</label><br />
    <input id="name" name="name" /><br />
    <label for="phone">Контактный телефон:*</label><br />
    <input id="phone" name="phone" /><br />
    <label for="email">E-mail:</label><br />
    <input id="email" name="email" /><br />
    <label style="margin-bottom: -10px; display: block;">Чтобы добавить фотографии, нажмите "Select Files" и выберите файлы:</label><br />
    <input id="uploadify" type="file">
    <br />
    <input type="button" id="deliverySubmitOrder" value="Отправить" />
    <br />
    <p>
        * - звездочкой помечены поля, обязательные для заполнения.
    </p>
</form>


<#include "../common/footer.ftl">