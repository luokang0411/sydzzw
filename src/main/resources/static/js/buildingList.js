var g_columns = [{
    field: 'num',
    title: '序号',
    width: '5%',
    align: 'center',
    formatter: function (value, row, index) {
        var pageSize=$('#resultList').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
        var pageNumber=$('#resultList').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
        return pageSize * (pageNumber - 1) + index + 1;//返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
    }
}, {
    field: 'xmmc',
    title: '项目名称',
    width: '20%',
    align: 'center',
    valign: 'middle',
    formatter: function (value, row, index) {
        if (!value) {
            return "-";
        }
        return "<a title='" + value + "' onclick='openDetailInfos(\"" + row.kid + "\")'>" + value + "</a>";
    }
}, {
    field: 'jsdw',
    title: '建设单位',
    width: '15%',
    align: 'center',
    valign: 'middle',
    formatter: addTitle,
}, {
    field: 'xmdz',
    title: '项目地址',
    width: '25%',
    align: 'center',
    valign: 'middle',
    formatter: addTitle,
}, {
    field: 'dkbh',
    title: '地块编号',
    width: '15%',
    align: 'center',
    valign: 'middle',
    formatter: addTitle,
}, {
    field: 'kid',
    title: '编码',
    width: '20%',
    align: 'center',
    valign: 'middle',
    formatter: addTitle,
}];

$(function () {
    initBuildingTable();
});

function initBuildingTable() {
    $("#resultList").bootstrapTable('destroy');
    $('#resultList').bootstrapTable({
        url: baseDir + "/building/data/getBuildingInfoList",
        method: 'post',
        striped: true,
        cache: false,
        pagination: true,
        sidePagination: "server",
        search: false,
        strictSearch: false,
        showColumns: false,
        showRefresh: false,
        clickToSelect: true,
        singleSelect: false,
        uniqueId: "kid",
        showToggle: false,
        pageNumber: 1,
        pageSize: 10,
        pageList: [5,10,20],
        formatNoMatches: function () {
            return "没有找到记录";
        },
        queryParams : function(param) {
            var page = {};
            page.begin = param.offset;
            page.length = param.limit;
            return page;
        },
        columns: g_columns
    });
}

function addTitle(value, row, index) {
    if (!value) {
        return "-";
    }
    return "<span title='" + value + "'>" + value + "</span>";
}

function openDetailInfos(kid) {
    var url = baseDir + "/building/buildingInfo";
    if (kid) {
        url = url + "?buildingCode=" + kid;
    }
    window.open(url);
}

function exportBuildInfos() {
    window.location.href = baseDir + "/building/data/exportBuildingInfos";
}