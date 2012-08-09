<#include "../common/header.ftl">
<div id="content">
    <div id="userMenu">
        <!--a href="#" id="showClassListDiv" url="<@spring.url '/classes/getList'/>">Список классов</a> &nbsp;-->
    </div>
    <div id="leftContainer">
        <h2>Редактировать страницу</h2>
        <form action="<@spring.url '/admin/pages/edit/'/>${page.pageId}" method="post">
            <label>URL:</label><br />
            <input type="text" name="url" value="${page.url}" /><br />
            <label>Заголовок страницы (Meta title):</label><br />
            <textarea rows="2" cols="30"name="metaTitle">"${page.metaTitle}"</textarea><br />
            <label>Описание (Meta description):</label><br />
            <textarea rows="2" cols="50" name="metaDescription">${page.metaDescription}</textarea><br />
            <label>Ключевые слова (Meta keywords):</label><br />
            <input type="text" name="metaKeywords" value="${page.metaKeywords}" /><br />
            <label>Название:</label><br />
            <input type="text" name="title" value="${page.title}" /><br />
            <label>Текст:</label><br />
            <textarea rows="30" cols="50" name="text" class="tinymce">${page.text}</textarea><br />
            <input type="submit" value="Добавить">
        </form>
    </div>
    <div class="clear"></div>
</div>
<#include "../common/footer.ftl">