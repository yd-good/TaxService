<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Calendar calendar=Calendar.getInstance();
	int currentYear=calendar.get(Calendar.YEAR);//获取当前年份
    List<Integer> yearList=new ArrayList<Integer>();
    for (int i=currentYear-4;i<=currentYear;i++)
        yearList.add(i);
    request.setAttribute("yearList",yearList);
%>

<!DOCTYPE HTML>
<html>
  <head>
    <%@include file="/common/header.jsp"%>
    <title>年度投诉统计图</title>
  </head>
<script type="text/javascript" src="${basePath}js/fusioncharts/fusioncharts.charts.js"></script>
<script type="text/javascript" src="${basePath}js/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript" src="${basePath}js/fusioncharts/themes/fusioncharts.theme.fint.js"></script>
<script type="text/javascript">

</script>
  <body>
  	<br>
    <s:select id="year" list="#request.yearList" onchange="doAnnualStatistic()"></s:select>
    <br>
    <div id="chartContainer"></div>
  </body>

<script type="text/javascript">
    $(document).ready(doAnnualStatistic());
    function doAnnualStatistic() {
        var currentYear=$("#year option:selected").val();
        $.ajax({
            url:"${basePath}/taxService/complain_getAnnualStatisticData.action",
            data:{"year":currentYear},
            type: "post",
            dataType:"json",
            success: function(data){
                if(data != null && data != "" && data != undefined){
                    var revenueChart = new FusionCharts({
                        "type": "line",
                        "renderAt": "chartContainer",
                        "width": "600",
                        "height": "400",
                        "dataFormat": "json",
                        "dataSource":  {
                            "chart": {
                                "caption": currentYear + " 年度投诉数统计图",
                                "xAxisName": "月  份",
                                "yAxisName": "投  诉  数",
                                "theme": "fint"
                            },
                            "data": data.charData
                        }
                    });
                    revenueChart.render();
                } else {
                    alert("统计投诉数失败！");
                }
            },
            error: function(){alert("统计投诉数失败！");}
        });
    }
</script>
</html>
