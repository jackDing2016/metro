 $(document).ready(function() {
     $('.guanzhu ul li').click(function(){
         var num=$(this).index();
         $('.guanzhu-list').fadeOut();
         $('.guanzhu-list').eq(num).fadeIn();
         $(this).addClass('active');
         $(this).siblings('li').removeClass('active');
         $('.state').fadeOut();
         $('.state').eq(num).fadeIn();
     });
     
 });
    
