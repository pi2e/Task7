<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.Object"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<html>
<head>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	google.load("visualization", "1", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);
	function drawChart() {
<%  List<Object[]> arrayData = (ArrayList<Object[]>)request.getAttribute("arrayData");%>
		
		var data = google.visualization.arrayToDataTable([
	     ['<%=arrayData.get(0)[0]%>', '<%=arrayData.get(0)[1]%>'],
		   
         <% for(int i = 1; i < arrayData.size(); i++) { %>
         
          ['<%=(Date)arrayData.get(i)[0]%>', <%=arrayData.get(i)[1]%>],
          
          <%} %>

		]);
		var options = {
			title : 'Fund Performance'
		};

		var chart = new google.visualization.LineChart(document
				.getElementById('chart_div'));
		chart.draw(data, options);
		}
		
</script>
</head>
<body>
	<div id="chart_div" style="width: 900px; height: 500px;">
	
	</div>
</body>
</html>