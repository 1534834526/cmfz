<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<script type="text/javascript">
    $(function () {
        $("#dg").edatagrid({

            pagination: true,
            pageSize: 3,
            pageList: [3, 5, 7, 9],
            fitColumns: true,
            fit: true,
            singleSelect: false,
            ctrlSelect: true,
            autoSave: true,//设置后,自动发updateUrl修改数据到数据库
            updateUrl: "${pageContext.request.contextPath}/banner/updateBanner",
            url: "${pageContext.request.contextPath}/banner/bannerQuery",
            columns: [[
                {field: 'title', title: '标题', align: 'center', width: 100},
                {field: 'imgPath', title: '图片', align: 'center', width: 100},
                {
                    field: 'status', title: '状态', align: 'center', width: 100, align: 'right',
                    editor: {
                        type: 'text',
                        options: {
                            required: true
                        }
                    }
                },
                {field: 'pubDate', title: '上传时间', align: 'center', width: 100, align: 'right'},
                {field: 'description', title: '描述', align: 'center', width: 100, align: 'right'}
            ]],
            toolbar: [{
                iconCls: 'icon-add',
                text: "添加",
                handler: function () {
                    $("#addDialog").dialog({
                        title: '添加对话框',
                        width: 400,
                        height: 200,
                        closed: false,
                        cache: false,
                        href: '${pageContext.request.contextPath}/banner/addBanner.jsp',
                        modal: true

                    });
                }
            }, '-', {
                iconCls: 'icon-edit',
                text: "修改",
                handler: function () {
                    //获得选中行，只使某列可编辑
                    var row = $("#dg").datagrid("getSelected");
                    if (row != null) {

                        var index = $('#dg').datagrid("getRowIndex", row);
                        $('#dg').edatagrid("editRow", index);
                    } else {
                        alert("未选中行");
                    }
                }

            }, '-', {
                iconCls: 'icon-remove',
                text: "删除",
                handler: function () {
                    var rows = $("#dg").datagrid("getSelections");
                    var ids = [];
                    $.each(rows, function (idx, banner) {
                        ids.push(banner.id);
                    })
                    console.log(ids);
                    $.messager.confirm("删除对话框", "您确认要删除吗？", function (r) {
                        if (r) {

                            $.get(
                                "${pageContext.request.contextPath}/banner/deleteBanner?ids=" + ids,

                                function (result) {
                                    $.messager.show({
                                        title: "系统提示",
                                        msg: "删除成功！！"
                                    });
                                    $("#dg").datagrid("reload");
                                }
                            );
                        }
                    });

                }

            }, '-', {
                iconCls: 'icon-save',
                text: "保存",
                handler: function () {
                    $("#dg").edatagrid("saveRow");
                }
            }],

            view: detailview,
            //自定义一个表格用于显示这一行的信息，行下标，行对象
            detailFormatter: function (rowIndex, rowData) {
                return '<table border=1><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.imgPath + '" style=height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>标题: ' + rowData.title + '</p>' +
                    '<p>状态: ' + rowData.status + '</p>' +
                    '<p>上传时间: ' + rowData.pubDate + '</p>' +
                    '<p>描述: ' + rowData.description + '</p>' +
                    '<p>图片路径: ' + rowData.imgPath + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });


    });

</script>
<%--表格--%>
<div id="dg"></div>
<%--对话框--%>
<div id="addDialog"></div>