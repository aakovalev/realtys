<#include "../common/header.ftl">
<#include "catlist.ftl">
<div id="content">

    <div class="userList">
        <h2>Список публикаций:</h2>
        <table rules="ALL">
            <tr>
                <td>№</td>
                <td>ID</td>
                <td>URL</td>
                <td>Заголовок</td>
                <td colspan="2"></td>
            </tr>
        <#list publicationList as publication>
            <tr>
                <td>${publication_index + 1}</td>
                <td>${publication.id}</td>
                <td>${publication.url}</td>
                <td>${publication.title}</td>
                <td>
                    <a title="Edit #${publication.id}" href="<@spring.url '/admin/publications/edit/${publication.id?string.computer}'/>"><img src="<@spring.url '/resources/images/edit.png'/>" alt="Edit" /></a>&nbsp;
                    <a title="Delete #${publication.id}" href="<@spring.url '/admin/publications/delete/${publication.id?string.computer}'/>"><img src="<@spring.url '/resources/images/delete.png'/>" alt="Delete" /></a>
                </td>
            </tr>
        </#list>
        </table>
    </div>
    <script type="text/javascript" src="<@spring.url '/resources/js/check.js'/>"></script>
    <div class="addUser">
        <h2>Добавить публикацию</h2>
        <form action="<@spring.url '/admin/publications/add'/>" method="POST" accept-charset="UTF-8">
            <label for="tree">Категория:</label><br />
            <ul id="tree" class="tree">
                <li class="open"><span>Главная</span>
            ${catList(categoryList, parentId)}
                </li>
            </ul><br />
            <label for="url">URL:</label><br />
            <input name="url" id="url" onchange="check_name('url')" /><br />
            <div id="url_err" class="error" ></div>
            <label for="title">Заголовок:</label><br />
            <input name="title" id="title" onchange="check_fio('title')" /><br />
            <div id="title_err" class="error" ></div>
            <label for="anons">Анонс:</label><br />
            <textarea rows="3" id="anons" name="anons" cols="70"></textarea><br />
            <label for="text">Текст:</label><br />
            <textarea rows="10" id="text" name="text" cols="70" class="tinymce"></textarea><br />
            <input type="submit" name="submit" value="Добавить" />
            <input type="reset" name="reset" value="Очистить" />
        </form>
    </div>
    <div class="clear"></div>
    
</div>
<#include "../common/footer.ftl">