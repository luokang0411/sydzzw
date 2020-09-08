$(function(){
    loadBuildingInfos();
});

function loadBuildingInfos(){
    if(type == "add"){
        mini.getByName("kid").setValue(buildingCode);
    }else{
        $.ajax({
            type: 'get',
            url: baseDir + "/building/data/getBuildingInfos",
            contentType: 'application/json',
            dataType: 'json',
            data: {buildingCode: buildingCode},
            success: function (data) {
                var jzwInfos = data.jzwInfos;
                var dsmjSum = 0.0;
                var dxmjSum = 0.0;
                var jrmjSum = 0.
                if(jzwInfos && jzwInfos.length>0){
                    for(var i=0;i<jzwInfos.length;i++){
                        jzwInfos[i].zmj = (jzwInfos[i].dsmj + jzwInfos[i].dxmj).toFixed(2);
                        dsmjSum += jzwInfos[i].dsmj;
                        dxmjSum += jzwInfos[i].dxmj;
                        jrmjSum += jzwInfos[i].jrmj;
                        addJzwTr("jzwxxTbody");
                    }
                }
                data.dsmjSum = dsmjSum.toFixed(2);
                data.dxmjSum = dxmjSum.toFixed(2);
                data.jrmjSum = jrmjSum.toFixed(2);
                data.zmjSum = (dsmjSum + dxmjSum).toFixed(2);

                var jzwDetailInfos = data.jzwDetailInfos;
                if(jzwDetailInfos && jzwDetailInfos.length>0){
                    for(var i=0;i<jzwDetailInfos.length;i++){
                        addJzwTr("jzwxqTbody");
                    }
                }
                form.setData(data);
                if(data.sftj == "1"){
                    form.setEnabled(false);
                    $("#buildingForm button").remove();
                    // $("#buildingForm .btnDiv").remove();
                }
            },
            error: function (data) {

            }
        });
    }
}


function addJzwTr(id){
    var xh =  $("#" + id).find("tr:last").attr("xh");
    if(!xh){
        xh = 1;
    }else{
        xh = parseInt(xh) + 1;
    }
    var index = xh - 1;
    var tr = "";
    if(id == "jzwxxTbody"){
        var dkbh = mini.getByName("dkbh").getValue();
        var preName = "jzwInfos["+ index +"].";
        tr="<tr xh='"+ xh +"' style='height:36px;'> <td><input type='checkbox'/></td>" +
            "<td><input class='mini-textbox' name='"+ preName +"dkbh' value='"+ dkbh +"'readonly/></td>" +
            "<td><input class='mini-textbox' name='"+ preName +"jzdh' required='true' requiredErrorText='建筑栋号不能为空'/></td>" +
            "<td><input class='mini-treeselect' name='"+ preName +"jzyt' multiSelect='true' showFolderCheckBox='true' " +
            "url='"+ baseDir +"/building/data/getJzytDatas' required='true' requiredErrorText='建筑用途不能为空'/></td>" +
            "<td><input class='mini-textbox' name='"+ preName +"dscs' onvalidation='numValidate' vtype='int' intErrorText='请输入整数' " +
            "required='true' requiredErrorText='地上层数不能为空'/></td>" +
            "<td><input class='mini-textbox' name='"+ preName +"dxcs' onvalidation='numValidate' vtype='int' floatErrorText='请输入整数' " +
            "required='true' requiredErrorText='地下层数不能为空'/></td>" +
            "<td><input class='mini-textbox' name='"+ preName +"dsgd' onvalidation='numValidate' vtype='float' floatErrorText='请输入数字' " +
            "required='true' requiredErrorText='地上高度不能为空'/></td>" +
            "<td><input class='mini-textbox' name='"+ preName +"dsmj' onvalidation='numValidate' vtype='float' floatErrorText='请输入数字' " +
            "onvaluechanged='sumHjmj(\""+ preName +"\")' required='true' requiredErrorText='地上面积不能为空'/></td>" +
            "<td><input class='mini-textbox' name='"+ preName +"dxmj' onvalidation='numValidate' vtype='float' floatErrorText='请输入数字' " +
            "onvaluechanged='sumHjmj(\""+ preName +"\")'required='true' requiredErrorText='地下面积不能为空'/></td>" +
            "<td><input class='mini-textbox' name='"+ preName +"jrmj' onvalidation='numValidate' vtype='float' floatErrorText='请输入数字' " +
            "onvaluechanged='sumHjmj(\""+ preName +"\")'required='true' requiredErrorText='计容面积不能为空'/></td>" +
            "<td><input class='mini-textbox' name='"+ preName +"zmj' readonly/></td>" +
            "<td><input class='mini-textbox' name='"+ preName +"bz'/></td></tr>";
    }else if(id == "jzwxqTbody"){
        tr="<tr xh='"+ xh +"' style='height:36px;'><td><input type='checkbox'/></td>" +
            "<td><input class='mini-textbox' name='jzwDetailInfos["+ index +"].jzdh' required='true' requiredErrorText='建筑栋号不能为空'/></td>" +
            "<td><input class='mini-treeselect' name='jzwDetailInfos["+ index +"].jzyt' showRadioButton='true' " +
            "url='"+ baseDir +"/building/data/getJzytDatas' required='true' requiredErrorText='建筑用途不能为空'/></td>" +
            "<td><input class='mini-textbox' name='jzwDetailInfos["+ index +"].qsc' vtype='int' intErrorText='请输入整数' " +
            "required='true' requiredErrorText='起始层不能为空'/></td>" +
            "<td><input class='mini-textbox' name='jzwDetailInfos["+ index +"].zzc' vtype='int' intErrorText='请输入整数' " +
            "required='true' requiredErrorText='终止层不能为空'/></td>" +
            "<td><input class='mini-textbox' name='jzwDetailInfos["+ index +"].jrmj' onvalidation='numValidate' vtype='float' floatErrorText='请输入数字' " +
            "required='true' requiredErrorText='计容面积不能为空'/></td>" +
            "<td><input class='mini-textbox' name='jzwDetailInfos["+ index +"].zmj' onvalidation='numValidate' vtype='float' floatErrorText='请输入数字'" +
            "required='true' requiredErrorText='总面积不能为空'/></td></tr>";
    }
    $("#" + id).append(tr);
    mini.parse();
}

