$(function(){
    $(document).keyup(function(event){
        if(event.keyCode ==13){
            login();
        }
    });
});

function login(){
    var username = $("#username").val();
    var password = $("#password").val();
    var checkCode = $("#checkCode").val();
    if(!username || !password){
        $("#error").html("用户名或者密码不能为空！");
        return;
    }else if(!checkCode){
        $("#error").html("验证码不能为空！");
        return;
    }else{
        $("#error").html("");
    }
    // window.top.location = "Demo.html";
    $.ajax({
        type:'POST',
        url: baseDir + "/user/login",
        // contentType: 'application/json',
        dataType:'json',
        data: {username: username, password: password, checkCode: checkCode},
        success: function(data){
            if(data.status == "success"){
                window.top.location = baseDir + "/building/buildingInfoList";
            }else{
                $("#error").html(data.msg);
            }
        },
        error:function(data){
            $("#error").html("登录失败，未知错误");
        }
    });
}

function showRegisterModal(){
    $("#registerForm").bootstrapValidator("resetForm");
    $("#registerForm")[0].reset();
    $("#registerModal").modal("show");
}

function showForgetModal(){
    var username = $("#username").val();
    if(!username){
        layer.open({
            title: '信息',
            content: "用户名不能为空！",
            icon: 2
        });
    }else{
        $("#forgetForm").bootstrapValidator("resetForm");
        $("#forgetForm")[0].reset();
        $("#forgetForm input[name='username']").val(username);
        $("#forgetModal").modal("show");
    }

}