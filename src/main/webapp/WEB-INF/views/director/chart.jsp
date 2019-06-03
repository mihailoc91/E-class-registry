<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chart</title>
        <link href="<c:url value="../resources/main.css" />" rel="stylesheet">
    </head>
    <body><%@include file="navbar_director.jsp" %>
        <h1 id="provera">Welcome to chart page</h1>
        
        <style>
#chartdiv {
  width: 100%;
  height: 650px;
}
</style>
        <ul>
            <c:forEach items="${allSubjects}" var="subject">
            <li><a href="<c:url value="/director/chartDetail?subject=${subject.getSubjectId()}"/>">${subject.getSubjectName()}</a></li>
            </c:forEach>
            <li><a href="<c:url value="/director/chartSchool"/>">All Subjects GDP</a></li>
            <li class="log">
            </li>
        </ul>

<!-- Resources -->
<script src="https://www.amcharts.com/lib/4/core.js"></script>
<script src="https://www.amcharts.com/lib/4/charts.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/animated.js"></script>

<!-- Chart code -->
<script>
am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
var chart = am4core.create("chartdiv", am4charts.XYChart3D);

// Add data
chart.data = [
        <c:forEach items="${chartClassesAndGrades}" var="className">
  {                  
  "className": "${className.getClassName()}",
  "sumOfGrades": ${className.getSumOfGrades()}
}, </c:forEach>
];

// Create axes
let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "className";
categoryAxis.renderer.labels.template.rotation = 270;
categoryAxis.renderer.labels.template.fontWeight = "bold";
categoryAxis.renderer.labels.template.hideOversized = false;
categoryAxis.renderer.minGridDistance = 20;
categoryAxis.renderer.labels.template.horizontalCenter = "right";
categoryAxis.renderer.labels.template.verticalCenter = "middle";
categoryAxis.tooltip.label.rotation = 270;
categoryAxis.tooltip.label.horizontalCenter = "right";
categoryAxis.tooltip.label.verticalCenter = "middle";
categoryAxis.title.text = "Classes";
categoryAxis.title.fontWeight = "bold";
categoryAxis.title.fontSize = 25;

let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());

valueAxis.title.text = "Grade Point Average ${subjectDto.getSubjectName()}";
valueAxis.title.fontWeight = "bold";
valueAxis.title.fontSize = 25;

valueAxis.max = 5;
valueAxis.min = 0;
// Create series
var series = chart.series.push(new am4charts.ColumnSeries3D());
series.dataFields.valueY = "sumOfGrades";
series.dataFields.categoryX = "className";
series.name = "Grades";
series.tooltipText = "{categoryX}: [bold]{valueY}[/]";
series.columns.template.fillOpacity = .8;

var columnTemplate = series.columns.template;
columnTemplate.strokeWidth = 2;
columnTemplate.strokeOpacity = 1;
columnTemplate.stroke = am4core.color("#FFFFFF");

columnTemplate.adapter.add("fill", (fill, target) => {
  return chart.colors.getIndex(target.dataItem.index);
});

columnTemplate.adapter.add("stroke", (stroke, target) => {
  return chart.colors.getIndex(target.dataItem.index);
});

chart.cursor = new am4charts.XYCursor();
chart.cursor.lineX.strokeOpacity = 0;
chart.cursor.lineY.strokeOpacity = 0;

}); // end am4core.ready()
</script>

<!-- HTML -->
<div id="chartdiv"></div>
    </body>
</html>
