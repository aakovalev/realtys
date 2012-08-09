<#include "../common/header.ftl">
<div id="content">
    <div id="userMenu">
        <!--a href="#" id="showClassListDiv" url="<@spring.url '/classes/getList'/>">Список классов</a> &nbsp;-->
    </div>
    <div id="search">
    </div>
     <div class="userList">
     </div>
     <div id="leftContainer">
         <h2>Список заявок:</h2>
         <h3>Аренда:</h3>
         <table rules="all" style="border: 1px solid #d5d5d5;" cellpadding="5">
             <tr>
                 <td>Дата:</td>
                 <td>Имя:</td>
                 <td>Телефон:</td>
                 <td></td>
             </tr>
             <#list rentOrders as rent>
             <tr>
                 <td>
                     ${rent.date}
                 </td>
                 <td>
                     ${rent.name}
                 </td>
                 <td>
                     ${rent.phone}
                 </td>
                 <td><a href="<@spring.url '/admin/rentOrders/'/>${rent.rentOrderId?string.computer}">Посмотреть</a></td>
             </tr>
             </#list>
         </table>
         <h3>Сдача:</h3>
         <table rules="all" style="border: 1px solid #d5d5d5;" cellpadding="5">
             <tr>
                 <td>Дата:</td>
                 <td>Имя:</td>
                 <td>Телефон:</td>
                 <td></td>
             </tr>
             <#list deliveryOrders as rent>
             <tr>
                 <td>
                     ${rent.date}
                 </td>
                 <td>
                     ${rent.name}
                 </td>
                 <td>
                     ${rent.phone}
                 </td>
                 <td><a href="<@spring.url '/admin/deliveryOrders/'/>${rent.deliveryOrderId?string.computer}">Посмотреть</a></td>
             </tr>
             </#list>
         </table>
         <h3>Оценка:</h3>
         <table rules="all" style="border: 1px solid #d5d5d5;" cellpadding="5">
             <tr>
                 <td>Дата:</td>
                 <td>Имя:</td>
                 <td>Телефон:</td>
                 <td></td>
             </tr>
             <#list evaluationOrders as rent>
             <tr>
                 <td>
                     ${rent.date}
                 </td>
                 <td>
                     ${rent.name}
                 </td>
                 <td>
                     ${rent.phone}
                 </td>
                 <td><a href="<@spring.url '/admin/evaluationOrders/'/>${rent.evaluationOrderId?string.computer}">Посмотреть</a></td>
             </tr>
             </#list>
         </table>
     </div>
     <div class="clear"></div>
</div>
<#include "../common/footer.ftl">