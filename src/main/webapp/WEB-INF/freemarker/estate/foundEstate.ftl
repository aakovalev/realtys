<#ftl encoding="UTF-8"><#import "/spring.ftl" as spring />
<table id="foundEstateListTable" rules="all">
    <tr>
        <td>
            Код
        </td>
        <td>
            Дата
        </td>
        <td width="290">
            Адрес
        </td>
        <td>
            Площадь
        </td>
        <td width="20">
            Этаж
        </td>
        <td>
            Цена
        </td>
        <td></td>
        <td>C Фото</td>
    </tr>
    <#assign cssClass="class='noimage'">
    <#if foundEstate.image?? && foundEstate.image!="noimage.jpg" && ((foundEstate.image?trim)?length > 0)>
        <#assign cssClass="">
    </#if>
    <tr ${cssClass}>
        <td>
        ${foundEstate.code}
        </td>
        <td>
        ${foundEstate.date}
        </td>
        <td>
        ${foundEstate.address}
        </td>
        <td>
        ${foundEstate.square}
        </td>
        <td>
        ${foundEstate.floor} / ${foundEstate.maxFloor}
        </td>
        <td>
        ${foundEstate.price}
        </td>
        <td>
            <a title="Редактировать #${foundEstate.estateId}" href="<@spring.url '/admin/estate/editImages/${foundEstate.estateId?string.computer}'/>"><img
                    src="<@spring.url '/resources/images/home.png'/>" alt="Загрузить картинки"/></a>&nbsp;
            <a title="Редактировать #${foundEstate.estateId}" href="<@spring.url '/admin/estate/edit/${foundEstate.estateId?string.computer}'/>"><img
                    src="<@spring.url '/resources/images/edit.png'/>" alt="Редактировать"/></a>&nbsp;
            <a title="Удалить #${foundEstate.estateId}" href="<@spring.url '/admin/estate/delete/${foundEstate.estateId?string.computer}'/>"><img
                    src="<@spring.url '/resources/images/delete.png'/>" alt="Удалить"/></a>
        </td>
        <td>
            <#if foundEstate.withPhoto?? && foundEstate.withPhoto>
                <img src="<@spring.url '/resources/images/ok.png'/>" />
            </#if>
        </td>
    </tr>
</table>
