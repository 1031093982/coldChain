$(document).ready(function(){
    createCode();
    var width = $('.content').width();
    var height = width *0.95;
    var window_width = $(document.body).width();
    var left = (window_width - width)/2;
    $('.content').height(height);
    $('.content').css("left",left);

    $('.form_field').focus(function () {
        $(this).addClass("focus_border");
    });
    $('.form_field').blur(function () {
        $(this).removeClass("focus_border");
       // if (this.value != "") {
            //$(this).next().css("display", "none");
        //  }
    });

    $('.verification_input').blur(function () {
            var inputCode = document.getElementById("inputcode").value.toUpperCase(); //取得输入的验证码并转化为大写
            if(inputCode.length == 0) { //若输入的验证码长度为0
                $('#showcheckmsg').css("display","block");
                $('#showcheckmsg').html("<span class='err_mess'>请输入验证码</span>");
            }
            else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时
                $('#showcheckmsg').css("display","block");
                $('#showcheckmsg').html("<span class='err_mess'>验证码错误！</span>");
            }
            else { //输入正确时
                $('#showcheckmsg').empty();
            }
    });
    $('.user_field').blur(function(){
        if($.trim($('.user_field').val()).length == 0){
            $('.user_field').next().css("display","block");
            $('.user_field').next().html("<span class='err_mess'>请输入正确的用户名</span>");
        }else{
            $('.user_field').next().empty();
        }
    });

    $('.pass_field').blur(function () {
        // 判断密码
        if ($.trim($('.pass_field').val()).length == 0) {
            $('.pass_field').next().css("display", "block");
            $('.pass_field').next().html("<span class='err_mess'>请输入密码</span>");
            // $(this).focus();
        }else{
            $('.pass_field').next().empty();
        }
    });
    $('.form_submit_button').click(function(){
        $("form :input").trigger('blur');
        var msg = $('#msgg').val();
        var mse = $('#mse').val();
        var codecheckmsg = $('#codecheckmsg').val();
        if(mse!="") {
            $('.user_field').next().css("display", "block");
            $('#errorName2').html("<span class='err_mess'>" + mse + "</span>");
            $('#mse').val("");
        }
        if(msg!=""){
            $('.pass_field').next().css("display", "block");
            $('#errorMsg').html("<span class='err_mess'>"+msg+"</span>");
            $('#msgg').val("");
        }
        if(codecheckmsg!=""){
            $('.showcheckmsg').css("display", "block");
            $('#showcheckmsg').html("<span class='err_mess'>"+codecheckmsg+"</span>");
            $('#codecheckmsg').val("");
        }
        var numError = $('form .err_mess').length;
        if(numError){
            return false;
        }
    });
});
        function logCont() {
        var checkresult = validate();
          if( !checkresult){
          return false;
        }
        var  phone =  $('.user_field').val();
        if (phone !=""){
            // $.ajax({
            //     async:false,
            //     type: "GET",
            //     url: "http://www.ahdataex.com:8088/HnBdmarkportal/a/loginCont",
            //     data: {phone:phone},
            //     dataType: "json",
            //     success: function(data){
            //         if(data.result == "success"){
            //         }else if(data.result == "error_parameter"){
            //             var mse="手机号格式有误";
            //             $('#mse').val(mse);
            //
            //         }else{
            //             $('#msgg').val(data.msg);
            //         }
            //     }
            // });
        }
    }

function validate(){
    var inputCode = document.getElementById("inputcode").value.toUpperCase(); //取得输入的验证码并转化为大写
    if(inputCode.length <= 0) { //若输入的验证码长度为0
        var codecheckmsg="请输入验证码！";
        $('#codecheckmsg').val(codecheckmsg);
        return false;
    }
    else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时
        var codecheckmsg="验证码错误！";
        $('#codecheckmsg').val(codecheckmsg);
       // createCode();//刷新验证码
       // $('#inputcode').val("");;//清空文本框
        return false;
    }
    else { //输入正确时
        return true;
    }
}


function createCode(){
    code = "";
    var codeLength = 4;//验证码的长度
    var checkCode = document.getElementById("imagecode");
    var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R',
        'S','T','U','V','W','X','Y','Z');//随机数
    for(var i = 0; i < codeLength; i++) {//循环操作
        var index = Math.floor(Math.random()*34);//取得随机数的索引（0~35）
        code += random[index];//根据索引取得随机数加到code上
    }
    checkCode.value = code;//把code值赋给验证码
}


$(window).resize(function(){
    var width = $('.content').width();
    var height = width *0.88;
    var window_width = $(document.body).width();
    var left = (window_width - width)/2;
    $('.content').height(height);
    $('.content').css("left",left);
})

