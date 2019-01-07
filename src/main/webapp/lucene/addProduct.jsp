<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<html>

<head>

    <%-- <link  rel="stylesheet"  href="css/jquery-ui-1.9.2.custom.css"></link>--%>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>

    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>

    <%--<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.js"></script>
    <script type="text/javascript" src="js/jquery.ui.datepicker-zh-CN.js"></script>
    --%>
    <script type="text/javascript">
        $(function () {
            /* $("#birthday").datepicker({
                 dateFormat: "yy-mm-dd"
             });*/
            $("#birthday").datebox({
                required: true,
                editable: false
            });

        });

    </script>
</head>
<body>


<h1>添加商品</h1>
<form action="${pageContext.request.contextPath}/search/insertProduct" enctype="multipart/form-data" method="post">
    商品名称:<input type="text" name="productName"><br/>
    商品价格:<input type="text" name="productPrice"><br/>
    商品描述:<input type="text" name="description"><br/>
    商品图片:<input type="file" name="file1"><br/>
    商品状态:<select name="status">
    <option value="1">上架</option>
    <option value="0">下架</option>
</select>
    商品上产日期:<input id="birthday" type="text" name="date"><br/>
    商品产地:<input type="text" name="place"><br/>
    <input type="submit" value="提交"><br/>
</form>


</body>
</html>
