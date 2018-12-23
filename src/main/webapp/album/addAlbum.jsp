<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">

    $(function () {
        $("#title").textbox({
            required: true,
            prompt: "请输入标题"
        });
        $("#img1").filebox({
            required: true,
            editable: false,
            prompt: "选择头像"
        });
        $("#score").combobox({
            required: true,
            editable:false,
            prompt: "请选择评分(10分制)",
            valueField:"id",
            textField:"text",
            panelHeight:120,
            data: [{
                id: '2',
                text: '1星',
                selected:true
            },{
                id: '4',
                text: '2星'
            },{
                id: '6',
                text: '3星'
            },{
                id: '8',
                text: '4星'
            },{
                id: '10',
                text: '5星'
            }],

        });
        $("#author").textbox({
            required: true,
            prompt: "请输入作者"
        });
        $("#broadcast").textbox({
            required: true,
            prompt: "请输入播音"
        });


        $("#addBtn1").linkbutton({
            text: "添加",
            iconCls: "icon-add",
            onClick: function () {
                $("#addForm1").form("submit", {
                    url: "${pageContext.request.contextPath}/album/addAlbum",
                    onSubmit: function () {
                        return $("#addForm1").form("validate");
                    },
                    success: function () {
                        $("#albumAddDialog").dialog("close");
                        $("#tg").treegrid("load");
                        $.messager.show({
                            title: "系统提示",
                            msg: "添加成功！！"
                        });
                    }
                })
            }
        });

        $("#resetBtn1").linkbutton({
            text: "重置",
            iconCls: "icon-mini-refresh",
            onClick: function () {
                $("#addForm1").form("reset");
            }

        });


    });
</script>
<div align="left">
    <form id="addForm1" method="post" enctype="multipart/form-data">
        <input id="title" name="title"/><br/>
        <input id="img1" name="file1"/>
        <input id="score" name="score"/><br/>

        <input id="author" name="author"/><br/>
        <input id="broadcast" name="broadcast"/><br/>

        <textarea cols="20" rows="5" name="brief">请输入简介</textarea><br/>

        <a id="addBtn1"/> <a id="resetBtn1"/>
    </form>
</div>