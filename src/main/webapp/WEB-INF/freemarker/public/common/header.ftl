<#ftl encoding="UTF-8"><#import "/spring.ftl" as spring /><?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="ru" xml:lang="ru">
<head>
	<title>${metaTitle}</title>
	<meta name="description"
		content="${metaDescription}" />
	<meta name="keywords"
		content="${metaKeywords}" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link href="<@spring.url '/resources/styles/stylePublic.css?v1.1'/>" type="text/css" rel="stylesheet" />
    <link href="<@spring.url '/resources/styles/skins/tango/skin.css'/>" type="text/css" rel="stylesheet" />
    <link rel="icon" type="image/png" href="<@spring.url '/resources/images/favicon.png'/>" />
    <script type="text/javascript" src="<@spring.url '/resources/js/jquery.min.js'/>"></script>
    <script type="text/javascript" src="<@spring.url '/resources/js/jquery.jcarousel.min.js'/>"></script>
    <!--script type="text/javascript" src="<@spring.url '/resources/js/menu.js'/>"></script-->

  <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/styles/jquery.ad-gallery.css'/>">
  <script type="text/javascript" src="<@spring.url '/resources/js/jquery.easing.1.3.js'/>"></script>
  <script type="text/javascript" src="<@spring.url '/resources/js/jquery.ad-gallery.js'/>"></script>
  <script src="http://api-maps.yandex.ru/2.0/?load=package.standard&lang=ru-RU" type="text/javascript"></script>

  <script type="text/javascript">
  $(function() {
    $('img.image1').data('ad-desc', '');
    $('img.image1').data('ad-title', '');
    $('img.image4').data('ad-desc', '');
    $('img.image5').data('ad-desc', '');
    var galleries = $('.ad-gallery').adGallery();
    if (galleries.length < 1) {
        return false;
    }
    $('#switch-effect').change(
      function() {
        galleries[0].settings.effect = $(this).val();
        return false;
      }
    );
    $('#toggle-slideshow').click(
      function() {
        galleries[0].slideshow.toggle();
        return false;
      }
    );
    galleries[0].addAnimation('wild',
      function(img_container, direction, desc) {
        var current_left = parseInt(img_container.css('left'), 10);
        var current_top = parseInt(img_container.css('top'), 10);
        if(direction == 'left') {
          var old_image_left = '-'+ this.image_wrapper_width +'px';
          img_container.css('left',this.image_wrapper_width +'px');
          var old_image_top = '-'+ this.image_wrapper_height +'px';
          img_container.css('top', this.image_wrapper_height +'px');
        } else {
          var old_image_left = this.image_wrapper_width +'px';
          img_container.css('left','-'+ this.image_wrapper_width +'px');
          var old_image_top = this.image_wrapper_height +'px';
          img_container.css('top', '-'+ this.image_wrapper_height +'px');
        };
        if(desc) {
          desc.css('bottom', '-'+ desc[0].offsetHeight +'px');
          desc.animate({bottom: 0}, this.settings.animation_speed * 2);
        };
        img_container.css('opacity', 0);
        return {old_image: {left: old_image_left, top: old_image_top, opacity: 0},
                new_image: {left: current_left, top: current_top, opacity: 1},
                easing: 'easeInBounce',
                speed: 2500};
      }
    );
  });
  function debug(str) {
    if(window.console && window.console.log && jQuery.browser.mozilla) {
      console.log(str);
    } else {
      $('#debug').show().val($('#debug').val() + str +'\n');
    }
  }
  </script>

    <script src="<@spring.url '/resources/js/uploadify/swfobject.js'/>"></script>
    <script src="<@spring.url '/resources/js/uploadify/jquery.uploadify.v2.1.4.min.js'/>"></script>

	<script type="text/javascript">
	$(document).ready(function(){

		/*initTrees();*/
        $("#findDiv").click(function() {
            $('#search form').submit();
        });

        $('#specials').jcarousel();

        $("#menu li").hover(function(){
            $(this).addClass("hover");
            $('ul:first',this).css('visibility', 'visible');
            $('ul:first',this).show();

        }, function(){
            $(this).removeClass("hover");
            $('ul:first',this).css('visibility', 'hidden');
            $('ul:first',this).hide();

        });

        $("#menu li ul li:has(ul)").find("a:first").append(" &raquo; ");

        $('#sendFeedback').click(function() {
            if ($('#name').val().length < 1) {
                alert('Пожалуйста, введите имя');
                return false;
            }
/*            if ($('#phone').val().length < 1) {
                alert('Пожалуйста, введите телефон');
                return false;
            }*/

            if ($('#email').val().length < 1) {
                alert('Пожалуйста, введите E-mail');
                return false;
            }

            if ($('#text').val().length < 1) {
                alert('Пожалуйста, введите текст');
                return false;
            }
        });

        $('#deliverySubmitOrder').click(function() {
            if ($('#name').val().length < 1) {
                alert('Пожалуйста, введите имя');
                return false;
            }
            if ($('#phone').val().length < 1) {
                alert('Пожалуйста, введите телефон');
                return false;
            }
            $.ajax({
                type: "POST",
                data: $("#order").serialize(),
                url : "<@spring.url '/deliveryOrder'/>",
                beforeSend: function() {
                    $("#loader").show("fast");
                },
                success: function(data) {
                    $("#loader").hide("fast");
                    alert("Заявка отправлена");
                    $("#deliverySubmitOrder").hide();
                    var h2 = $("<h2/>");
                    h2.text("Ваша заявка принята. В ближайшее время с Вами свяжется наш менеджер.");
                    var a = $("<a/>");
                    a.attr("href", "<@spring.url '/deliveryOrder'/>");
                    a.text("Отправить ещё заявку");
                    $("#order").append(h2);
                    $("#order").append(a);
                    $('#uploadify').uploadifyUpload();
                },
                error: function(err) {
                    $("#loader").hide("fast");
                    alert("Ошибка на сервере" + err);
                }
            });
            return false;
        });

        $('#rentSubmitOrder').click(function() {
            if ($('#name').val().length < 1) {
                alert('Пожалуйста, введите имя');
                return false;
            }
            if ($('#phone').val().length < 1) {
                alert('Пожалуйста, введите телефон');
                return false;
            }
            $.ajax({
                type: "POST",
                data: $("#order").serialize(),
                url : "<@spring.url '/rentOrder'/>",
                beforeSend: function() {
                    $("#loader").show("fast");
                },
                success: function(data) {
                    $("#loader").hide("fast");
                    alert("Заявка отправлена");
                    $("#rentSubmitOrder").hide();
                    var h2 = $("<h2/>");
                    h2.text("Ваша заявка принята. В ближайшее время с Вами свяжется наш менеджер.");
                    var a = $("<a/>");
                    a.attr("href", "<@spring.url '/rentOrder'/>");
                    a.text("Отправить ещё заявку");
                    $("#order").append(h2);
                    $("#order").append(a);
                    $('#uploadify').uploadifyUpload();
                },
                error: function(err) {
                    $("#loader").hide("fast");
                    alert("Ошибка на сервере" + err);
                }
            });
            return false;
        });

        $('#evaluationSubmitOrder').click(function() {
            if ($('#address').val().length < 1) {
                alert('Пожалуйста, введите адресс');
                return false;
            }
            if ($('#name').val().length < 1) {
                alert('Пожалуйста, введите имя');
                return false;
            }
            if ($('#phone').val().length < 1) {
                alert('Пожалуйста, введите телефон');
                return false;
            }
            if ($('#email').val().length < 1) {
                alert('Пожалуйста, введите email');
                return false;
            }
            $.ajax({
                type: "POST",
                data: $("#order").serialize(),
                url : "<@spring.url '/evaluationOrder'/>",
                beforeSend: function() {
                    $("#loader").show("fast");
                },
                success: function(data) {
                    $("#loader").hide("fast");
                    alert("Заявка отправлена");
                    $("#evaluationSubmitOrder").hide();
                    var h2 = $("<h2/>");
                    h2.text("Ваша заявка принята. В ближайшее время с Вами свяжется наш менеджер.");
                    var a = $("<a/>");
                    a.attr("href", "<@spring.url '/evaluationOrder'/>");
                    a.text("Отправить ещё заявку");
                    $("#order").append(h2);
                    $("#order").append(a);
                    $('#uploadify').uploadifyUpload();
                },
                error: function(err) {
                    $("#loader").hide("fast");
                    alert("Ошибка на сервере" + err);
                }
            });
            return false;
        });

        $("#estateListTable tr:odd").css("background", "#FFF");

        $("#typeId").change(function() {
            if ($("#typeId").val() == 2) {
                $("#rooms").attr("disabled", "disabled");
            } else {
                $("#rooms").removeAttr("disabled");
            }
        });

	});
