<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>楼面信息</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/dist/css/bootstrapValidator.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/miniui.css"/>


    <script src="<%= request.getContextPath() %>/jquery/jquery-1.10.2.min.js"></script>
    <script src="<%= request.getContextPath() %>/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%= request.getContextPath() %>/dist/js/bootstrapValidator.js"></script>
    <script src="<%= request.getContextPath() %>/js/miniui.js"></script>
    <script src="<%= request.getContextPath() %>/layui/layui.all.js"></script>
    <script src="<%= request.getContextPath() %>/js/building.js?version=1.1"></script>
    <style>
        .formTable td{
            border:1px solid #ccc;
            text-align:center;
            height:36px;
        }
        .formTable table-label{
              width:25%;
        }
        .formTable table-value{
            width:75%;
        }
        .centerDiv{
            width:80%;
            margin-left:10%;
        }
        .formTable input{
            width:100%;
            height:100%;
            text-align: center;
            border:none;
        }
        input[type='checkbox']{
            width:16px;
            height:16px;
        }
    </style>
</head>
<body>
<div>
    <div class="mini-form" id="buildingForm" style="padding-bottom: 50px;">
        <div class="centerDiv" style="border-bottom:2px solid #3171BA;">
            <h3 style="text-align: center;">楼面信息表</h3>
            <div class="btnDiv" style="float:right;margin:6px 0px">
                <button onclick="submitBuildingForm()">提交</button>
                <button onclick="saveBuildingInfo()" >保存</button>
            </div>
        </div>

        <%--隐藏域--%>
        <input class="mini-hidden" name="kid"/>

        <table class="formTable centerDiv" style="clear: both">
            <tr>
                <td class="table-label">建设单位</td>
                <td class="table-value">
                    <input class="mini-textbox" name="jsdw" required="true" requiredErrorText="建设单位不能为空"/>
                </td>
            </tr>
            <tr>
                <td class="table-label required">项目名称</td>
                <td class="table-value">
                    <input class="mini-textbox" name="xmmc" required="true" requiredErrorText="项目名称不能为空"/>
                </td>
            </tr>
            <tr>
                <td class="table-label required">项目地址</td>
                <td class="table-value">
                    <input class="mini-textbox" name="xmdz" required="true" requiredErrorText="项目地址不能为空"/>
                </td>
            </tr>
            <tr>
                <td class="table-label required">地块编号</td>
                <td class="table-value">
                    <input class="mini-textbox" name="dkbh" required="true" requiredErrorText="地块编号不能为空" onvaluechanged="dkbhChanged()"/>
                </td>
            </tr>
        </table>

        <div class="centerDiv" style="border-bottom:2px solid #3171BA;margin-top: 30px;">
            <h4>建筑物信息</h4>
            <div class="btnDiv" style="margin:6px 0px;float:left">
                <button onclick="addJzwTr('jzwxxTbody')" >添加</button>
                <button onclick="delJzwTr('jzwxxTbody')" >删除</button>
                <button onclick="buildingInfosValidate('true')" >验证</button>
            </div>
        </div>
        <table class="formTable centerDiv" style="clear: both">
            <thead>
                <tr>
                    <td rowspan="2" style="width:3%">
                        <input type="checkbox" onclick="checkAllTr(this,'jzwxxTbody')"/>
                    </td>
                    <td rowspan="2" style="width:8%">地块编号</td>
                    <td rowspan="2" style="width:7%">建筑栋号</td>
                    <td rowspan="2" style="width:18%">建筑用途</td>
                    <td rowspan="2" style="width:6%">地上层数</td>
                    <td rowspan="2" style="width:6%">地下层数</td>
                    <td rowspan="2" style="width:7%">地上高度(m)</td>
                    <td colspan="4" style="width:7%">建筑面积(m²)</td>
                    <td rowspan="2" style="width:18%">备注</td>
                </tr>
                <tr>
                    <td style="width:7%">地上</td>
                    <td style="width:7%">地下</td>
                    <td style="width:7%">计容面积</td>
                    <td style="width:7%">总面积</td>
                </tr>
            </thead>
            <tbody id="jzwxxTbody"></tbody>
            <tfoot>
                <tr>
                    <td></td><td>合计</td><td></td>
                    <td></td><td></td><td></td><td></td>
                    <td><input class="mini-textbox" name="dsmjSum" readonly/></td>
                    <td><input class="mini-textbox" name="dxmjSum" readonly/></td>
                    <td><input class="mini-textbox" name="jrmjSum" readonly/></td>
                    <td><input class="mini-textbox" name="zmjSum" readonly/></td>
                    <td><input class="mini-textbox" readonly/></td>
                </tr>
            </tfoot>
        </table>

        <div class="centerDiv" style="border-bottom:2px solid #3171BA;margin-top: 30px;">
            <h4>建筑物详细信息</h4>
            <div class="btnDiv" style="margin:6px 0px;float:left">
                <button onclick="addJzwTr('jzwxqTbody')" >添加</button>
                <button onclick="delJzwTr('jzwxqTbody')" >删除</button>
            </div>
        </div>
        <table class="formTable centerDiv" style="clear: both">
            <thead>
                <tr>
                    <td style="width:5%">
                        <input type="checkbox" onclick="checkAllTr(this,'jzwxqTbody')"/>
                    </td>
                    <td rowspan="2" style="width:15%">建筑栋号</td>
                    <td rowspan="2" style="width:18%">建筑用途</td>
                    <td rowspan="2" style="width:15%">起始层</td>
                    <td rowspan="2" style="width:15%">终止层</td>
                    <td rowspan="2" style="width:16%">计容面积</td>
                    <td colspan="4" style="width:16%">总面积</td>
                </tr>
            </thead>
            <tbody id="jzwxqTbody">

            </tbody>
        </table>
    </div>
</div>
<script>
    mini.parse();
    var baseDir = "<%=request.getContextPath()%>";
    var buildingCode = "${buildingCode}";
    var type = "${type}";
    var form = new mini.Form("#buildingForm");
</script>
</body>
</html>
