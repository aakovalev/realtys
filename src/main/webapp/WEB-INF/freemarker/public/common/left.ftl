<div id="left">
    <div id="searchTitle">
        <span class="title">Поиск по сайту:</span>
    </div>
    <div id="search">
        <form action="<@spring.url '/search'/>" method="get">
            <fieldset>
                <select name="categoryId" class="firstSel">
                    <option value="0">Все</option>
                <#list estateCategoryList as estateCategory>
                    <#assign selectedAttribute = "">
                    <#if categoryId?? && categoryId = estateCategory.estateCategoryId>
                        <#assign selectedAttribute="selected='selected'"/>
                    </#if>
                    <option value="${estateCategory.estateCategoryId}" ${selectedAttribute}>
                        ${estateCategory.name}
                    </option>
                </#list>
                </select>
                <br/>
                <select id="typeId" name="typeId" class="firstSel">
                    <option value="0">Все</option>
                <#list estateTypeList as estateType>
                    <#assign selectedAttribute = "">
                    <#if typeId?? && typeId = estateType.estateTypeId>
                        <#assign selectedAttribute="selected='selected'"/>
                    </#if>
                    <option value="${estateType.estateTypeId}" ${selectedAttribute}>
                        ${estateType.name}
                    </option>
                </#list>
                </select>
                <br/>
                <nobr>
                    <label for="rooms">Комнат: </label>
                    <select id="rooms" name="rooms" <#if typeId?? && typeId != 1>disabled="disabled"</#if>  >
                        <#assign selectedAttribute = "">
                        <option value="" <#if rooms?? && rooms = 0>selected='selected'</#if> >Не важно</option>
                        <option value="1" <#if rooms?? && rooms = 1>selected='selected'</#if> >1</option>
                        <option value="2" <#if rooms?? && rooms = 2>selected='selected'</#if>>2</option>
                        <option value="3" <#if rooms?? && rooms = 3>selected='selected'</#if> >3</option>
                        <option value="4" <#if rooms?? && rooms = 4>selected='selected'</#if> >4</option>
                        <option value="5" <#if rooms?? && rooms = 5>selected='selected'</#if> >&gt; 4</option>
                        <option value="98" <#if rooms?? && rooms = 98>selected='selected'</#if> >Комната</option>
                        <option value="99" <#if rooms?? && rooms = 99>selected='selected'</#if> >Дом</option>
                    </select>
                </nobr>
                <br/>
                <label for="district">Район: </label>
                <select name="districtId" id="district" class="secondSel">
                    <option value="0">Все</option>
                <#list districtList as estateDistrict>
                    <#assign selectedAttribute = "">
                    <#if districtId?? && districtId = estateDistrict.districtId>
                        <#assign selectedAttribute="selected='selected'"/>
                    </#if>
                    <option value="${estateDistrict.districtId}" ${selectedAttribute}>
                        ${estateDistrict.name}
                    </option>
                </#list>
                </select>
                <span class="title">Стоимость:</span>
                <br/>
                <nobr><label for="minPrice">От: </label>
                    <input name="minPrice" id="minPrice" value="<#if minPrice?? && (minPrice > 0)>${minPrice}</#if>"/></nobr>
                <br/>
                <nobr><label for="maxPrice">До: </label>
                    <input name="maxPrice" id="maxPrice" value="<#if maxPrice?? && (maxPrice > 0)>${maxPrice}</#if>"/></nobr>
                <br/>
                <input name="order" type="hidden" value="2"/>
                <input name="orderBy" type="hidden" value="date"/>
                <div id="findDiv">
                    <div id="findLeft"></div>
                    <div id="findRight"></div>
                    <div id="findBtn">Найти</div>
                </div>
            </fieldset>
        </form>
    </div>
    <div id="support">
        <span class="title">Поддержка клиентов:</span>
        <br/>
        <ul>
            <li><a href="<@spring.url '/page/offer.html'/>">Образцы договоров</a></li>
            <li><a href="<@spring.url '/page/price.html'/>">Расценки на услуги</a></li>
            <li><a href="<@spring.url '/page/tax.html'/>">Налоговые вычеты</a></li>
            <li><a href="<@spring.url '/page/complaints.html'/>">Жалобы и предложения</a></li>
            <li><a href="<@spring.url '/page/rieltors.html'/>">Выбрать риэлтора</a></li>
            <li><a href="<@spring.url '/category/articles.html'/>">Все статьи</a></li>
        </ul>
    </div>
</div>