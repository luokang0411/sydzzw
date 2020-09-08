function register(){
    var formValidator = $("#registerForm").data('bootstrapValidator');
    formValidator.validate();
    var flag = formValidator.isValid();
    if(!flag){
        return;
    }
    var user = formJson();
    $.ajax({
        type:'POST',
        url: baseDir + "/user/insertUserInfo",
        contentType: 'application/json',
        dataType:'json',
        data: JSON.stringify(user),
        success: function(data){
            if(data.status == "success"){
                layer.open({
                    title: '信息',
                    content: data.msg,
                    icon: 1
                });
                $("#registerModal").modal("hide");
            }else{
                layer.open({
                    title: '信息',
                    content: data.msg,
                    icon: 2
                });
            }
        },
        error:function(data){
            layer.open({
                title: '信息',
                content: "用户注册失败",
                icon: 2
            });
        }
    });
}

function formJson(){
    var fields = $("#registerForm").serializeArray();
    console.log(fields);
    var jsonObj = {};
    for(var i=0;i<fields.length;i++){
        var name = fields[i].name;
        var value = fields[i].value;
        jsonObj[name] = value;
    }
    return jsonObj;
}



