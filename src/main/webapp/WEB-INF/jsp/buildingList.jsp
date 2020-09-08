<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>楼面信息列表</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/layui/css/layui.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap/css/bootstrap-table.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/dist/css/bootstrapValidator.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/miniui.css"/>

    <script src="<%= request.getContextPath() %>/jquery/jquery-1.10.2.min.js"></script>
    <script src="<%= request.getContextPath() %>/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%= request.getContextPath() %>/bootstrap/js/bootstrap-table.js"></script>
    <script src="<%= request.getContextPath() %>/dist/js/bootstrapValidator.js"></script>
    <script src="<%= request.getContextPath() %>/js/miniui.js"></script>
    <script src="<%= request.getContextPath() %>/layui/layui.all.js"></script>
    <script src="<%= request.getContextPath() %>/js/buildingList.js"></script>
    <style>
        #resultList td{
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
        }
        .contentDiv{
            margin-top:100px;
            width:80%;
            margin-left:10%;
            padding-bottom: 50px;
        }
        .btnDiv{
            float:right;
            margin-bottom: 6px;
        }
    </style>
</head>
<body>
<div class="contentDiv">
    <div class="btnDiv">
        <button onclick="openDetailInfos()">增加</button>
        <button onclick="exportBuildInfos()">导出</button>
    </div>
    <table id="resultList" style="font-size: 1.2rem !important; width:100%;table-layout: fixed"></table>
</div>
<script>
    var baseDir = "<%=request.getContextPath()%>";
</script>
</body>
</html>
