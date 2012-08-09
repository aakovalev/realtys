<#function catList categories, parentId>
    <#local ans = '<ul>'>

        <#list categories as cat>
            <#if parentId?seq_contains(cat.id)>
            <#local ans = ans + '<li><span>
                <input type="checkbox" name="parentId" checked="checked" value="' + cat.id + '" />' + cat.name + '
                </span>
            '>
            <#else>
            <#local ans = ans + '<li><span>
                <input type="checkbox" name="parentId" value="' + cat.id + '" />' + cat.name + '
                </span>
            '>
            </#if>
            <#local ans = ans + catList(cat.subcategory, parentId)>
            <#local ans = ans + '</li>'>
        </#list>

    <#local ans = ans + '</ul>'>
    <#if ans?matches('<ul></ul>')>
        <#local ans = ''>;
    </#if>
    <#return ans>
</#function>