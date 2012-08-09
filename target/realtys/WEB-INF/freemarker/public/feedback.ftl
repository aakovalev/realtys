<#ftl encoding="UTF-8"><#import "/spring.ftl" as spring />
<form action="<@spring.url '/feedback'/>" method="post">
    <label for="name">Представьтесь:*</label><br />
    <input id="name" name="name" /><br />
    <label for="email">E-mail:*</label><br />
    <input id="email" name="email" /><br />
    <label for="phone">Ваш телефон:</label><br />
    <input id="phone" name="phone" /><br />
    <label for="text">Сообщение:*</label><br />
    <textarea rows="5" cols="40" id="text" name="text"></textarea><br />
    <input type="submit" id="sendFeedback" value="Отправить" />
    <br />
    <p>
        * - звездочкой помечены поля, обязательные для заполнения.
    </p>
</form>