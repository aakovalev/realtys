<#include "../common/header.ftl">
<div id="content">
    <div id="userMenu">
        <!--a href="#" id="showClassListDiv" url="<@spring.url '/classes/getList'/>">Список классов</a> &nbsp;-->
    </div>
    <div id="leftContainer">
        <h2>Добавить страницу</h2>
        <form action="<@spring.url '/admin/pages/add'/>" method="post">
            <label>URL:</label><br />
            <input type="text" name="url" /><br />
            <label>Заголовок страницы (Meta title):</label><br />
            <input type="text" name="metaTitle" /><br />
            <label>Описание (Meta description):</label><br />
            <textarea rows="2" cols="50" name="metaDescription"></textarea><br />
            <label>Ключевые слова (Meta keywords):</label><br />
            <input type="text" name="metaKeywords" /><br />
            <label>Название:</label><br />
            <input type="text" name="title" /><br />
            <label>Текст:</label><br />
            <textarea rows="30" cols="50" name="text" class="tinymce"></textarea><br />
            <input type="submit" value="Добавить">
        </form>
    </div>
    <div class="clear"></div>
</div>
<#include "../common/footer.ftl">