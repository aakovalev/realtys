            <div id="content">
                <div id="menuDiv">
                    <div id="menuLeft"></div>
                    <div id="menuRight"></div>

                    <ul id="menu" class="topnav">
                        <li class="no-style"><a href="<@spring.url '/search?categoryId=1&typeId=0&rooms=&districtId=0&minPrice=&maxPrice=&order=2&orderBy=date'/>">Аренда<span></span></a>
                            <ul class="subnav">
                                <li><a href="<@spring.url '/search?categoryId=1&typeId=1&rooms=&districtId=0&minPrice=&maxPrice=&order=2&orderBy=date'/>">Жилая</a></li>
                                <li><a href="<@spring.url '/search?categoryId=1&typeId=2&rooms=&districtId=0&minPrice=&maxPrice=&order=2&orderBy=date'/>">Коммерческая</a></li>
                                <li><a href="<@spring.url '/search?categoryId=1&typeId=3&rooms=&districtId=0&minPrice=&maxPrice=&order=2&orderBy=date'/>"><nobr>На сутки</nobr></a></li>
                            </ul>
                        </li>
                        <li><a href="/search?categoryId=2&typeId=0&rooms=&districtId=0&minPrice=&maxPrice=">Продажа<span></span></a>
                            <ul class="subnav">
                                <li><a href="<@spring.url '/search?categoryId=2&typeId=1&rooms=&districtId=0&minPrice=&maxPrice=&order=2&orderBy=date'/>">Жилая</a></li>
                                <li><a href="<@spring.url '/search?categoryId=2&typeId=2&rooms=&districtId=0&minPrice=&maxPrice=&order=2&orderBy=date'/>">Коммерческая</a></li>
                            </ul>
                        </li>
                        <li><a href="<@spring.url '/category/services.html'/>">Наши услуги<span></span></a>
                            <ul class="subnav">
                                <#if services??>
                                    <#list services as publication >
                                        <li>
                                            <a href="<@spring.url '/publication/'/>${publication.url}.html"><nobr>${publication.title}</nobr></a>
                                        </li>
                                    </#list>
                                </#if>
                            </ul>
                        </li>
                        <li><a href="<@spring.url '/page/garant.html'/>">Гарантия</a></li>
                        <li><a href="<@spring.url '/page/feedback.html'/>">Обратная связь</a></li>
                    </ul>
                </div>
