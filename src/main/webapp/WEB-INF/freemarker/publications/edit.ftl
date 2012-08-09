<#include "../common/header.ftl">
<#include "catlist.ftl">
<div id="content">
    <div class="addUser">
        <h2>Редактировать публикацию</h2>
        <form action="<@spring.url '/admin/publications/edit/${publication.id}'/>" method="POST" accept-charset="UTF-8">
            <input type="hidden" name="id" value="${publication.id}" />
            <label for="tree">Категория:</label><br />
            <ul id="tree" class="tree">
                <li class="open"><span>Главная</span>
            ${catList(categoryList, parentId)}
                </li>
            </ul><br />
            <label for="url">Url:</label><br />
            <input name="url" id="url" value="${publication.url}" /><br />
            <label for="title">Имя:</label><br />
            <input name="title" id="title" value="${publication.title}" /><br />
            <label for="anons">Анонс:</label><br />
            <textarea rows="3" name="anons" id="anons" cols="70">${publication.anons}</textarea><br />
            <label for="text">Текст:</label><br />
            <textarea rows="3" name="text" id="text" cols="70" class="tinymce">${publication.text}</textarea><br />
            <input type="submit" name="submit" value="Сохранить" />
            <input type="reset" name="reset" value="Очистить" />
        </form>
    </div>
    <div class="clear"></div>
</div>

<#include "../common/footer.ftl">