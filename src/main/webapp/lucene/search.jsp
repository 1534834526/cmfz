<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="always" name="referrer">
    <title>百度一下，你就知道</title>
    <style id="style_super_inline">
        html {
            color: #000;
            overflow-y: scroll;
            overflow: -moz-scrollbars
        }

        body, button, input, select, textarea {
            font: 12px arial
        }


        .s_lm_hide {
            display: none !important
        }


        .s-lite-version #m {
            padding-top: 125px
        }

        #head_wrapper .s-p-top {
            height: 61.8%;
            min-height: 181px;
            position: relative;
            z-index: 0;
            text-align: center
        }

        #s_lg_img {
            position: absolute;
            bottom: 10px;
            left: 50%;
            margin-left: -135px
        }

        #head_wrapper .s-word-top {
            margin-top: -1px;
            margin-bottom: 11px
        }

        #form {
            z-index: 1
        }

        #s_lm_wrap {
            position: absolute;
            margin-left: -447px;
            bottom: 0;
            left: 50%;
            z-index: 0;
            height: 30px;
            width: 895px;
            line-height: 30px;
            text-align: center
        }

        #lm {
            color: #666;
            height: 15px;
            line-height: 16px;
            padding: 7px 0
        }

        #lm a {
            text-decoration: underline;
            color: #666
        }

        #nv {
            margin: 0 0 5px;
            _margin-bottom: 4px;
            padding: 2px 0 0;
            text-align: left;
            text-indent: 50px
        }

        #nv a, #nv b {
            margin-left: 19px
        }

        #nv a, #nv b, .btn, #lk {
            font-size: 14px
        }

        .s_form {
            width: 641px;
            height: 100%;
            min-height: 293px;
            margin: 0 auto 0 auto;
            text-align: left;
            z-index: 100
        }

        .s-down .s_form {
            padding-left: 0;
            margin-top: 0;
            min-height: 0
        }

        .s_form .tools {
            position: absolute;
            right: -55px
        }

        .s_form_wrapper {
            height: 100%
        }

        #head_wrapper.s-down #kw, #kw {
            width: 500px;
            height: 20px;
            padding: 9px 7px;
            font: 16px arial;
            border: 1px solid #b8b8b8;
            border-bottom: 1px solid #ccc;
            border-right: 0;
            vertical-align: top;
            outline: none;
            box-shadow: none
        }

        .s-skin-hasbg .self-btn {
            background: url(https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/spis7_d7c9959e.png) no-repeat #fff
        }

        .btn {
            color: white;
            background-color: #38f;
            width: 102px;
            height: 38px;
            font-size: 16px;
            border: 0
        }

    </style>
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/icon.css">
    <link rel="stylesheet" id="s_superplus_css_lnk" type="text/css" href="../index_files/super_min_0cb4b166.css">
    <link rel="stylesheet" type="text/css" href="../index_files/card_min_e8bcf60d.css">
    <link rel="stylesheet" href="../index_files/ubase_83c8f0ba.css">
    <link rel="stylesheet" href="../index_files/mt_min_d0e7c6d2.css">
    <link rel="stylesheet" href="../index_files/nsguide_a8cbc2e7.css">
    <link rel="stylesheet" href="../index_files/super_ext_c02dfc40.css">


    <script type="text/javascript" src="../index_files/jquery.js"></script>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#search").linkbutton({
                text: "搜索一下",
                onClick: function () {
                    $("#form1").form("submit", {
                        url: "${pageContext.request.contextPath}/search/searchProduct",
                        onSubmit: function () {
                            return $("#form1").form("validate");
                        },
                        success: function (data) {
                            var data1 = eval("(" + data + ")");

                            /* $.each(data,function(idx,d){
                                /!* $("#tbody1").append("<tr><td>"+data[i].id+"</td><td>"+data[i].productName+"</td></tr>");*!/
                                 console.log(idx);
                             })*/

                            for (var i = 0; i < data1.length; i++) {
                                $("#tbody1").append("<tr><td>" + data1[i].id + "</td><td>" + data1[i].productName + "</td><td>" + data1[i].productPrice + "</td><td>" + data1[i].description + "</td><td><img width=80 height=20 src=${pageContext.request.contextPath}" + data1[i].productImg + "/></td><td>" + data1[i].status + "</td><td>" + data1[i].date + "</td><td>" + data1[i].place + "</td></tr>");

                            }
                        }

                    });
                }

            });

        })
    </script>
<body>
<div id="head_wrapper" class="s-isindex-wrap head_wrapper s-title-img ">
    <div id="s_fm" class="s_form">
        <div class="s_form_wrapper" id="s_form_wrapper">
            <div id="lg" class="s-p-top">
                <img id="s_lg_img" src="../index_files/logo.png">
            </div>
            <form id="form1" class="fm">
                <input type="text" class="s_ipt" name="param" id="kw" maxlength="100" autocomplete="off">
                <a id="search" class="easyui-linkbutton"/>
            </form>
        </div>
    </div>
</div>


<hr/>

<table align="center" border="1" bgcolor="#6495ed" cellpadding="1" cellspacing="1">
    <thead>
    <th>id</th>
    <th>productName</th>
    <th>productPrice</th>
    <th>description</th>
    <th>productImg</th>
    <th>status</th>
    <th>date</th>
    <th>place</th>
    </thead>
    <tbody id="tbody1">

    </tbody>
</table>
</body>
</html>