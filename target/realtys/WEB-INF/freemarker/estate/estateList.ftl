<#ftl encoding="UTF-8"><#import "/spring.ftl" as spring />
<table id="estateListTable" rules="all">
    <tr>
        <td>
            Код
        </td>
        <td style="border-right: none; padding-right: 0; margin-right: 1px;">
            Дата
        </td>
        <td style="border-left: none; ; padding: 0; margin: 0;">
            <nobr>
            <a href="<@spring.url '/admin/estate'/>?order=2&orderBy=date" title="По убыванию">
                <img src="<@spring.url '/resources/images/arrowDown.png'/>" /></a>
            <a href="<@spring.url '/admin/estate'/>?order=1&orderBy=date" title="По возрастанию">
                <img src="<@spring.url '/resources/images/arrowUp.png'/>" /></a>
            </nobr>
        </td>
        <td width="290">
            Адрес
        </td>
        <td width="30" style="border-right: none; padding-right: 0; margin-right: 1px;">
            Площадь
        </td>
        <td style="border-left: none; ; padding: 0; margin: 0;">
            <nobr>
            <a href="<@spring.url '/admin/estate'/>?order=2&orderBy=square" title="По убыванию">
                <img src="<@spring.url '/resources/images/arrowDown.png'/>" /></a>
            <a href="<@spring.url '/admin/estate'/>?order=1&orderBy=square" title="По возрастанию">
                <img src="<@spring.url '/resources/images/arrowUp.png'/>" /></a>
            </nobr>
        </td>
        <td width="20" style="border-right: none; padding-right: 0; margin-right: 1px;">
            Этаж
        </td>
        <td style="border-left: none; ; padding: 0; margin: 0;">
            <nobr>
            <a href="<@spring.url '/admin/estate'/>?order=2&orderBy=floor" title="По убыванию">
                <img src="<@spring.url '/resources/images/arrowDown.png'/>" /></a>
            <a href="<@spring.url '/admin/estate'/>?order=1&orderBy=floor" title="По возрастанию">
                <img src="<@spring.url '/resources/images/arrowUp.png'/>" /></a>
            </nobr>
        </td>
        <td style="border-right: none; padding-right: 0; margin-right: 1px;">
            Цена
        </td>
        <td style="border-left: none; ; padding: 0; margin: 0;">
            <nobr>
            <a href="<@spring.url '/admin/estate'/>?order=2&orderBy=price" title="По убыванию">
                <img src="<@spring.url '/resources/images/arrowDown.png'/>" /></a>
            <a href="<@spring.url '/admin/estate'/>?order=1&orderBy=price" title="По возрастанию">
                <img src="<@spring.url '/resources/images/arrowUp.png'/>" /></a>
            </nobr>
        </td>
        <td></td>
        <td>С Фото</td>
    </tr>
<#list estateList as estate>
    <#assign cssClass="class='noimage'">
    <#if estate.image?? && estate.image!="noimage.jpg" && ((estate.image?trim)?length > 0)>
        <#assign cssClass="">
    </#if>
    <tr ${cssClass}>
        <td>
        ${estate.code}
        </td>
        <td colspan="2">
        ${estate.date}
        </td>
        <td>
        ${estate.address}
        </td>
        <td colspan="2">
        ${estate.square}
        </td>
        <td colspan="2">
        ${estate.floor} / ${estate.maxFloor}
        </td>
        <td colspan="2">
        ${estate.price}
        </td>
        <td>
            <a title="Редактировать #${estate.estateId}" href="<@spring.url '/admin/estate/editImages/${estate.estateId?string.computer}'/>"><img
                    src="<@spring.url '/resources/images/home.png'/>" alt="Загрузить картинки"/></a>&nbsp;
            <a title="Редактировать #${estate.estateId}" href="<@spring.url '/admin/estate/edit/${estate.estateId?string.computer}'/>"><img
                    src="<@spring.url '/resources/images/edit.png'/>" alt="Редактировать"/></a>&nbsp;
            <a title="Удалить #${estate.estateId}" href="<@spring.url '/admin/estate/delete/${estate.estateId?string.computer}'/>"><img
                    src="<@spring.url '/resources/images/delete.png'/>" alt="Удалить"/></a>
        </td>
        <td>
            <#if estate.withPhoto?? && estate.withPhoto>
                <img src="<@spring.url '/resources/images/ok.png'/>" />
            </#if>
        </td>
    </tr>
</#list>
</table>
<div id="estatePages">
Страницы
<#list 0..pageCount as i>
    <#assign pageNumber = i + 1>
    <#if pageId = i>
    ${pageNumber}&nbsp;&nbsp;
    <#else >
    <a href="<@spring.url '/admin/estate'/>?pageId=${i}&order=${order}&orderBy=${orderBy}">${pageNumber}</a>&nbsp;&nbsp;
    </#if>
</#list>
</div>