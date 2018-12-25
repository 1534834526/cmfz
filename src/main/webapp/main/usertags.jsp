<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">

    $(function () {
        // 1、基于准备好的dom，初始化echarts实例
        var chart = echarts.init(document.getElementById('main'));

        option = {
            title: {
                text: '持明法州APP注册用户省份分布图',
                subtext: '<%=new Date().toLocaleString()%>最新数据',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['用户分布图']
            },
            visualMap: {
                min: 0,
                max: 2500,
                left: 'left',
                top: 'bottom',
                text: ['高', '低'],           // 文本，默认为数值文本
                calculable: true
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            series: [
                {
                    name: '用户分布图',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: []

                }
            ]

        }


        chart.setOption(option);

        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/user/queryProvinceUserCount",
            dataType: "JSON",
            success: function (data) {

                chart.setOption({
                    series: [
                        {
                            name: '用户分布图',
                            type: 'map',
                            mapType: 'china',
                            roam: false,
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data: data.data

                        }
                    ]
                });
            }
        });


    });


    /*    chart.setOption({
            //左上角
            title: {
                text: '用户分布图'
            },
            //鼠标显示提示框,默认
            tooltip: {},
            //每个系列对应一个这个标记
            legend: {
                data: ['销量1']
            },

            series: [{
                name:"持明法州APP用户分布图",
                type: 'map',
                map: '中国地图',
                data:''
            }]
        })*/






    /*     // 指定图表的配置项和数据
         var option = {
             //左上角
             title: {
                 text: 'ECharts 入门示例'
             },
             //鼠标显示提示框,默认
             tooltip: {},
             //每个系列对应一个这个标记
             legend: {
                 data:['销量1']
             },
             //系列横坐标
             xAxis: {
                 data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
             },
             //系列纵坐标,默认根据系列的data给出
             yAxis: {},
             //系列，整个柱状图是一个系列包括柱name,类型，每个的销量
             series: [{
                 name: '销量1',
                 type: 'map',
                 data: [5, 20, 36, 10, 10, 20]
             }]
         };


         //2、 使用刚指定的配置项和数据显示图表。
         myChart.setOption(option);
     });*/

</script>


<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>


