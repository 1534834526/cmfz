
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false" %>
<script type="text/javascript">

    function urlFormatter(value,row,index){
        if(row.children==null) {
        }
        if(row.children==null){
            return "<audio controls='controls' src=${pageContext.request.contextPath}"+row.url+"></audio>";
        }

    }


    $(function(){
        $('#tg').treegrid({
            fitColumns:true,
            fit:true,
            animate:true,
            url:"${pageContext.request.contextPath}/album/albumQueryAll",
            idField:"id",
            treeField:"title",
            columns:[[
                {field:'title',title:'名字',position:'relative',width:40,},
                {field:'xx',title:"下载/播放",align:'center',width:70,formatter:urlFormatter},
                {field:'size',title:'章节大小',align:'center',width:20},
                {field:'duration',title:'章节时长',align:'center',width:40}
            ]],
            toolbar:[
                {
                   text:"专辑详情",
                    iconCls:"icon-save",
                    handler: function(){

                       var row=$("#tg").treegrid("getSelected");
                        if(row!=null) {
                            if (row.author != null) {
                                $("#dd").dialog({
                                    content: "&nbsp标题：<input disabled type='text' value='" + row.title + "'/><br/>" +
                                        "章节数:<input disabled type='text' value='" + row.count + "'/><br/>" +
                                        "&nbsp头像：<img alt='' disabled width='50px' height:'50px' src=${pageContext.request.contextPath}"+row.url+"/><br/>" +
                                        "&nbsp评分：<input disabled type='text' value='" + row.score + "'/><br/>" +
                                        "&nbsp作者：<input disabled type='text' value='" + row.author + "'/><br/>" +
                                        "&nbsp播音：<input disabled type='text' value='" + row.broadcast + "'/><br/>" +
                                        "&nbsp简介：<input disabled type='text' value='" + row.brief + "'/><br/>" +
                                        "&nbsp日期：<input disabled type='text' value='" + row.pubDate + "'/><br/>",
                                    closed: false
                                });
                            } else {
                                alert("请选择专辑行");
                            }
                        }else{
                            alert("请先选择行");
                        }
                    }

                },{
                    text:"添加专辑",
                    iconCls:"icon-add",
                    handler: function(){
                        $("#albumAddDialog").dialog({
                            title: '添加专辑对话框',
                            width: 200,
                            height: 250,
                            closed: false,
                            cache: false,
                            href: '${pageContext.request.contextPath}/album/addAlbum.jsp',
                            modal: true

                        });
                    }
                },{
                    text:"添加音频",
                    iconCls:"icon-search",
                    handler: function(){
                        var row=$("#tg").treegrid("getSelected");
                        var row1=$("#tg").treegrid("getParent",row.id);
                        if(row!=null && row1==null){
                            $("#chapterAddDialog").dialog({
                                title: '添加音频对话框',
                                width: 200,
                                height: 120,
                                closed: false,
                                cache: false,
                                href: '${pageContext.request.contextPath}/chapter/addChapter.jsp?rid='+row.id,
                                modal: true

                            });
                        }else{
                            alert("请选择专辑行进行添加音频");
                        }
                    }
                },{
                    text:"下载音频",
                    iconCls:"icon-remove",
                    handler: function(){
                        var row=$("#tg").treegrid("getSelected");
                        var row1=$("#tg").treegrid("getParent",row.id);
                        if(row!=null && row1!=null){
                            window.location.href="${pageContext.request.contextPath}/chapter/downloadChapter?musicUrl="+row.url;
                           /* $.ajax({
                                type:"post",
                                url:"",
                                data:",

                                success:function(data){

                                }
                            });*/
                        }else{
                            alert("请选择音频行");
                        }
                    }
                }
            ]

        });

    });

</script>




<%--树形表格--%>
<table id="tg" style="width:600px;height:400px"></table>


<div id="dd"  class="easyui-dialog" title="专辑详情对话框" style="width:250px;height:230px;background-color: ivory"
     data-options="closed: true, iconCls:'icon-save',resizable:true,modal:true">

</div>

<%--专辑对话框--%>
<div id="albumAddDialog"></div>
<%--音频对话框--%>
<div id="chapterAddDialog"></div>