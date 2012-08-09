<#include "common/header.ftl">
<#include "common/left.ftl">
<#include "common/right.ftl">
<#include "common/content.ftl">

<br />
<table width="100%">
    <tr>
        <td>
            <div class="title" style="font-size: 17px; font-weight: bold; margin-top: 2px;">
                ${estate.estateCategory.name}: ${estate.address}. (код: ${estate.code})
            </div>
        </td>
    </tr>
    <tr>
		<td valign="top" align="top">
			<table style="float: left;">
				<tr>
			        <td valign="top">
			            <!--img src="<@spring.url '/resources/${estate.image}'/>" alt="Preview"-->
			            <div id="gallery" class="ad-gallery">
			              <div class="ad-image-wrapper" style="margin-bottom: 25px;">
			              </div>
			              <div class="ad-controls">
			                  <a style="position: relative;  left: 70px; top: -10px;  " rel="example_group"
			                     href="<@spring.url '/resources/thumbs/${estate.image}'/>"><!-- ссылка на первую картинку -->
			                   Увеличить</a>
			              </div>
			              <div class="ad-nav">
			                  <div class="ad-thumbs">
			                      <ul class="ad-thumb-list">
			                          <li>
			                              <a href="<@spring.url '/resources/thumbs/${estate.image}'/>">
			                                  <img src="<@spring.url '/resources/thumbs/s${estate.image}'/>"
			                                       title="" longdesc="" alt="" width="90" height="90" class="image0">
			                              </a>
			                          </li>
			                      <#list estate.images as estateImage>
			                          <li>
			                              <a href="<@spring.url '/resources/thumbs/${estateImage.image}'/>">
			                                  <img src="<@spring.url '/resources/thumbs/s${estateImage.image}'/>"
			                                       title="" longdesc=""  width="90" height="90" class="image1">
			                              </a>
			                          </li>
			                      </#list>
			                      </ul>
			                  </div>
			                  <div style="display: none; ">
			                      <#list estate.images as estateImage>
			                      <a rel="example_group" href="<@spring.url '/resources/thumbs/${estateImage.image}'/>">
			                          <!-- ссылка на вторую картинку -->
			                          <img src="<@spring.url '/resources/thumbs/s${estateImage.image}'/>"  width="90" height="90" title="" longdesc="" class="image1">
			                          <!-- миниатюра второй картинки -->    </a>
			                        </#list>
			                  </div>
			              </div>
			            </div>
			        </td>
					<td width="20" valign="top"></td>
				</tr>
			</table>
            <span class="priceLabel">Цена:</span><br />
            <span class="price title">${estate.price} руб.</span><br />
            <span><a href="#">Получить ипотеку</a> </span><br />
            <span class="priceLabel">Местоположение:</span><br />
            <span>Адрес: ${estate.address}</span><br />
            <span>Район: ${estate.district.name}</span><br />
            <span class="priceLabel">Характеристики:</span><br />
            <span>Этаж: ${estate.floor} этаж ${estate.maxFloor} этажного дома</span><br />
            <span>Площадь: ${estate.square} кв. м.</span><br />
            <span><#if (estate.estateType.estateTypeId == 2 && estate.rooms == 0)>
                    -
                <#elseif (estate.rooms = 0 || estate.rooms = 98)>
                    Комната
                <#elseif (estate.rooms = 99)>
                    Дом
                <#else>
                    Комнат: ${estate.rooms}
                </#if> </span><br />
            <span>
                <h2>Описание:</h2>
                ${estate.benefits}
            </span>
        </td>
    </tr>
</table>
<script type="text/javascript">
    document.title = "${estate.estateCategory.name}: ${estate.address}. (код: ${estate.code})";
</script>
<#include "common/footer.ftl">