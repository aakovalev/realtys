<#include "../common/header.ftl">
<#include "catlist.ftl">
<#function catListLinks categories, parentId, path>
    <#local ans = '<ul>'>

        <#list categories as cat>
            <#local ans = ans + '<li><span>
                <input type="radio" name="parentId" value="' + cat.id + '" />' + cat.name + '

            '+ '<a title="Edit #${cat.id}" href="${path}/admin/category/edit/${cat.id}/${parentId}">
                                <img src="../resources/images/edit.png" alt="Edit" /></a>&nbsp;
                    <a title="Delete #${cat.id}" href="${path}/admin/category/delete/${cat.id}">
                        <img src="../resources/images/delete.png" alt="Delete" /></a></span>'>
            <#local ans = ans + catListLinks(cat.subcategory, cat.id, path)>
            <#local ans = ans + '</li>'>
        </#list>

    <#local ans = ans + '</ul>'>
    <#if ans?matches('<ul></ul>')>
        <#local ans = ''>;
    </#if>
    <#return ans>
</#function>
<div id="content">
    <div class="userList">
        <h2>Список категорий:</h2>
            <ul class="tree">
                <li class="open"><span><input type="radio" name="parentId" value="0" />Главная</span>
            ${catListLinks(categoryList, parentId, path)}
                </li>
            </ul><br />
    </div>
    <div class="addUser">
        <h2>Добавить категорию</h2>
        <form action="<@spring.url '/admin/category/add'/>" method="POST" accept-charset="UTF-8">
            <label for="tree">Категория:</label><br />
            <ul id="tree" class="tree">
                <li class="open"><span><input type="radio" name="parentId" value="0" checked="checked" />Главная</span>
            ${catList(categoryList, parentId)}
                </li>
            </ul><br />
            <label for="url">Url:</label><br />
            <input name="url" id="url" /><br />
            <label for="name">Имя:</label><br />
            <input name="name" id="name" /><br />
            <label for="description">Описание:</label><br />
            <input name="description" id="description" /><br />
            <input type="submit" name="submit" value="Добавить" />
            <input type="reset" name="reset" value="Очистить" />
        </form>
    </div>
    <div class="clear"></div>
</div>

<#include "../common/footer.ftl">
