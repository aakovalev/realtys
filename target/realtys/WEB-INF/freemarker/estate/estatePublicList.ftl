<table id="estateListTable" border="0" cellpadding="5">
    <tr>
        <td>
            Код
        </td>
        <td style="border-right: none; padding-right: 0; margin-right: 1px;">
            Дата
        </td>
        <td style="border-left: none; ; padding: 0; margin: 0;">
            <nobr>
            <a href="<@spring.url '/search'/>?catehoryId=${categoryId?string.computer}&typeId=${typeId?string.computer}&districtId=${districtId?string.computer}&minPrice=${minPrice?string.computer}&maxPrice=${maxPrice?string.computer}&rooms=${rooms?string.computer}&order=2&orderBy=date" title="По убыванию">
                <img src="<@spring.url '/resources/images/arrowDown.png'/>" /></a>
            <a href="<@spring.url '/search'/>?catehoryId=${categoryId?string.computer}&typeId=${typeId?string.computer}&districtId=${districtId?string.computer}&minPrice=${minPrice?string.computer}&maxPrice=${maxPrice?string.computer}&rooms=${rooms?string.computer}&order=1&orderBy=date" title="По возрастанию">
                <img src="<@spring.url '/resources/images/arrowUp.png'/>" /></a>
            </nobr>
        </td>
        <td>
            Адрес
        </td>
        <td width="30" style="border-right: none; padding-right: 0; margin-right: 1px;">
            Комнат
        </td>
        <td style="border-left: none; ; padding: 0; margin: 0;">
            <nobr>
            <a href="<@spring.url '/search'/>?catehoryId=${categoryId?string.computer}&typeId=${typeId?string.computer}&districtId=${districtId?string.computer}&minPrice=${minPrice?string.computer}&maxPrice=${maxPrice?string.computer}&rooms=${rooms?string.computer}&order=2&orderBy=rooms" title="По убыванию">
                <img src="<@spring.url '/resources/images/arrowDown.png'/>" /></a>
            <a href="<@spring.url '/search'/>?catehoryId=${categoryId?string.computer}&typeId=${typeId?string.computer}&districtId=${districtId?string.computer}&minPrice=${minPrice?string.computer}&maxPrice=${maxPrice?string.computer}&rooms=${rooms?string.computer}&order=1&orderBy=rooms" title="По возрастанию">
                <img src="<@spring.url '/resources/images/arrowUp.png'/>" /></a>
            </nobr>
        </td>
        <td width="30" style="border-right: none; padding-right: 0; margin-right: 1px;">
            Пл.
        </td>
        <td style="border-left: none; ; padding: 0; margin: 0;">
            <nobr>
            <a href="<@spring.url '/search'/>?catehoryId=${categoryId?string.computer}&typeId=${typeId?string.computer}&districtId=${districtId?string.computer}&minPrice=${minPrice?string.computer}&maxPrice=${maxPrice?string.computer}&rooms=${rooms?string.computer}&order=2&orderBy=date" title="По убыванию">
                <img src="<@spring.url '/resources/images/arrowDown.png'/>" /></a>
            <a href="<@spring.url '/search'/>?catehoryId=${categoryId?string.computer}&typeId=${typeId?string.computer}&districtId=${districtId?string.computer}&minPrice=${minPrice?string.computer}&maxPrice=${maxPrice?string.computer}&rooms=${rooms?string.computer}&order=1&orderBy=date" title="По возрастанию">
                <img src="<@spring.url '/resources/images/arrowUp.png'/>" /></a>
            </nobr>
        </td>
        <td style="border-right: none; padding-right: 0; margin-right: 1px;">
            Этаж
        </td>
        <td style="border-left: none; ; padding: 0; margin: 0;">
            <nobr>
            <a href="<@spring.url '/search'/>?catehoryId=${categoryId?string.computer}&typeId=${typeId?string.computer}&districtId=${districtId?string.computer}&minPrice=${minPrice?string.computer}&maxPrice=${maxPrice?string.computer}&rooms=${rooms?string.computer}&order=2&orderBy=floor" title="По убыванию">
                <img src="<@spring.url '/resources/images/arrowDown.png'/>" /></a>
            <a href="<@spring.url '/search'/>?catehoryId=${categoryId?string.computer}&typeId=${typeId?string.computer}&districtId=${districtId?string.computer}&minPrice=${minPrice?string.computer}&maxPrice=${maxPrice?string.computer}&rooms=${rooms?string.computer}&order=1&orderBy=floor" title="По возрастанию">
                <img src="<@spring.url '/resources/images/arrowUp.png'/>" /></a>
            </nobr>
        </td>
        <td style="border-right: none; padding-right: 0; margin-right: 1px;">
            Цена
        </td>
        <td style="border-left: none; ; padding: 0; margin: 0;">
            <nobr>
            <a href="<@spring.url '/search'/>?catehoryId=${categoryId?string.computer}&typeId=${typeId?string.computer}&districtId=${districtId?string.computer}&minPrice=${minPrice?string.computer}&maxPrice=${maxPrice?string.computer}&rooms=${rooms?string.computer}&order=2&orderBy=price" title="По убыванию">
                <img src="<@spring.url '/resources/images/arrowDown.png'/>" /></a>
            <a href="<@spring.url '/search'/>?catehoryId=${categoryId?string.computer}&typeId=${typeId?string.computer}&districtId=${districtId?string.computer}&minPrice=${minPrice?string.computer}&maxPrice=${maxPrice?string.computer}&rooms=${rooms?string.computer}&order=1&orderBy=price" title="По возрастанию">
                <img src="<@spring.url '/resources/images/arrowUp.png'/>" /></a>
            </nobr>
        </td>
        <td>
            Фото
        </td>
    </tr>