</script>

    <script type="text/javascript" src="<@spring.url '/resources/js/fancybox/jquery.mousewheel-3.0.4.pack.js'/>"></script>
 <script type="text/javascript" src="<@spring.url '/resources/js/fancybox/jquery.fancybox-1.3.4.pack.js'/>"></script>
 <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/js/fancybox/jquery.fancybox-1.3.4.css'/>" media="screen" />

 <script type="text/javascript">
     $(document).ready(function() {

         $("a[rel=example_group]").fancybox({
             'transitionIn'		: 'none',
             'transitionOut'		: 'none',
             'titlePosition' 	: 'over',
             'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
                 return '<span id="fancybox-title-over">Фотография ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
             }
         });


     });
 </script>

<script type="text/javascript" src="http://userapi.com/js/api/openapi.js?45"></script>

<script type="text/javascript">
  VK.init({apiId: 2632809, onlyWidgets: true});
</script>

<script type="text/javascript">
    function externalLinks() {
        links = document.getElementsByTagName("a");
        for (i = 0; i < links.length; i++) {
            link = links[i];
            if (link.getAttribute("href") && link.getAttribute("rel") == "external")
                link.target = "_blank";
        }
    }
    window.onload = externalLinks;
</script>

</head>
<body>
    <div id="loader">
        <img src="<@spring.url '/resources/images/loader.gif' />" alt="Loader" />
    </div>
    <div id="header">
        <div id="headerDiv">
            <div id="logo">
                <a href="<@spring.url '/'/>" title="На главную">
                    <img src="<@spring.url '/resources/images/logo.png'/>" alt="Пара - агентсво недвижимости" />
                </a>
            </div>
            <div id="key">
                <img src="<@spring.url '/resources/images/key.png'/>" alt="Key" />
            </div>
            <div id="contactsImg">
                <img src="<@spring.url '/resources/images/contacts.jpg'/>" alt="Contacts" />
            </div>
            <div id="contacts">
                <span class="title2">Контактная информация:</span>
                <br />
                <table cellpadding="5">
                    <tbody><tr>
                        <td colspan="2">Офис (с 9.00 до 19.00):</td>
					</tr>
                    <tr>
                        <td colspan="2"><span class="titleRed">8 (846) 200-21-20</span></td>
                    </tr>
                    <tr>
                        <td>24 часа:</td>
                        <td>8 (917) 158-7-444</td>
                    </tr>
                    <tr>
                        <td>Почта:</td>
                        <td><a href="mailto:info@parasamara.ru" class="title2">info@parasamara.ru</a></td>
                    </tr>
                </tbody></table>
            </div>
            <div id="slogan">
                Слова и дело<br />
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;в одном ключе
            </div>
        </div>
    </div>
    <div id="page">
        <div id="container">