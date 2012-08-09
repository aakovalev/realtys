<#include "../common/header.ftl">
<div id="content">
    <div id="userMenu">
    </div>
    <div id="search">
    </div>
     <div class="userList">
     </div>
     <div id="leftContainer">
         <h2>Заявка № ${rent.rentOrderId}:</h2>
         <table cellpadding="5">
             <tr>
                 <td align="right">
                     Дата:
                 </td>
                 <td>
                     ${rent.date}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Кол-во комнат:
                 </td>
                 <td>
                     ${rent.rooms}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Район:
                 </td>
                 <td>
                     ${rent.district.name}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Стоимость от:
                 </td>
                 <td>
                     ${rent.minPrice}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Стоимость до:
                 </td>
                 <td>
                     ${rent.maxPrice}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Требования:
                 </td>
                 <td>
                     ${rent.text}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Срок аренды (мес):
                 </td>
                 <td>
                     ${rent.period}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Кто будет проживать:
                 </td>
                 <td>
                     ${rent.who}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Имя:
                 </td>
                 <td>
                     ${rent.name}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Email:
                 </td>
                 <td>
                     ${rent.email}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Телефон:
                 </td>
                 <td>
                      ${rent.phone}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Картинки:
                 </td>
                 <td>
                      <#list rent.images as image>
                          <a rel="example_group" href="<@spring.url '/resources/thumbs/${image.image}'/>" target="_blank">${image.image}</a></a>
                      </#list>
                 </td>
             </tr>
         </table>
         <a href="<@spring.url '/admin/rentOrders/delete/'/>${rent.rentOrderId?string.computer}">Удалить</a>
     </div>
     <div class="clear"></div>
</div>
<#include "../common/footer.ftl">