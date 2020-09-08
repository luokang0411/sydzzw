<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>注册用户</title>
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
                <form id="registerForm" class="form-horizontal">
                    <fieldset>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">姓名</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" name="name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">电话</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" name="phone"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">证件类型</label>
                            <div class="col-lg-5">
                                <select class="form-control" name="cardType">
                                    <option value=""></option>
                                    <option value="1">身份证</option>
                                    <option value="2">组织机构代码证</option>
                                    <option value="3">统一社会信用代码</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">证件号码</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" name="cardId"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">用户名(邮箱)</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" name="username">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">密码</label>
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
                            <button type="button" class="btn btn-primary" onclick="register()">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <!-- :form -->
    </div>
</div>
<script type="text/javascript">
    var baseDir = "<%=request.getContextPath()%>";
    $(function(){
        $('#registerForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    message: '姓名验证失败',
                    validators: {
                        notEmpty: {
                            message: '姓名不能为空'
                        }
                    }
                },
                phone: {
                    message: '电话验证失败',
                    validators: {
                        notEmpty: {
                            message: '电话不能为空'
                        },
                        regexp: {
                            regexp: /^([1]\d{10}|([\(（]?0[0-9]{2,3}[）\)]?[-]?)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?)$/,
                            message: '电话格式有误'
                        }
                    }
                },
                username: {
                    message: '用户名(邮箱)验证失败',
                    validators: {
                        notEmpty: {
                            message: '用户名(邮箱)不能为空'
                        },
                        emailAddress: {
                            message: '用户名(邮箱)格式有误'
                        },
                        threshold: 2,
                        remote: {
                            url: baseDir + "/user/isUserExists",
                            message: '用户名已存在,请重新输入',
                            delay: 1000,
                            type: 'POST',
                            dataType:'json',
                            data: function(validator) {
                                return {
                                    username : $("#registerForm input[name=username]").val()
                                };
                            }
                        }
                    }
                },
                password: {
                    message: '密码验证失败',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 10,
                            message: '密码长度必须在6到10位之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '密码只能包含大写、小写、数字和下划线'
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
                            message: '确认密码与密码不一致'
                        }
                    }
                },
                cardType: {
                    message: '证件类型验证失败',
                    validators: {
                        notEmpty: {
                            message: '证件类型不能为空'
                        }
                    }
                },
                cardId: {
                    message: '证件号码验证失败',
                    validators: {
                        notEmpty: {
                            message: '证件号码不能为空'
                        }
                    }
                }
             }
        });
    });
</script>
</body>
</html>
