<#include "../common/header.ftl">
<script type="text/javascript">
    $(document).ready(function() {
        $('#uploadify').uploadify({
            'uploader' : "<@spring.url '/resources/'/>js/uploadify/uploadify.swf",
            'script'   :  "<@spring.url '/admin/estate/editImages/'/>${estate.estateId?c};JSESSIONID=${sessionId}",
            'folder'   : "<@spring.url '/resources/'/>thumbs",
            'cancelImg': "<@spring.url '/resources/images/'/>cancel.png",
            'multi'    : true,
            'method'   : 'post'
        });
        $('#upload').click(function() {
            $('#uploadify').uploadifyUpload();
            return false;
        });
    });
</script>
<div id="content">
    <div id="userMenu">
        <!--a href="#" id="showClassListDiv" url="<@spring.url '/classes/getList'/>">Список классов</a> &nbsp;-->
    </div>
    <div id="search">
    </div>
     <div class="userList">
     </div>
     <div id="leftContainer">
         <h2>Список картинок:</h2>
         <ul style="list-style: none;">
             <li>
                <img src="<@spring.url '/resources/thumbs/s${estate.image!"noimage.jpg"}'/>"
                                       title="" longdesc="" alt="">
                <a title="Удалить #${estate.estateId?c}" href="<@spring.url '/admin/estate/deleteImage/${estate.estateId?c}'/>"><img
                    src="<@spring.url '/resources/images/delete.png'/>" alt="Удалить"/></a>
             </li>
             <#list estate.images as image>
                 <li>
                    <img src="<@spring.url '/resources/thumbs/s${image.image}'/>"
                                       title="" longdesc="" alt="">
                    <a title="Удалить #${estate.estateId?c}" href="<@spring.url '/admin/estate/deleteImages/${image.imageId?c}'/>"><img
                        src="<@spring.url '/resources/images/delete.png'/>" alt="Удалить"/></a>
                 </li>
             </#list>
         </ul>
         <form action="<@spring.url '/admin/estate/editImages/'/>${estate.estateId?c}" method="POST" enctype="multipart/form-data">
             <!--input type="file" name="image" /><br />
             <input type="submit" value="Добавить"-->
             <input id="uploadify" type="file">
                 <a id="upload" href="#">Загрузить файлы</a>
         </form>
     </div>
     <div class="clear"></div>
</div>
<#include "../common/footer.ftl">
