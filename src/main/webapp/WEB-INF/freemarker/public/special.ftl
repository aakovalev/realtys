<br />
<h2 style="text-align: left;">Специальные предложения</h2>
<ul id="specials" class="jcarousel-skin-tango">
<#list specials as special>
     <li>
         <div>
             <div class="preview" onclick="javascript:window.open('/estate/${special.estateId?string.computer}');"
                  style="cursor: pointer; background: url('<@spring.url '/resources/thumbs/s${special.image}'/>') no-repeat center center;">
             </div>
             <p>
             <#if special.rooms?? && special.rooms = 99>Дом
             <#elseif special.rooms?? && special.rooms = 98>Комната
             <#else>Комнат: ${special.rooms}
             </#if>
             </p>
             <p>Район: ${special.district.name}</p>
             <p>Цена: ${special.price} руб.</p>
             <a href="<@spring.url '/estate/'/>${special.estateId?string.computer}">Посмотреть</a>
         </div>
     </li>
</#list>
</ul>