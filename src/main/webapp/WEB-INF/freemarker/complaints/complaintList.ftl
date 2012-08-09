<#include "../common/header.ftl">
<div id="content">
<h2>Список жалоб и предложений:</h2>
<#if complaintList??>
    <table id="complaintListTable" rules="all">
        <tr>
            <td>Дата/Время</td>
            <td>Имя Пользователя</td>
            <td>Телефон</td>
            <td>E-mail</td>
            <td>Текст Жалобы/Предложения</td>
            <td>&nbsp;</td>
        </tr>
    <#list complaintList as complaint>
        <tr>
            <td>${complaint.dateTime!"Ранее"}</td>
            <td>${complaint.name}</td>
            <td>${complaint.phone!"Не указан"}</td>
            <td>${complaint.email}</td>
            <td>${complaint.text}</td>
            <td>
               <a title="Удалить жалобу/предложение #${complaint.feedbackId}" href="<@spring.url '/admin/complaints/delete/${complaint.feedbackId}'/>"><img src="<@spring.url '/resources/images/delete.png'/>" alt="Delete" /></a>
            </td>
        </tr>
    </#list>
    </table>
</#if>

</div>
<#include "../common/footer.ftl">
