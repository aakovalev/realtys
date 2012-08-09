<#include "common/header.ftl">
<#include "common/left.ftl">
<#include "common/right.ftl">
<#include "common/content.ftl">
<ul id="publicationsList">
    <#list publications as publication >
    <li>
        <h3>${publication.title}</h3>
        <div class="anons">
            ${publication.anons}
        </div>
        <a href="<@spring.url '/publication/'/>${publication.url}.html">Читать полностью</a>
    </li>
    </#list>
</ul>
<#include "common/footer.ftl">