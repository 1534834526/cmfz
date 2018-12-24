<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script type="text/javascript">

    $(function () {

        var rid = ${param.rid}

        $("#title1").textbox({
            required: true,
            prompt: "请输入音频标题"
        });
        $("#music").filebox({
            required: true,
            editable: false,
            prompt: "请选择音频"
        });


        $("#addBtn2").linkbutton({
            text: "添加",
            iconCls: "icon-add",
            onClick: function () {
                $("#addForm2").form("submit", {
                    url: "${pageContext.request.contextPath}/chapter/addChapter",
                    onSubmit: function () {
                        return $("#addForm2").form("validate");
                    },
                    success: function () {
                        $("#chapterAddDialog").dialog("close");
                        $("#tg").treegrid("load");
                        $.messager.show({
                            title: "系统提示",
                            msg: "添加成功！！"
                        });
                    },
                    queryParams:{"aid":rid}

                })
            }
        });

        $("#resetBtn2").linkbutton({
            text: "重置",
            iconCls: "icon-mini-refresh",
            onClick: function () {
                $("#addForm2").form("reset");
            }

        });


    });
</script>
<div align="left">
    <form id="addForm2" method="post" enctype="multipart/form-data">
        <input id="title1" name="title"/><br/>
        <input id="music" name="file1"/>
        <a id="addBtn2"/> <a id="resetBtn2"/>
    </form>
</div>