function sumHjmj(name){
    sumZmj(name);
    var data = form.getData();
    var jzwInfos = data.jzwInfos;
    var dsmjSum = 0.0;
    var dxmjSum = 0.0;
    var jrmjSum = 0.0;
    if(jzwInfos && jzwInfos.length>0){
        for(var i=0;i<jzwInfos.length;i++){
            if(!$.isEmptyObject(jzwInfos[i])){
                dsmjSum += parseFloat(jzwInfos[i].dsmj || 0.0);
                dxmjSum += parseFloat(jzwInfos[i].dxmj || 0.0);
                jrmjSum += parseFloat(jzwInfos[i].jrmj || 0.0);
            }
        }

    }
    var zmjSum = dsmjSum + dxmjSum;
    mini.getByName("dsmjSum").setValue(dsmjSum.toFixed(2));
    mini.getByName("dxmjSum").setValue(dxmjSum.toFixed(2));
    mini.getByName("jrmjSum").setValue(jrmjSum.toFixed(2));
    mini.getByName("zmjSum").setValue(zmjSum.toFixed(2));
}

/**
 * 计算某行总面积
 * @param name
 */
function sumZmj(name){
    var dsmj = mini.getByName(name+"dsmj").getValue() || 0.0;
    var dxmj = mini.getByName(name+"dxmj").getValue() || 0.0;
    var zmj = parseFloat(dsmj) + parseFloat(dxmj);
    mini.getByName(name+"zmj").setValue(zmj.toFixed(2));
}

function delJzwTr(id){
    var $checkTr = $("#"+id).find(":checkbox:checked").parents("tr");
    if($checkTr.length == 0){
        layer.open({
            title: '信息',
            content: "请选择一条数据",
            icon: 2
        });
        return;
    }
    $checkTr.remove();
}

function checkAllTr(e,id){
    if(e.checked){
        $("#"+ id).find(":checkbox:not(:checked)").click();
    }else{
        $("#"+ id).find(":checkbox:checked").click();
    }
}

function submitBuildingForm(){
    form.validate();
    var flag = form.isValid();
    if(!flag){
        var errors = form.getErrorTexts();
        var errorMsg = errors.join("<br/>");
        layer.open({
            title: '信息',
            content: errorMsg,
            icon: 2
        });
    }else{
        var isValidate = buildingInfosValidate();
        if(isValidate){
            layer.confirm('提交后信息不可更改，确定继续吗?', {btn: ['确定', '取消'], title: "提示"}, function () {
                saveBuildingInfos(true);
            });
        }
    }
}

function saveBuildingInfo(){
    form.validate();
    var flag = form.isValid();
    if(!flag){
        var errors = form.getErrorTexts();
        var errorMsg = errors.join("<br/>");
        layer.open({
            title: '信息',
            content: errorMsg,
            icon: 2
        });
    }else{
        saveBuildingInfos();
    }
}

