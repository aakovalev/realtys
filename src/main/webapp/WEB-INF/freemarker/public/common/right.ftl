            <div id="right">
                <table cellpadding="10">
                    <tr>
                        <td>
                            <img src="<@spring.url '/resources/images/orderArenda.jpg'/>" alt="" />
                        </td>
                        <td><a href="<@spring.url '/rentOrder'/>">Заявка на аренду квартиры</a></td>
                    </tr>
                    <tr>
                        <td>
                            <img src="<@spring.url '/resources/images/orderSdacha.jpg'/>" alt="" />
                        </td>
                        <td><a href="<@spring.url '/deliveryOrder'/>">Заявка на сдачу квартиры</a></td>
                    </tr>
                    <tr>
                        <td>
                            <img src="<@spring.url '/resources/images/orderWhy.jpg'/>" alt="" />
                        </td>
                        <td><a href="<@spring.url '/page/who.html'/>">Почему именно "Пара"</a></td>
                    </tr>
                    <tr>
                        <td>
                            <img src="<@spring.url '/resources/images/orderExpress.jpg'/>" alt="" />
                        </td>
                        <td><a href="<@spring.url '/evaluationOrder'/>">Экспресс оценка недвижимости</a></td>
                    </tr>
                    <!--tr>
                        <td>
                            <img src="<@spring.url '/resources/images/orderOnline.jpg'/>" alt="" />
                        </td>
                        <td><a href="<@spring.url '/page/online.html'/>">Online консультация</a></td>
                    </tr-->
                    <tr>
                        <td></td>
                        <td>
                            <!-- Put this div tag to the place, where the Like block will be -->
                            <p>
                                <div id="vk_like"></div>
                                <script type="text/javascript">
                                VK.Widgets.Like("vk_like", {type: "button"});
                                </script>
                            </p>

                            <p>
                                <div id="fb-root"></div>
                                <script>(function(d, s, id) {
                                  var js, fjs = d.getElementsByTagName(s)[0];
                                  if (d.getElementById(id)) return;
                                  js = d.createElement(s); js.id = id;
                                  js.src = "//connect.facebook.net/ru_RU/all.js#xfbml=1";
                                  fjs.parentNode.insertBefore(js, fjs);
                                }(document, 'script', 'facebook-jssdk'));</script>
                                <div class="fb-like" data-href="http://parasamara.ru/" data-send="false" data-layout="button_count" data-width="200" data-show-faces="true"></div>
                            </p>

                            <p>
                                <!-- VK Widget -->
                                <div id="vk_groups"></div>
                                <script type="text/javascript">
                                VK.Widgets.Group("vk_groups", {mode: 0, width: "200", height: "290"}, 9928958);
                                </script>
                            </p>
                        </td>
                    </tr>
                </table>
            </div>