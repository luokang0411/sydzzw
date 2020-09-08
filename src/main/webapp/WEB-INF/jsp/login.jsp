<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>用户登录</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/dist/css/bootstrapValidator.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css"/>

    <script src="<%= request.getContextPath() %>/jquery/jquery-1.10.2.min.js"></script>
    <script src="<%= request.getContextPath() %>/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%= request.getContextPath() %>/dist/js/bootstrapValidator.js"></script>
    <script src="<%= request.getContextPath() %>/js/login.js"></script>
    <style>
        #checkCode{
            height:32px;
            padding-left: 10px;
            border:1px solid #c0c0c0;
            width: calc(100% - 110px);
        }
    </style>
</head>
<body>
<div class="head">
    <a href="#">
        <img src="<%= request.getContextPath() %>/images/login_logo.png" />
    </a>
</div>

<div class="mainContent">
    <div class="content" id="form1">
        <form class="loginDiv" id="loginForm" class="form-horizontal">
            <div class="title">用户登录</div>
            <div class="loginbox icon_user">
                <input id="username" placeholder="用户名"/>
            </div>
            <div class="loginbox icon_password" >
                <input id="password" placeholder="密码" type="password"/>
            </div>
            <div style="margin-bottom: 10px;">
                <input id="checkCode" placeholder="请输入验证码" />
                <img id="codeImg" title="看不清，换一张" src="<%=request.getContextPath()%>/code/getCode">
            </div>
            <p id="error" class="login-error" style="display:inline-block;color:red;margin-bottom:2px;"></p>
            <div class="loginBtnWrap" >
                <input class="loginBtn" type="button" value="登录" onclick="login()"/>
            </div>
            <div class="footBtnWrap" >
                <a style="float:left" onclick="showRegisterModal()">立即注册</a>
                <a style="float:right" onclick="showForgetModal()">忘记密码</a>
            </div>
        </form>
    </div>
</div>

<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="registerModalLabel">账号注册</h4>
            </div>
            <div class="modal-body" class="col-lg-8 col-lg-offset-2" style="width:300px;">
                <jsp:include page="register.jsp"/>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="forgetModal" tabindex="-1" role="dialog" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="forgetModalLabel">修改密码</h4>
            </div>
            <div class="modal-body" class="col-lg-8 col-lg-offset-2" style="width:300px;">
                <jsp:include page="forget.jsp"/>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script type="text/javascript">
    var baseDir = "<%=request.getContextPath()%>";
    $(function(){
        $("#codeImg").click(function () {
            var src =baseDir + "/code/getCode?" + new Date().getTime();
            $(this).attr("src",src);
        });
    });
</script>

</html>