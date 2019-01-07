<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<script type="text/javascript">
    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart2 = echarts.init(document.getElementById('main2'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '用户注册量统计分析'
            },
            tooltip: {},
            legend: {
                data: ['柱状', '折线']
            },
            xAxis: {
                data: ["一周内", "两周内", "三周内", "一个月内", "半年内", "一年内"]
            },
            yAxis: {},
            series: [{
                name: '注册量',
                type: 'bar',
                data: []
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart2.setOption(option);


        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/user/queryUserCount",
            dataType: "JSON",
            success: function (data) {
                myChart2.setOption({
                    series: [{
                        name: '柱状',
                        type: 'bar',
                        data: data.data
                    }, {
                        name: '折线',
                        type: 'line',
                        data: data.data
                    }]
                });
            }
        });


        var goEasy = new GoEasy({
            appkey: "BC-317c21a7de5e43a79646b9c6255fd12b"
        });
        goEasy.subscribe({
            channel: "my_channel2",
            onMessage: function (message2) {

                var msg2 = eval("(" + message2.content + ")");

                myChart2.setOption({
                    series: [{
                        name: '柱状',
                        type: 'bar',
                        data: msg2.data2
                    }, {
                        name: '折线',
                        type: 'line',
                        data: msg2.data2
                    }]
                });


            }
        });
    });

</script>


<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main2" style="width: 600px;height:400px;"></div>



