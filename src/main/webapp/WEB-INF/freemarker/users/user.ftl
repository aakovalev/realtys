<#include "../common/header.ftl">
<div id="content">
    <div id="search">
        <!--
        <form action="<@spring.url '/users/search/'/>" method="GET" accept-charset="UTF-8">
            <label for="last">Фамилия: </label>
            <input type="text" name="lastname" id="last" />
            <input type="submit" name="search" value="Search" />
            <a>Расширенный поиск</a>
            <div id="additionalSearch">
                <label for="login2">Логин: </label>
                <input type="text" name="username" id="login2" />
                <label for="firstname2">Имя: </label>
                <input type="text" name="firstname" id="firstname2" />
                <input type="hidden" name="useAdditional" id="useAdditional" value="0" />
            </div>
        </form-->
    </div>
    <div class="userList">
        <h2>Список пользователей:</h2>
        <table rules="ALL">
            <tr>
                <td>№</td>
                <td>ID</td>
                <td>Логин</td>
                <td>Включен</td>
                <td>Админ</td>
                <td>Имя</td>
                <td>Фамилия</td>
                <td>Email</td>
                <td>Телефон</td>
                <td colspan="2"></td>
            </tr>
        <#list userList as user>
            <tr>
                <td>${user_index + 1}</td>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>
                    <#if user.enabled><span class="yes">Да</span>
                    <#else><span class="no">Нет</span>
                    </#if>
                </td>
                <td>
                    <#if user.admin><span class="yes">Да</span>
                    <#else><span class="no">Нет</span>
                    </#if>
                </td>
                <td>${user.firstname}</td>
                <td>${user.lastname}</td>
                <td>${user.email}</td>
                <td>${user.telephone}</td>
                <td>
                    <a title="Edit #${user.id}" href="<@spring.url '/admin/users/edit/${user.id}'/>"><img src="<@spring.url '/resources/images/edit.png'/>" alt="Edit" /></a>&nbsp;
                    <a title="Delete #${user.id}" href="<@spring.url '/admin/users/delete/${user.id}'/>"><img src="<@spring.url '/resources/images/delete.png'/>" alt="Delete" /></a>
                </td>
            </tr>
        </#list>
        </table>
    </div>
    <div id="leftContainer">
        <script type="text/javascript" src="<@spring.url '/resources/js/check.js'/>"></script>
        <div class="addUser" id="addUserDiv">
            <h2>Добавить:</h2>
            <form action="<@spring.url '/admin/users/add'/>" method="POST" accept-charset="UTF-8" class="addUserForm">
                <label for="username">Логин:</label><br />
                <input name="username" id="username" onchange="check_name('username')" /><br />
                <div id="username_err" class="error" ></div>
                <label for="password">Пароль:</label><br />
                <input name="password" id="password" onchange="check_pass('password')" /><br />
                <div id="password_err" class="error" ></div>
                <label for="firstname">Имя:</label><br />
                <input name="firstname" id="firstname" onchange="check_fio('firstname')" /><br />
                <div id="firstname_err" class="error" ></div>
                <label for="lastname">Фамилия:</label><br />
                <input name="lastname" id="lastname" onchange="check_fio('lastname')" /><br />
                <div id="lastname_err" class="error" ></div>
                <label for="email">email:</label><br />
                <input name="email" id="email" onchange="check_email('email')" /><br />
                <div id="email_err" class="error" ></div>
                <label for="telephone">Телефон:</label><br />
                <input name="telephone" id="telephone" onchange="check_phone('telephone')" /><br />
                <div id="telephone_err" class="error" ></div>
                <label for="enabled">Включен:</label><input type="checkbox" name="enabled" id="enabled" checked="checked" /><br />
                <label for="admin">Админ:</label><input type="checkbox" name="admin" id="admin" /><br />
                <input type="submit" name="submit" value="Добавить" />
                <input type="reset" name="reset" value="Очистить" />
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>

<#include "../common/footer.ftl">
