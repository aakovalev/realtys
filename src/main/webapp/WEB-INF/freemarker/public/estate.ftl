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
            </span><br/>
        </td>
    </tr>
</table>
<div id="map" style="width:480px; height:300px; border: 1px solid #bcb9b4;"></div>
<script type="text/javascript">
    document.title = "${estate.estateCategory.name}: ${estate.address}. (код: ${estate.code})";
</script>

<script type="text/javascript">
    ymaps.ready(init);
    var myMap;

    function showOnMap(address, district, image, rooms, price, link) {
        var addressContainDelimiters = address.indexOf("/") >= 0 || address.indexOf("\\") >= 0;
        if (addressContainDelimiters) {
            geocodeComplexAddress(address, district, image, rooms, price, link);
        }
        else {
            geocodeSimpleAddress(address, district, image, rooms, price, link);
        }
    }

    function geocodeComplexAddress(address) {
        var delimiter = "/";

        if (address.indexOf("/") == -1) {
            delimiter = "\\";
        }

        var firstStreet = address.substring(0, address.indexOf(delimiter));
        var secondStreet = address.substring(address.indexOf(delimiter) + 1, address.length);

        if (hasNumbers(firstStreet)) {
            geocodeSimpleAddress(firstStreet);
        }
        else {
            // do crazy stuff: calculating intersection of streets via yandex routing facility
            // might not produce the results we want probably...
            var coordinates;
            ymaps.route(['Самара, ' + firstStreet, 'Самара, ' + secondStreet]).then(function (route) {
                for (var i = 0; i < route.getPaths().getLength(); i++) {
                    way = route.getPaths().get(i);
                    segments = way.getSegments();
                    var previousStreet = "";
                    for (var j = 0; j < segments.length; j++) {
                        var street = segments[j].getStreet();
                        if (previousStreet) {
                            // только что "повернули" значит начало сегмента есть пересечение улиц
                            if (previousStreet != street) {
                                var points = way.geometry;
                                coordinates = points.get(segments[j].getPolylineStartIndex());
                            }
                        }
                        previousStreet = street;
                    }
                }
                if (coordinates) {
                    putOnMap(coordinates, address);
                }
            });
        }
    }

    function geocodeSimpleAddress(address) {
        ymaps.geocode('Самара, ' + address, { results: 1 }).then(function (res) {
            var foundLocation = res.geoObjects.get(0);
            putOnMap(foundLocation.geometry.getCoordinates(), address);
        }, handleError);
    }

    function putOnMap(coordinates, address, district, image, rooms, price, link) {
        var placeMark = new ymaps.Placemark(coordinates)
        myMap.geoObjects.add(placeMark);
        adjustMapToLocation(coordinates);
    }

    function adjustMapToLocation(location) {
        myMap.setCenter(location);
        myMap.setZoom(16);
        myMap.containter.fitToViewport();
    }

    function init() {
        ymaps.geocode('Самара', { results: 1 }).then(function (res) {
            // Выбираем первый результат геокодирования.
            var samaraCity = res.geoObjects.get(0);
            // Создаем карту с нужным центром.
            myMap = new ymaps.Map("map", {
                center: samaraCity.geometry.getCoordinates(),
                zoom: 11
            });

            myMap.controls
                // Кнопка изменения масштаба.
                .add('zoomControl', { left: 5, top: 5 })
                // Список типов карты
                .add('typeSelector')
                // Кнопка изменения масштаба - компактный вариант.
                // Расположим её справа.
                .add('smallZoomControl', { right: 5, top: 75 })
                // Стандартный набор кнопок
                .add('mapTools', { left: 35, top: 5 });

            myMap.container.fitToViewport();

            showOnMap('${estate.address}');
        }, handleError);
    }

    function hasNumbers(str) {
        return /\d/.test(str)
    }

    function handleError() {
        // do nothing
    }
</script>
<#include "common/footer.ftl">