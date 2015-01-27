<#include "common/header.ftl">
<#include "common/left.ftl">
<#include "common/right.ftl">
<#include "common/content.ftl">
<#include "special.ftl">
     <div id="leftContainer">
         <h2>Список объявлений:</h2>
         <#if estateList?size == 0>
             <span id="emptyEstateList">Не найдено ни одного объявления соответствующего параметрам поиска</span>
         <#else>
            <div id="estateListDiv">
            <#include "../estate/estatePublicList.ftl">
            </div>
         </#if>
     </div>
     <div class="clear"></div>
<#include "common/footer.ftl">