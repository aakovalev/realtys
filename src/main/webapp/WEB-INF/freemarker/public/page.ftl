<#include "common/header.ftl">
<#include "common/left.ftl">
<#include "common/right.ftl">
<#include "common/content.ftl">
<h1>${title}</h1>
${content}
<br />
<#if url == "feedback">
    <#include "feedback.ftl">
</#if>
<#if url == "complaints">
    <#include "complaints.ftl">
</#if>
<#include "common/footer.ftl">