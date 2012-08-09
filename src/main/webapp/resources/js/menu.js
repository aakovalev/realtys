$(document).ready(function(){
 
/*    $("ul.subnav").parent().addClass("myClass");
    alert($("ul.subnav").parent().html());*/
    
    $("ul.topnav li").mouseover(function() {
        
        $(this).find("ul.subnav").slideDown('fast').show(); 
 
        $(this).parent().hover(function() {
        }, function(){    
            $(this).parent().find("ul.subnav").slideUp('slow'); 
        });
 
        }).hover(function() { 
            $(this).addClass("subhover"); 
            
            $(this).removeClass("subhover"); 
    });
    
    $("ul.subnav li").mouseover(function() {
        
        $(this).find("ul.subnav2").slideDown('fast').show(); 
 
        $(this).parent().hover(function() {
        }, function(){    
            $(this).parent().find("ul.subnav2").slideUp('slow'); 
        });
 
        }).hover(function() { 
            $(this).addClass("subhover"); 
            
            $(this).removeClass("subhover"); 
    });   
    
    $("span.atopnav").mouseover(function() {
        
        $(this).find("ul.subnav2").slideDown('fast').show(); 
 
        $(this).parent().hover(function() {
        }, function(){    
            $(this).parent().find("ul.subnav2").slideUp('slow'); 
        });
 
        }).hover(function() { 
            $(this).addClass("subhover"); 
            
            $(this).removeClass("subhover"); 
    });     
 
});