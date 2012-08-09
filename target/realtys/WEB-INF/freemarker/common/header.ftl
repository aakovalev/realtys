<#ftl encoding="UTF-8"><#import "/spring.ftl" as spring /><?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xmlns="http://www.w3.org/TR/1999/REC-html-in-xml"
              xml:lang="en" lang="en" >
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/styles/style.css'/>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<link rel="stylesheet" href="<@spring.url '/resources/styles/jquery.treeview.css'/>" />
    <link rel="stylesheet" href="<@spring.url '/resources/styles/datepicker.css'/>" />

	<script src="<@spring.url '/resources/js/jquery.js'/>" type="text/javascript"></script>
	<script src="<@spring.url '/resources/js/jquery.treeview.js'/>" type="text/javascript"></script>
    <script src="<@spring.url '/resources/js/ui.datepicker.js'/>" type="text/javascript"></script>
    <!--script src="<@spring.url '/resources/js/datepicker.translate.js'/>" type="text/javascript"></script-->
	<!--script src="<@spring.url '/resources/js/jquery.treeview.edit.js'/>" type="text/javascript"></script>
	<!--script src="<@spring.url '/resources/js/jquery.treeview.async.js'/>" type="text/javascript"></script-->

	<script type="text/javascript">
	function initTrees() {
		$("#categoryList").treeview({
			url: "<@spring.url '/category/childs'/>"
		});
	}

	$(document).ready(function(){

        $('#addDate').attachDatepicker();

        $(".tree").treeview({
		    collapsed: true
	    });
        $(".tree2").treeview({
		    collapsed: true
	    });

		/*initTrees();*/

	});

</script>

    <script src="<@spring.url '/resources/js/uploadify/swfobject.js'/>"></script>
    <script src="<@spring.url '/resources/js/uploadify/jquery.uploadify.v2.1.4.min.js'/>"></script>

<script type="text/javascript" src="<@spring.url '/resources/js/swfupload/swfupload.queue.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/resources/js/swfupload/fileprogress.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/resources/js/swfupload/handlers.js'/>"></script>
<script type="text/javascript">
		var upload1;

/*		window.onload = function() {
			upload1 = new SWFUpload({
				// Backend Settings
				upload_url: "upload.php",
				//post_params: {"PHPSESSID" : "<?php echo session_id(); ?>"},

				// File Upload Settings
				file_size_limit : "102400",	// 100MB
				file_types : "*.*",
				file_types_description : "All Files",
				file_upload_limit : "10",
				file_queue_limit : "0",

				// Event Handler Settings (all my handlers are in the Handler.js file)
				file_dialog_start_handler : fileDialogStart,
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,

				// Button Settings
				button_image_url : "<@spring.url '/resources/images/XPButtonUploadText_61x22.png'/>",
				button_placeholder_id : "spanButtonPlaceholder1",
				button_width: 61,
				button_height: 22,

				// Flash Settings
				flash_url : "<@spring.url '/resources/js/swfupload/swfupload.swf'/>",


				custom_settings : {
					progressTarget : "fsUploadProgress1",
					cancelButtonId : "btnCancel1"
				},

				// Debug Settings
				debug: false
			});

	     }*/
	</script>

<script type="text/javascript" src="<@spring.url '/resources/js/estate.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/resources/js/tiny_mce/jquery.tinymce.js'/>"></script>
<script type="text/javascript">
        $(function() {
                $('textarea.tinymce').tinymce({
                        // Location of TinyMCE script
                        script_url : "<@spring.url '/resources/js/tiny_mce/tiny_mce.js'/>",

                        // General options
                        theme : "advanced",
                        plugins : "pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",

                        // Theme options
                        theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
                        theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
                        theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
                        theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak",
                        theme_advanced_toolbar_location : "top",
                        theme_advanced_toolbar_align : "left",
                        theme_advanced_statusbar_location : "bottom",
                        theme_advanced_resizing : true,

                        // Example content CSS (should be your site CSS)
                        content_css : "css/content.css",

                        // Drop lists for link/image/media/template dialogs
                        template_external_list_url : "lists/template_list.js",
                        external_link_list_url : "lists/link_list.js",
                        external_image_list_url : "lists/image_list.js",
                        media_external_list_url : "lists/media_list.js",

                        // Replace values for the template plugin
                        template_replace_values : {
                                username : "Some User",
                                staffid : "991234"
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

</head>
<body>
    <div id="loader">
        <img src="<@spring.url '/resources/images/loader.gif' />" alt="Loader" />
    </div>
    <div id="container">
        <div id="header">

        </div>
        <div id="menu">
            <ul>
                <li><a href="<@spring.url '/'/>">Сайт</a></li>
                <li>|</li>
                <li><a href="<@spring.url '/admin/users'/>">Пользователи</a></li>
                <li>|</li>
                <li><a href="<@spring.url '/admin/pages'/>">Страницы</a></li>
                <li>|</li>
                <li><a href="<@spring.url '/admin/publications'/>">Статьи</a></li>
                <li>|</li>
                <li><a href="<@spring.url '/admin/category'/>">Категории</a></li>
                <li>|</li>
                <li><a href="<@spring.url '/admin/estate'/>">Объявления</a></li>
                <li>|</li>
                <li><a href="<@spring.url '/admin/orders'/>">Заявки</a></li>
                <li>|</li>
                <li><a href="<@spring.url '/admin/complaints'/>">Жалобы и Предложения</a></li>
                <li>|</li>
                <li><a href="<@spring.url '/logout'/>">Выход</a></li>
            </ul>
        </div>
        <div id="page">