function buildingInfosValidate(flag){
    var data = form.getData();
    var jzwInfos = data.jzwInfos;
    var jzwDetailInfos = data.jzwDetailInfos;
    var jzdhArr = [];
    var errorMsgs = [];
    if(jzwInfos && jzwInfos.length>0) {
        for (var i = 0; i < jzwInfos.length; i++) {
            if (!$.isEmptyObject(jzwInfos[i])) {
                var jzdh = jzwInfos[i].jzdh;
                var msg = "";
                if(jzdhArr.indexOf(jzdh) > -1){
                    msg = jzdh+"建筑物信息重复输入";
                    errorMsgs.push(msg);
                    continue;
                }
                jzdhArr.push(jzdh);
                var jzytArr = jzwInfos[i].jzyt.split(",");
                if(jzwDetailInfos && jzwDetailInfos.length>0) {
                    var index = 0;
                    var zmjSum = 0.0;
                    for (var j = 0; j < jzwDetailInfos.length; j++) {
                        if(jzwDetailInfos[j].jzdh == jzdh){
                            var jzyt = jzwDetailInfos[j].jzyt;
                            if(jzytArr.indexOf(jzyt) < 0 ){
                                if(msg.indexOf("建筑用途不符") < 0){
                                    msg += "建筑用途不符、";
                                }
                            }
                            zmjSum += parseFloat(jzwDetailInfos[j].zmj);
                            index++;
                        }
                    }
                    if(index == 0){
                        msg = jzdh+"缺少建筑物详细信息";
                        errorMsgs.push(msg);
                        continue;
                    }else if(index != jzytArr.length){
                        if(msg.indexOf("建筑用途不符") < 0){
                            msg += "建筑用途不符、";
                        }
                    }
                    var zmj = parseFloat(jzwInfos[i].dsmj) + parseFloat(jzwInfos[i].dxmj);
                    if(zmj.toFixed(2) != zmjSum.toFixed(2)){
                        msg += "面积计算有误、";
                    }
                }else{
                    msg = jzdh+"缺少建筑物详细信息";
                    errorMsgs.push(msg);
                    continue;
                }
                if(msg){
                    msg = jzdh + msg.substring(0,msg.length-1);
                    errorMsgs.push(msg);
                }

            }
        }
    }
    if(errorMsgs.length>0){
        showErrorMsg(errorMsgs.join("<br/>"));
        return false;
    }
    if(flag){
        layer.open({
            title: '信息',
            content: "验证通过",
            icon: 1
        });
    }
    return true;
}

function showErrorMsg(msg){
    layer.open({
        title: '信息',
        content: msg,
        icon: 2
    });
}

function saveBuildingInfos(isSubmit){
    var data = form.getData();
    var jzwxxs = data.jzwInfos;
    var jzwInfos = [];
    var kid = mini.getByName("kid").getValue();
    var index = 1;
    if(jzwxxs && jzwxxs.length>0){
        for(var i=0;i<jzwxxs.length;i++){
            if(!$.isEmptyObject(jzwxxs[i])){
                jzwxxs[i].pid = kid;
                jzwxxs[i].xh = index;
                jzwInfos.push(jzwxxs[i]);
                index++;
            }
        }
    }
    var jzwxqs = data.jzwDetailInfos;
    var jzwDetailInfos = [];
    if(jzwxqs && jzwxqs.length>0){
        for(var i=0;i<jzwxqs.length;i++){
            if(!$.isEmptyObject(jzwxqs[i])){
                jzwxqs[i].pid = kid;
                jzwDetailInfos.push(jzwxqs[i]);
            }
        }
    }
    data.jzwInfos = jzwInfos;
    data.jzwDetailInfos = jzwDetailInfos;
    var successMsg = "";
    var errorMsg = "";
    if(isSubmit){
        data.sftj = "1";
        successMsg = "楼面信息录入成功，编码为<br/><span style='color:green'>" +kid+"</span>";
        errorMsg = "楼面信息录入失败";
    }else{
        successMsg = "楼面信息保存成功";
        errorMsg = "楼面信息保存失败";
    }
    $.ajax({
        type:'POST',
        url: baseDir + "/building/data/saveBuildingInfos",
        contentType: 'application/json',
        dataType:'json',
        data: JSON.stringify(data),
        success: function(data){
            if(data.status == "success"){
                layer.open({
                    title: '信息',
                    content: successMsg,
                    icon: 1,
                    yes: function(index, layero){
                        // layer.close(index);
                        var url = baseDir + "/building/buildingInfo?buildingCode="+buildingCode;
                        window.top.location = url;
                    }
                });
            }else{
                layer.open({
                    title: '信息',
                    content: errorMsg,
                    icon: 2
                });
            }
        },
        error:function(data){
            layer.open({
                title: '信息',
                content: errorMsg,
                icon: 2
            });
        }
    });
}

function dkbhChanged() {
    var dkbh = mini.getByName("dkbh").getValue();
    var trs = $("#jzwxxTbody").find("tr");
    for(var i=0;i<trs.length;i++){
        var index = $(trs[i]).attr("xh") - 1;
        var name = "jzwInfos["+ index +"].dkbh";
        mini.getByName(name).setValue(dkbh);
    }
}

function numValidate(e){
    if (e.isValid) {
        if (e.value<0) {
            e.errorText = "必须输入大于等于0的数";
            e.isValid = false;
        }
    }
}