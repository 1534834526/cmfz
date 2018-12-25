<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="../themes/icon.css">

    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="../js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/echarts.js"></script>
    <script type="text/javascript" src="../js/china.js"></script>
    <style type="text/css">
        #m2 {
            position: relative;
            left: 40px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            /*左边菜单查询显示*/
            $.ajax({
                type: "get",
                url: "${pageContext.request.contextPath}/menu/menuQuery",
                dataType: "JSON",
                success: function (data) {
                    //console.log(data);
                    $.each(data, function (index, menu1) {
                        var a = "";
                        $.each(menu1.list, function (index, menu2) {
                            a += "<div><a class='easyui-linkbutton' onclick=\"addTab('" + menu2.title + "','" + menu2.iconcls + "','" + menu2.url + "')\" data-options=\"text:'" + menu2.title + "',iconCls:'" + menu2.iconcls + "'\"/></div>";
                        });
                        $("#aa").accordion("add", {
                            title: menu1.title,
                            content: a,
                            iconCls: menu1.iconcls,
                            selected: false
                        });
                    });

                }
            });

        });

        //点击左边触发的函数，创建选项卡，获得选项卡标题，获得选项卡的面板内容(由另一个页面提供)。
        function addTab(title, iconcls, url) {

            //选项卡的名字，图标，url（是填充到选项卡面板的jsp页面，jsp中请求后台数据）
            var bool = $("#tt").tabs("exists", title);
            if (bool) {
                $('#tt').tabs("select", title);
            } else {
                /*添加一个选项卡，选项卡的标题，内容(选项卡=标题+内容(就是面板))*/
                $('#tt').tabs('add', {
                    title: title,
                    selected: true,
                    iconCls: iconcls,
                    //加载选项卡的面板内容
                    href: "${pageContext.request.contextPath}/main" + url,
                    closable: true
                });
            }

        }
    </script>

</head>



<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:xxxxx
        &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="#"
                                                                                                              class="easyui-linkbutton"
                                                                                                              data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>


<div data-options="region:'south',split:true" style="height: 40px;background: #5C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>


<div data-options="region:'west',title:'导航菜单',split:true"
     style="width:220px;">
    <div id="aa" class="easyui-accordion" style="width:300px;height:550px;">

    </div>

</div>


<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;">

        </div>
    </div>
</div>


</body>
</html>