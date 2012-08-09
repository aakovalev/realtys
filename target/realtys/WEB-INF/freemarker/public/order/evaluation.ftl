<#include "../common/header.ftl">
<#include "../common/left.ftl">
<#include "../common/right.ftl">
<#include "../common/content.ftl">

<script type="text/javascript">
    $(document).ready(function() {
        $('#uploadify').uploadify({
            'uploader' : "<@spring.url '/resources/'/>js/uploadify/uploadify.swf",
            'script'   :  "<@spring.url '/evaluationOrder/editImages/'/>${orderId?string.computer}",
            'folder'   : "<@spring.url '/resources/'/>thumbs",
            'cancelImg': "<@spring.url '/resources/images/'/>cancel.png",
            'multi'    : true,
            'method'   : 'post'
        });
    });
</script>

<h1>Экспресс оценка недвижимости</h1>

<form action="<@spring.url '/evaluationOrder'/>" method="post" style="margin-left: 5px;" id="order">
    <input type="hidden" name="evaluationOrderId" value="${orderId?string.computer}" /><br />
    <label for="addRooms">Количество комнат:*</label><br/>
    <select id="addRooms" name="rooms">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">больше 4-х</option>
        <option value="0">Дом</option>
    </select><br/>
    <label for="district">Район:*</label><br/>
    <select name="districtId" id="district" class="secondSel">
    <#list districtList as estateDistrict>
        <option value="${estateDistrict.districtId}">${estateDistrict.name}</option>
    </#list>
    </select><br />
    <label for="address">Адрес:*</label><br/>
    <input id="address" name="address" type="text"/><br/>
    <label for="floor">Этаж:</label><br />
    <input id="floor" name="floor" /><br />
    <label for="maxFloor">Этажность:</label><br />
    <input id="maxFloor" name="maxFloor" /><br />
    <label for="text">Описание (наличие техники, мебели, ремонт):</label><br/>
    <textarea id="text" name="text" rows="4" cols="25"></textarea><br/>
    <label for="addEstateCategoryF">Цель оценки:</label><br />
    <select id="addEstateCategoryF" name="estateCategoryId">
    <#list estateCategoryList as estateCategory>
        <option value="${estateCategory.estateCategoryId}">${estateCategory.name}</option>
    </#list>
    </select><br />
    <label for="name">Имя:*</label><br />
    <input id="name" name="name" /><br />
    <label for="phone">Контактный телефон:*</label><br />
    <input id="phone" name="phone" /><br />
    <label for="email">E-mail:*</label><br />
    <input id="email" name="email" /><br />
    <label style="margin-bottom: -10px; display: block;">Чтобы добавить фотографии, нажмите "Select Files" и выберите файлы:</label><br />
    <input id="uploadify" type="file">
    <br />
    <input type="submit" value="Отправить" id="evaluationSubmitOrder" />
    <br />
    <p>
        * - звездочкой помечены поля, обязательные для заполнения.
    </p>
</form>

<#include "../common/footer.ftl">