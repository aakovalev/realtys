<#include "../common/header.ftl">
<#include "catlist.ftl">
<div id="content">
    <div class="addUser">
        <h2>Редактировать категорию</h2>
        <form action="<@spring.url '/admin/category/edit/${category.id}'/>" method="POST" accept-charset="UTF-8">
            <input type="hidden" name="id" value="${category.id}" />
            <label for="tree">Категория:</label><br />
            <ul id="tree" class="tree">
                <#if parentId == 0>
                <li class="open"><span><input type="radio" name="parentId" checked="checked" value="0" />Главная</span>
                <#else>
                <li class="open"><span><input type="radio" name="parentId" value="0" />Главная</span>                
                </#if>
            ${catList(categoryList, parentId)}
                </li>
            </ul><br />            
            <label for="url">Url:</label><br />
            <input name="url" id="url" value="${category.url}" /><br />
            <label for="name">Имя:</label><br />
            <input name="name" id="name" value="${category.name}" /><br />
            <label for="description">Описание:</label><br />
            <input name="description" id="description" value="${category.description}" /><br />
            <input type="submit" name="submit" value="Сохранить" />
            <input type="reset" name="reset" value="Очистить" />
        </form>
    </div>
    <div class="clear"></div>
</div>

<#include "../common/footer.ftl">