<#include "../common/header.ftl">
<div id="content">
    <div id="userMenu">
    </div>
    <div id="search">
    </div>
     <div class="userList">
     </div>
     <div id="leftContainer">
         <h2>Заявка № ${evaluation.evaluationOrderId}:</h2>
         <table cellpadding="5">
             <tr>
                 <td align="right">
                     Дата:
                 </td>
                 <td>
                     ${evaluation.date}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Кол-во комнат:
                 </td>
                 <td>
                     ${evaluation.rooms}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Район:
                 </td>
                 <td>
                     ${evaluation.district.name}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Адрес:
                 </td>
                 <td>
                     ${evaluation.address}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Этаж:
                 </td>
                 <td>
                     ${evaluation.floor}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Этажность:
                 </td>
                 <td>
                     ${evaluation.maxFloor}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Описание, наличие техники,<br /> мебели, ремонт:
                 </td>
                 <td>
                     ${evaluation.text}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Цель оценки:
                 </td>
                 <td>
                     ${evaluation.estateCategory.name}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Имя:
                 </td>
                 <td>
                     ${evaluation.name}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Email:
                 </td>
                 <td>
                     ${evaluation.email}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Телефон:
                 </td>
                 <td>
                      ${evaluation.phone}
                 </td>
             </tr>
             <tr>
                 <td align="right">
                     Картинки:
                 </td>
                 <td>
                      <#list evaluation.images as image>
                          <a rel="example_group" href="<@spring.url '/resources/thumbs/${image.image}'/>" target="_blank">${image.image}</a></a>
                      </#list>
                 </td>
             </tr>
         </table>
         <a href="<@spring.url '/admin/evaluationOrders/delete/'/>${evaluation.evaluationOrderId?string.computer}">Удалить</a>
     </div>
     <div class="clear"></div>
</div>
<#include "../common/footer.ftl">