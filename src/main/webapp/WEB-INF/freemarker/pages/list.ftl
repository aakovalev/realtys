<#include "../common/header.ftl">
<div id="content">
    <div id="userMenu">
        <!--a href="#" id="showClassListDiv" url="<@spring.url '/classes/getList'/>">Список классов</a> &nbsp;-->
    </div>
    <div id="leftContainer">
        <a href="<@spring.url '/admin/pages/add'/>">Добавить</a><br />
        <table cellpadding="7" style="border: 1px solid #eee;" rules="all">
            <tr>
                <td>ID</td>
                <td>Url</td>
                <td style="min-width: 250px;">Заголовок</td>
                <td></td>
            </tr>
            <#list pages as page>
            <tr>
                <td>${page.pageId}</td>
                <td>${page.url}</td>
                <td>${page.title}</td>
                <td>
                    <a title="Редактировать #${page.pageId}" href="<@spring.url '/admin/pages/edit/${page.pageId?string.computer}'/>"><img
                            src="<@spring.url '/resources/images/edit.png'/>" alt="Edit"/></a>&nbsp;
                    <a title="Удалить #${page.pageId}" href="<@spring.url '/admin/pages/delete/${page.pageId?string.computer}'/>"><img
                            src="<@spring.url '/resources/images/delete.png'/>" alt="Delete"/></a>
                </td>
            </tr>
            </#list>
        </table>
    </div>
    <div class="clear"></div>
</div>
<#include "../common/footer.ftl">