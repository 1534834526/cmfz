<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {
        $("#title").textbox({
            required: true,
            prompt: "请输入要添加的标题"
        });
        $("#img1").filebox({
            required: true,
            editable: false,
            prompt: "选择文件"
        });


        $("#addBtn").linkbutton({
            text: "添加",
            iconCls: "icon-add",
            onClick: function () {
                $("#addForm").form("submit", {
                    url: "${pageContext.request.contextPath}/banner/addBanner",
                    onSubmit: function () {
                        return $("#addForm").form("validate");
                    },
                    success: function () {
                        $("#addDialog").dialog("close");
                        $("#dg").datagrid("load");
                        $.messager.show({
                            title: "系统提示",
                            msg: "添加成功！！"
                        });
                    }
                })
            }
        });

        $("#resetBtn").linkbutton({
            text: "重置",
            iconCls: "icon-mini-refresh",
            onClick: function () {
                $("#addForm").form("reset");
            }

        });


    });
</script>
<div align="left">
    <form id="addForm" method="post" enctype="multipart/form-data">
        <input id="title" name="title"/><br/>
        <input id="img1" name="file1"/><br/>
        <div>
            <select name="status">
                <option value="Y">展示</option>
                <option value="N">不展示</option>
            </select>
        </div>
        <textarea cols="20" rows="5" name="description">请输入描述信息</textarea><br/>

        <a id="addBtn"/> <a id="resetBtn"/>
    </form>
</div>