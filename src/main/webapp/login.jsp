<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<html>
<head>
    <title>持名法州后台管理中心</title>

    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"></link>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css"></link>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/common.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        $(function () {

            $("#img1").click(function () {
                $("#img1").prop("src", "${pageContext.request.contextPath}/admin/imageValidate?timestamp=" + new Date());
            });


            //管理员手机号
            $("#phone").textbox({
                required: true,
                validType: "isLength[11]",
                iconCls: "icon-man",
                prompt: "请输入手机号"
            });
            $.extend($.fn.validatebox.defaults.rules, {
                isLength: {
                    validator: function (value, params) {
                        return value.length == params[0];
                    },
                    message: "手机号不是11位！"
                }
            });

            //密码
            $("#password").textbox({
                required: true,
                validType: "equals[6]",
                type: "password",
                prompt: "请输入密码"
            });

            $.extend($.fn.validatebox.defaults.rules, {
                equals: {
                    validator: function (value, param) {
                        return value.length == param[0];
                    },
                    message: '密码必须是6位'
                }
            });

            //  form 表单提交


            $("#loginBtn").linkbutton({
                text: "登录",
                iconCls: "icon-add",
                onClick: function () {
                    $("#loginForm").form("submit", {
                        url: "${pageContext.request.contextPath}/admin/login",
                        onSubmit: function () {
                            return $("#loginForm").form("validate");
                        },
                        success: function (data1) {
                            var data = eval("(" + data1 + ")");

                            if (data == "ValidateCodeError") {
                                $.messager.show({
                                    title: "系统提示",
                                    msg: "验证码错误！！"
                                });
                                $("#loginForm").form("reset");

                            } else if (data == "loginError") {
                                $.messager.show({
                                    title: "系统提示",
                                    msg: "登录失败,用户名或密码错误！！"
                                });
                                $("#loginForm").form("reset");
                            } else if (data == "loginOk") {

                                window.location.href = "${pageContext.request.contextPath}/menu/menuQuery";
                            }
                        }
                    });
                }
            });
        });





    </script>
</head>
<body>

<div class="login">
    <form id="loginForm" method="post">

        <table>
            <tbody>
            <tr>
                <td width="190" rowspan="2" align="center" valign="bottom">
                    <img src="img/header_logo.gif"/>
                </td>
                <th>
                    用户名:
                </th>
                <td>
                    <input id="phone" name="phone" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <th>
                    密&nbsp;&nbsp;&nbsp;码:
                </th>
                <td>
                    <input id="password" name="password" maxlength="20"
                           autocomplete="off"/>
                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <th>验证码:</th>
                <td>
                    <input type="text" name="enCode" class="text captcha" maxlength="4" autocomplete="off"/>
                    <img id="img1" src="${pageContext.request.contextPath}/admin/imageValidate" title="点击更换验证码"/></a>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;
                </td>
                <th>
                    &nbsp;
                </th>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <th>&nbsp;</th>
                <td>
                    <input type="button" class="homeButton" value="" onclick="location.href='..'"><a id="loginBtn"/>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="powered">COPYRIGHT © 2008-2017.</div>
        <div class="link">
            <a href="http://www.chimingfowang.com/">持名佛网首页</a> |
            <a href="http://www.chimingbbs.com/">交流论坛</a> |
            <a href="">关于我们</a> |
            <a href="">联系我们</a> |
            <a href="">授权查询</a>
        </div>
    </form>
</div>
</body>
</html>