<#include "../common/header.ftl">
<div id="content">
    <script type="text/javascript" src="<@spring.url '/resources/js/check.js'/>"></script>
    <div class="addUser">
        <h2>Редактировать пользователя</h2>
        <form action="<@spring.url '/admin/users/edit/${user.id}'/>" method="POST" accept-charset="UTF-8">
            <label for="username">Логин:</label><br />
            <input name="username" id="username" onchange="check_name('username')" value="${user.username}" /><br />
            <div id="username_err" class="error" ></div>
            <label for="firstname">Имя:</label><br />
            <input name="firstname" id="firstname" onchange="check_fio('firstname')" value="${user.firstname}" /><br />
            <div id="firstname_err" class="error" ></div>
            <label for="lastname">Фамилия:</label><br />
            <input name="lastname" id="lastname" onchange="check_fio('lastname')" value="${user.lastname}" /><br />
            <div id="lastname_err" class="error" ></div>
            <label for="email">email:</label><br />
            <input name="email" id="email" onchange="check_email('email')" value="${user.email}" /><br />
            <div id="email_err" class="error" ></div>
            <label for="telephone">Телефон:</label><br />
            <input name="telephone" id="telephone" onchange="check_phone('telephone')" value="${user.telephone}" /><br />
            <div id="telephone_err" class="error" ></div>
            <label for="enabled">Включен:</label><input type="checkbox" name="enabled" id="enabled" checked="checked" /><br />
            <label for="admin">Админ:</label><input type="checkbox" name="admin" id="admin" /><br />
            <input type="submit" name="submit" value="Сохранить" />
            <input type="reset" name="reset" value="Очистить" />
        </form>
    </div>
    <div class="clear"></div>
</div>

<#include "../common/footer.ftl">