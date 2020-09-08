<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>修改密码</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/dist/css/bootstrapValidator.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/layui/css/layui.css"/>

    <script src="<%= request.getContextPath() %>/jquery/jquery-1.10.2.min.js"></script>
    <script src="<%= request.getContextPath() %>/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%= request.getContextPath() %>/dist/js/bootstrapValidator.js"></script>
    <script src="<%= request.getContextPath() %>/layui/layui.all.js"></script>
    <script src="<%= request.getContextPath() %>/js/register.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <!-- form: -->
        <section>
            <div class="col-lg-8">
                <form id="forgetForm" class="form-horizontal">
                    <fieldset>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">用户名(邮箱)</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" name="username">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">验证码</label>
                            <div class="col-lg-4">
                                <input type="text" class="form-control" id="code" name="code">
                            </div>
                            <div class="col-lg-1">
                                <input type="button" class="form-control" value="发送" onclick="sendMail()" style="border-color:#ccc;margin-left:-20px;">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">新密码</label>
                            <div class="col-lg-5">
                                <input type="password" class="form-control" name="password">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">确认密码</label>
                            <div class="col-lg-5">
                                <input type="password" class="form-control" name="confirmPassword">
                            </div>
                        </div>
                    </fieldset>

                    <div class="form-group">
                        <div class="col-lg-9 col-lg-offset-3">
                            <button type="button" class="btn btn-primary" onclick="update()">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <!-- :form -->
    </div>

    <div style="display:inline-block;padding-top:20px;">
        <span>技术支持：024-22972212</span>
    </div>

</div>
<script type="text/javascript">
    var baseDir = "<%=request.getContextPath()%>";
    $(function(){
        $('#forgetForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                username: {
                    message: '用户名(邮箱)验证失败',
                    validators: {
                        notEmpty: {
                            message: '用户名(邮箱)不能为空'
                        },
                        emailAddress: {
                            message: '用户名(邮箱)格式有误'
                        },
                        callback:{
                            message:"该用户名不存在",
                            callback:function (value,validator) {
                                var flag = false;
                                $.ajax({
                                    url: baseDir + "/user/isUserExists2", // 请求路径
                                    type:'POST',
                                    async:false,	// 同步请求，必须是false
                                    data:{
                                        username:$("#forgetForm input[name=username]").val() // 请求参数
                                    },
                                    success:function(res){
                                        flag = res.valid;
                                    }
                                });
                                return flag;
                            }
                        }
                    }
                },
                code: {
                    message: '验证码验证失败',
                    validators: {
                        notEmpty: {
                            message: '验证码不能为空'
                        }
                    }
                },
                password: {
                    message: '新密码验证失败',
                    validators: {
                        notEmpty: {
                            message: '新密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 10,
                            message: '新密码长度必须在6到10位之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '新密码只能包含大写、小写、数字和下划线'
                        }
                    }
                },
                confirmPassword: {
                    message: '确认密码验证失败',
                    validators: {
                        notEmpty: {
                            message: '确认密码不能为空'
                        },
                        identical: {
                            field: 'password',
                            message: '确认密码与新密码不一致'
                        }
                    }
                }
             }
        });
    });

    function sendMail(){
        var formValidator = $("#forgetForm").data('bootstrapValidator');
        formValidator.validateField("username");
        var flag = formValidator.isValidField("username");
        if(flag){
            var email = $("#forgetForm input[name='username']").val();
            var index = layer.load(2); //又换了种风格，并且设定最长等待10秒
            $.ajax({
                type:'POST',
                url: baseDir + "/mail/sendMail",
                dataType:'json',
                data: {email : email},
                success: function(data){
                    layer.close(index);
                    if(data.status == "success"){
                        layer.open({
                            title: '信息',
                            content: data.msg,
                            icon: 1
                        });
                    }else{
                        layer.open({
                            title: '信息',
                            content: data.msg,
                            icon: 2
                        });
                    }
                },
                error:function(data){
                    layer.close(index);
                    layer.open({
                        title: '信息',
                        content: "验证码发送失败！",
                        icon: 2
                    });
                }
            });
        }
    }

    function update(){
        var formValidator = $("#forgetForm").data('bootstrapValidator');
        formValidator.validate()
        var flag = formValidator.isValid();
        if(!flag){
            return;
        }
        var code = $("#code").val();
        if(!code){
            layer.open({
                title: '信息',
                content: "验证码不能为空！",
                icon: 2
            });
            return;
        }
        var username = $("#forgetForm input[name='username']").val();
        var password = $("#forgetForm input[name='password']").val();
        $.ajax({
            type:'POST',
            url: baseDir + "/user/update",
            dataType:'json',
            data: {username: username, password: password, code: code},
            success: function(data){
                if(data.status == "success"){
                    layer.open({
                        title: '信息',
                        content: data.msg,
                        icon: 1
                    });
                    $("#forgetModal").modal("hide");
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
                    content: "密码修改失败",
                    icon: 2
                });
            }
        });
    }
</script>
</body>
</html>
