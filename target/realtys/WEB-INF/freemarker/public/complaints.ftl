<#ftl encoding="UTF-8"><#import "/spring.ftl" as spring />
<form action="<@spring.url '/complaints'/>" method="post">
    <#assign name = ""/>
    <#assign email = ""/>
    <#assign phone = ""/>
    <#assign text = ""/>
    <#if feedback??>
         <#assign name = feedback.name/>
         <#assign email = feedback.email/>
         <#assign phone = feedback.phone/>
         <#assign text = feedback.text/>
    </#if>

    <label for="name">Представьтесь:*</label><br />
    <input id="name" name="name" value="${name!}"/><br />
    <label for="email">E-mail:*</label><br />
    <input id="email" name="email" value="${email!}"/><br />
    <label for="phone">Ваш телефон:</label><br />
    <input id="phone" name="phone" value="${phone!}"/><br />
    <label for="text">Сообщение:*</label><br />
    <textarea rows="5" cols="40" id="text" name="text">${text!}</textarea><br />
    <#if captchaPassed?? && captchaPassed == false>
        <label for="text" style="color: red" >Неправильный проверочный код, введите проверочный код еще раз:</label><br />
    <#else>
        <label for="text">Введите проверочный код:*</label><br />
    </#if>
    <img src="<@spring.url '/jcaptcha.jpg'/>"  style="border:1px solid #bcb9b4; margin-left: 2px;"/></br >
    <input type="text" name="jcaptcha" value="" /><br />
    <input type="submit" id="sendFeedback" value="Отправить" />
</form>
<br />
<p>
    * - звездочкой помечены поля, обязательные для заполнения.
</p>

<#if complaintList??>
    <table id="estateListTable" border="0" cellpadding="7" cellspacing="7">
    <#list complaintList as complaint>
            <tr>
                <td>${complaint.dateTime!"Ранее"}, <span style="color: #0f7dac"><b>${complaint.name}</b></span>:&nbsp;${complaint.text}</td>
            </tr>
    </#list>
    </table>
</#if>