<#list estateList as estate>
    <tr>
        <td>
            <a href="<@spring.url '/estate/'/>${estate.estateId?string.computer}">
            ${estate.code}
            </a>
        </td>
        <td colspan="2">
            <a href="<@spring.url '/estate/'/>${estate.estateId?string.computer}">
            ${estate.date?string("dd.MM.yyyy")}
            </a>
        </td>
        <td><div style="width: 100px; word-wrap: break-word;">
            <a href="<@spring.url '/estate/'/>${estate.estateId?string.computer}">
            ${estate.address}
            </a></div>
        </td>
        <td colspan="2">
            <#if (estate.estateType.estateTypeId == 2 && estate.rooms == 0)>
                -
            <#else>
                <a href="<@spring.url '/estate/'/>${estate.estateId?string.computer}">
                <#if (estate.rooms = 0 || estate.rooms = 98)>
                <!--
                    0 и 98 специальные значения, которые обозначают что предлагается комната, а не квартира
                    (выглядит странно - нужно будет пофиксить)
                -->
                    ком.
                <#elseif (estate.rooms == 99)>
                    дом
                <#else>
                    ${estate.rooms}
                </#if>
                </a>
            </#if>
        </td>
        <td colspan="2">
            <a href="<@spring.url '/estate/'/>${estate.estateId?string.computer}">
            ${estate.square}
            </a>
        </td>
        <td colspan="2">
            <a href="<@spring.url '/estate/'/>${estate.estateId?string.computer}">
            ${estate.floor}/${estate.maxFloor}
            </a>
        </td>
        <td colspan="2">
            <a href="<@spring.url '/estate/'/>${estate.estateId?string.computer}">
            ${estate.price}
            </a>
        </td>
        <td>
            <#assign estateImageName = 'noimage.jpg'/>
            <#if estate.image?? && (estate.image?trim?length > 0)>
                <#assign estateImageName = estate.image/>
            </#if>
            <img style="border:1px solid #bcb9b4;"
                 src="<@spring.url '/resources/thumbs/s${estateImageName}'/>" width="60" height="60"/>
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
    <a href="<@spring.url '/search'/>?catehoryId=${categoryId?string.computer}&typeId=${typeId?string.computer}&districtId=${districtId?string.computer}&minPrice=${minPrice?string.computer}&maxPrice=${maxPrice?string.computer}&rooms=${rooms?string.computer}&order=${order}&orderBy=${orderBy}&pageId=${i}">${pageNumber}</a>&nbsp;&nbsp;
    </#if>
</#list>
</div>