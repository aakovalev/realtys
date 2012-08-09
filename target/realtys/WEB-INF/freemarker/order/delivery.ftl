<#include "../common/header.ftl">
<div id="content">
    <div id="userMenu">
    </div>
    <div id="search">
    </div>
     <div class="userList">
     </div>
     <div id="leftContainer">
         <h2>Заявка № ${delivery.deliveryOrderId}:</h2>
         <table cellpadding="5">
             <tr>
                 <td align="right">
                     Дата:
                 </td>
                 <td>
                     ${delivery.date}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Кол-во комнат:
                 </td>
                 <td>
                     ${delivery.rooms}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Район:
                 </td>
                 <td>
                     ${delivery.district.name}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Адрес:
                 </td>
                 <td>
                     ${delivery.address}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Этаж:
                 </td>
                 <td>
                     ${delivery.floor}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Этажность:
                 </td>
                 <td>
                     ${delivery.maxFloor}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Описание:
                 </td>
                 <td>
                     ${delivery.text}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Цена:
                 </td>
                 <td>
                     ${delivery.price}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Срок аренды (мес):
                 </td>
                 <td>
                     ${delivery.period}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Имя:
                 </td>
                 <td>
                     ${delivery.name}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Email:
                 </td>
                 <td>
                     ${delivery.email}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Телефон:
                 </td>
                 <td>
                      ${delivery.phone}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Картинки:
                 </td>
                 <td>
                      <#list delivery.images as image>
                          <a rel="example_group" href="<@spring.url '/resources/thumbs/${image.image}'/>" target="_blank">${image.image}</a></a>
                      </#list>
                 </td>
             </tr>
         </table>
         <a href="<@spring.url '/admin/deliveryOrders/delete/'/>${delivery.deliveryOrderId?string.computer}">Удалить</a>
     </div>
     <div class="clear"></div>
</div>
<#include "../common/footer.ftl">