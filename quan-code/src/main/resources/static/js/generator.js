$(function () {
    $("#jqGrid").jqGrid({
        url: 'sys/generator/list',
        datatype: "json",
        colModel: [
            {label: '表名', name: 'tableName', width: 100, key: true},
            {label: 'Engine', name: 'engine', width: 70},
            {label: '表备注', name: 'tableComment', width: 100},
            {label: '创建时间', name: 'createTime', width: 100}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 30,
        rowList: [10, 30, 50, 100, 200],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            tableName: null
        }
    },
    methods: {
        query: function () {
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'tableName': vm.q.tableName},
                page: 1
            }).trigger("reloadGrid");
        },
        generator: function () {
            var tableNames = getSelectedRows();
            if (tableNames == null) {
                return;
            }
            this.download(tableNames);
        },

        download: function (tableNames) {
            var url = '/sys/generator/code';
            var xhr = new XMLHttpRequest();
            xhr.open('POST', url, true);    // 也可以使用POST方式，根据接口
            xhr.responseType = "blob";  // 返回类型blob
            xhr.setRequestHeader("Content-type", "application/json");
            // 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
            xhr.onload = function () {
                // 请求完成
                if (this.status === 200) {
                    // 返回200
                    var blob = this.response;
                    var reader = new FileReader();
                    reader.readAsDataURL(blob);  // 转换为base64，可以直接放入a表情href
                    reader.onload = function (e) {
                        // 转换完成，创建一个a标签用于下载
                        var a = document.createElement('a');
                        a.download = 'quan.icu.zip';
                        a.href = e.target.result;
                        $("body").append(a);  // 修复firefox中无法触发click
                        a.click();
                        $(a).remove();
                    }
                }
            };
            var params = JSON.stringify({
                tableNames: tableNames
            })
            // 发送ajax请求
            xhr.send(params);
        }
    }
});

