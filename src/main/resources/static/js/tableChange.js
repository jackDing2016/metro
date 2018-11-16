   $(document).ready(function() {
        var box1 = $('#tongjih1').html();
        var box2 = $('#tongjih2').html();
        var box3 = $('#tongjih3').html();
        var box4 = $('#tongjih4').html();
        var box5 = $('#tongjih5').html();
        var box6 = $('#tongjih6').html();
        $('input[type=radio][name=zhantaitype1]').change(function() {
        if (this.value == 'daoshi') {
            $('.daoshi1').show();
            $('.ceshi1').hide();
        }
        else if (this.value == 'ceshi') {
            $('.ceshi1').show();
            $('.daoshi1').hide();
        }
    });
       $('input[type=radio][name=zhantaitype2]').change(function() {
        if (this.value == 'daoshi') {
            $('.daoshi2').show();
            $('.ceshi2').hide();
        }
        else if (this.value == 'ceshi') {
            $('.ceshi2').show();
            $('.daoshi2').hide();
        }
    });
       $('input[type=radio][name=zhantaitype3]').change(function() {
        if (this.value == 'daoshi') {
            $('.daoshi3').show();
            $('.ceshi3').hide();
        }
        else if (this.value == 'ceshi') {
            $('.ceshi3').show();
            $('.daoshi3').hide();
        }
    });
       // jack
    //    for(let i=0;i<100;i++){
    //     $('#huanC'+i).on('input propertychange',function() {
    //      if (this.value == '1') {
    //         $('#huanCm'+i).html('');
    //     }
    //      if (this.value == '2') {
    //         $('#huanCm'+i).html('<div class="col-sm-6"><input type="text" class="form-control input-danwei" id="" >m</div>');
    //     }
    //      if (this.value == '3') {
    //         $('#huanCm'+i).html('<div class="col-sm-6"><input type="text" class="form-control input-danwei" id="" >m</div><div class="col-sm-6"><input type="text" class="form-control input-danwei" id="" >m</div>');
    //     }
    // });}


//       $('input[type=text][id=huanC2]').on('input propertychange',function() {
//         if (this.value == '1') {
//            $('#huanCm2').html('');
//        }
//         if (this.value == '2') {
//            $('#huanCm2').html('<div class="col-sm-6"><input type="text" class="form-control input-danwei" id="" >m</div>');
//        }
//         if (this.value == '3') {
//            $('#huanCm2').html('<div class="col-sm-6"><input type="text" class="form-control input-danwei" id="" >m</div><div class="col-sm-6"><input type="text" class="form-control input-danwei" id="" >m</div>');
//        }   
//    });
//       $('input[type=text][id=huanC3]').on('input propertychange',function() {
//         if (this.value == '1') {
//            $('#huanCm3').html('');
//        }
//         if (this.value == '2') {
//            $('#huanCm3').html('<div class="col-sm-6"><input type="text" class="form-control input-danwei" id="" >m</div>');
//        }
//         if (this.value == '3') {
//            $('#huanCm3').html('<div class="col-sm-6"><input type="text" class="form-control input-danwei" id="" >m</div><div class="col-sm-6"><input type="text" class="form-control input-danwei" id="" >m</div>');
//        }   
//    });
        $('input[type=text][id=zongji1]').on('input propertychange',function() {
           
         if (this.value == '1') {
            $('#tongjih1').html(box1);
             $('#tongjih2').html(box2);
             $('.line01 .tongji').addClass('col-6');
             $('.line01 .tongji').removeClass('col-12');
        }
         if (this.value == '2') {
             $('#tongjih1').html(box1);
             $('#tongjih2').html(box2);
             $('.line01 .tongji').addClass('col-6');
             $('.line01 .tongji').removeClass('col-12');
            for(i=0;i<100;i++){
            $('#tt'+i).append('<td><input type="text" value="" class="form-control"></td>');
            $('#thead'+i).append('<th>换乘通道2</th>');
             };
             
             $('#tbhead1').append('<th>换乘走行时间</th>');
             $('#tbhead2').append('<th>换乘通道换乘量</th>');
        }
         if (this.value == '3') {
             $('#tongjih1').html(box1);
             $('#tongjih2').html(box2);
             $('.line01 .tongji').addClass('col-6');
             $('.line01 .tongji').removeClass('col-12');
             for(i=0;i<100;i++){
            $('#tt'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#thead'+i).append('<th>换乘通道2</th><th>换乘通道3</th>');
             };
             
             $('#tbhead1').append('<th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead2').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        }
            if (this.value == '4') {
             $('#tongjih1').html(box1);
             $('#tongjih2').html(box2);
             $('.line01 .tongji').addClass('col-6');
             $('.line01 .tongji').removeClass('col-12');
             for(i=0;i<100;i++){
            $('#tt'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#thead'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th>');
             };
             
             $('#tbhead1').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead2').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
            if (this.value == '5') {
             $('#tongjih1').html(box1);
             $('#tongjih2').html(box2);
             $('.line01 .tongji').addClass('col-6');
             $('.line01 .tongji').removeClass('col-12');
             for(i=0;i<100;i++){
            $('#tt'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#thead'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th>');
             };
             
             $('#tbhead1').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead2').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
            if (this.value == '6') {
             $('#tongjih1').html(box1);
             $('#tongjih2').html(box2);
             $('.line01 .tongji').removeClass('col-6');
             $('.line01 .tongji').addClass('col-12');
             for(i=0;i<100;i++){
            $('#tt'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#thead'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th><th>换乘通道6</th>');
             };
             
             $('#tbhead1').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead2').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        }
            if (this.value == '7') {
             $('#tongjih1').html(box1);
             $('#tongjih2').html(box2);
             $('.line01 .tongji').removeClass('col-6');
             $('.line01 .tongji').addClass('col-12');
             for(i=0;i<100;i++){
            $('#tt'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#thead'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th><th>换乘通道6</th><th>换乘通道7</th>');
             };
             
             $('#tbhead1').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead2').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
            if (this.value == '8') {
             $('#tongjih1').html(box1);
             $('#tongjih2').html(box2);
             $('.line01 .tongji').removeClass('col-6');
             $('.line01 .tongji').addClass('col-12');
             for(i=0;i<100;i++){
            $('#tt'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#thead'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th><th>换乘通道6</th><th>换乘通道7</th><th>换乘通道8</th>');
             };
             
             $('#tbhead1').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead2').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
            if (this.value == '9') {
             $('#tongjih1').html(box1);
             $('#tongjih2').html(box2);
             $('.line01 .tongji').removeClass('col-6');
             $('.line01 .tongji').addClass('col-12');
             for(i=0;i<100;i++){
            $('#tt'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#thead'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th><th>换乘通道6</th><th>换乘通道7</th><th>换乘通道8</th><th>换乘通道9</th>');
             };
             
             $('#tbhead1').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead2').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
    });
       $('input[type=text][id=zongji2]').on('input propertychange',function() {
           
         if (this.value == '1') {
             $('#tongjih3').html(box3);
             $('#tongjih4').html(box4);
             $('.line02 .tongji').addClass('col-6');
             $('.line02 .tongji').removeClass('col-12');
        }
         if (this.value == '2') {
             $('#tongjih3').html(box3);
             $('#tongjih4').html(box4);
             $('.line02 .tongji').addClass('col-6');
             $('.line02 .tongji').removeClass('col-12');
            for(i=0;i<1000;i++){
            $('#tts'+i).append('<td><input type="text" value="" class="form-control"></td>');
            $('#theads'+i).append('<th>换乘通道2</th>');
             };
             
             $('#tbhead3').append('<th>换乘走行时间</th>');
             $('#tbhead4').append('<th>换乘通道换乘量</th>');
        }
         if (this.value == '3') {
             $('#tongjih3').html(box3);
             $('#tongjih4').html(box4);
             $('.line02 .tongji').addClass('col-6');
             $('.line02 .tongji').removeClass('col-12');
             for(i=0;i<1000;i++){
            $('#tts'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#theads'+i).append('<th>换乘通道2</th><th>换乘通道3</th>');
             };
             
             $('#tbhead3').append('<th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead4').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        }
            if (this.value == '4') {
             $('#tongjih3').html(box3);
             $('#tongjih4').html(box4);
             $('.line02 .tongji').addClass('col-6');
             $('.line02 .tongji').removeClass('col-12');
             for(i=0;i<1000;i++){
            $('#tts'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#theads'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th>');
             };
             
             $('#tbhead3').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead4').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
            if (this.value == '5') {
             $('#tongjih3').html(box3);
             $('#tongjih4').html(box4);
             $('.line02 .tongji').addClass('col-6');
             $('.line02 .tongji').removeClass('col-12');
             for(i=0;i<1000;i++){
            $('#tts'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#theads'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th>');
             };
             
             $('#tbhead3').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead4').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
            if (this.value == '6') {
             $('#tongjih3').html(box3);
             $('#tongjih4').html(box4);
             $('.line02 .tongji').removeClass('col-6');
             $('.line02 .tongji').addClass('col-12');
             for(i=0;i<1000;i++){
            $('#tts'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#theads'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th><th>换乘通道6</th>');
             };
             
             $('#tbhead3').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead4').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        }
            if (this.value == '7') {
             $('#tongjih3').html(box3);
             $('#tongjih4').html(box4);
             $('.line02 .tongji').removeClass('col-6');
             $('.line02 .tongji').addClass('col-12');
             for(i=0;i<1000;i++){
            $('#tts'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#theads'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th><th>换乘通道6</th><th>换乘通道7</th>');
             };
             
             $('#tbhead3').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead4').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
            if (this.value == '8') {
             $('#tongjih3').html(box3);
             $('#tongjih4').html(box4);
             $('.line02 .tongji').removeClass('col-6');
             $('.line02 .tongji').addClass('col-12');
             for(i=0;i<1000;i++){
            $('#tts'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#theads'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th><th>换乘通道6</th><th>换乘通道7</th><th>换乘通道8</th>');
             };
             
             $('#tbhead3').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead4').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
            if (this.value == '9') {
             $('#tongjih3').html(box3);
             $('#tongjih4').html(box4);
             $('.line02 .tongji').removeClass('col-6');
             $('.line02 .tongji').addClass('col-12');
             for(i=0;i<1000;i++){
            $('#tts'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#theads'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th><th>换乘通道6</th><th>换乘通道7</th><th>换乘通道8</th><th>换乘通道9</th>');
             };
             
             $('#tbhead3').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead4').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
    });
       $('input[type=text][id=zongji3]').on('input propertychange',function() {
           
         if (this.value == '1') {
             $('#tongjih5').html(box5);
             $('#tongjih6').html(box6);
             $('.line03 .tongji').addClass('col-6');
             $('.line03 .tongji').removeClass('col-12');
        }
         if (this.value == '2') {
             $('#tongjih5').html(box5);
             $('#tongjih6').html(box6);
             $('.line03 .tongji').addClass('col-6');
             $('.line03 .tongji').removeClass('col-12');
            for(i=0;i<1000;i++){
            $('#ttss'+i).append('<td><input type="text" value="" class="form-control"></td>');
            $('#theadss'+i).append('<th>换乘通道2</th>');
             };
             
             $('#tbhead5').append('<th>换乘走行时间</th>');
             $('#tbhead6').append('<th>换乘通道换乘量</th>');
        }
         if (this.value == '3') {
             $('#tongjih5').html(box5);
             $('#tongjih6').html(box6);
             $('.line03 .tongji').addClass('col-6');
             $('.line03 .tongji').removeClass('col-12');
             for(i=0;i<1000;i++){
            $('#ttss'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#theadss'+i).append('<th>换乘通道2</th><th>换乘通道3</th>');
             };
             
             $('#tbhead5').append('<th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead6').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        }
            if (this.value == '4') {
             $('#tongjih5').html(box5);
             $('#tongjih6').html(box6);
             $('.line03 .tongji').addClass('col-6');
             $('.line03 .tongji').removeClass('col-12');
             for(i=0;i<1000;i++){
            $('#ttss'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#theadss'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th>');
             };
             
             $('#tbhead5').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead6').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
            if (this.value == '5') {
             $('#tongjih5').html(box5);
             $('#tongjih6').html(box6);
             $('.line03 .tongji').addClass('col-6');
             $('.line03 .tongji').removeClass('col-12');
             for(i=0;i<1000;i++){
            $('#ttss'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#theadss'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th>');
             };
             
             $('#tbhead5').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead6').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
            if (this.value == '6') {
             $('#tongjih5').html(box5);
             $('#tongjih6').html(box6);
             $('.line03 .tongji').removeClass('col-6');
             $('.line03 .tongji').addClass('col-12');
             for(i=0;i<1000;i++){
            $('#ttss'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#theadss'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th><th>换乘通道6</th>');
             };
             
             $('#tbhead5').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead6').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        }
            if (this.value == '7') {
             $('#tongjih5').html(box5);
             $('#tongjih6').html(box6);
             $('.line03 .tongji').removeClass('col-6');
             $('.line03 .tongji').addClass('col-12');
             for(i=0;i<1000;i++){
            $('#ttss'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#theadss'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th><th>换乘通道6</th><th>换乘通道7</th>');
             };
             
             $('#tbhead5').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead6').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
            if (this.value == '8') {
             $('#tongjih5').html(box5);
             $('#tongjih6').html(box6);
             $('.line03 .tongji').removeClass('col-6');
             $('.line03 .tongji').addClass('col-12');
             for(i=0;i<1000;i++){
            $('#ttss'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#theadss'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th><th>换乘通道6</th><th>换乘通道7</th><th>换乘通道8</th>');
             };
             
             $('#tbhead5').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead6').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
            if (this.value == '9') {
             $('#tongjih5').html(box5);
             $('#tongjih6').html(box6);
             $('.line03 .tongji').removeClass('col-6');
             $('.line03 .tongji').addClass('col-12');
             for(i=0;i<1000;i++){
            $('#ttss'+i).append('<td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td><td><input type="text" value="" class="form-control"></td>');
            $('#theadss'+i).append('<th>换乘通道2</th><th>换乘通道3</th><th>换乘通道4</th><th>换乘通道5</th><th>换乘通道6</th><th>换乘通道7</th><th>换乘通道8</th><th>换乘通道9</th>');
             };
             
             $('#tbhead5').append('<th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th><th>换乘走行时间</th>');
             $('#tbhead6').append('<th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th><th>换乘通道换乘量</th>');
        } 
    });
    for(t=0;t<100;t++){
       var crkSample = $('.crk-sample').html();
       $('#add-entrace'+t).click(function(){
           var num=$(this).parent().siblings('.crk-table-add').find('.col-4').size();
           $(this).parent().siblings('.crk-table-add').append(crkSample);
           $(this).parent().siblings('.crk-table-add').find('span').last().html(num+1);
       })
    }
});
