<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta name="viewport" content="width=device-width, user-scalable=no"/><!-- "position: fixed" fix for Android 2.2+ -->
        <link rel="stylesheet" href="../resources/styleschedule.css" type="text/css" media="screen"/>
        <link href="<c:url value="../resources/main.css" />" rel="stylesheet">
        <script type="text/javascript" src="../resources/redips-drag-min.js"></script>
        <script type="text/javascript" src="../resources/script.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>Class Schedule</title>
    </head>
    <body>
        <%@include file="navbar_admin.jsp" %>
            <div style="background-color:#eee;padding:10px;text-align:center;font-size:20px;font-weight:bold">Class Schedule</div>
            <br/>
            <div id="main_container">
                <select size="1" id="classOptions">
                        <option value="0">--- SELECT CLASS ---</option>
                        <c:forEach items="${allClassEntities}" var="classes">
                            <option value=${classes.getClassId()}>${classes.getClassName()}</option>
                        </c:forEach>
                    </select>
                <!-- tables inside this DIV could have draggable content -->
                <div id="redips-drag">
                    <!-- left container (table with subjects) -->
                    <div id="left">
                        <table id="table1">
                            <colgroup>
                                <col width="250"/>
                            </colgroup>
                            <tbody>
                                <c:forEach items="${allSubjectsEntities}" var="subject">
                                <tr>
                                    <td class="dark">
                                        <div id="${subject.getSubjectId()}" class="redips-drag redips-clone sub${subject.getSubjectId()}">${subject.getSubjectName()}</div>
                                    </td>      
                                </tr>
                                </c:forEach> 
                            </tbody>
                        </table>
                        </div><!-- left container -->
                        <!-- right container -->
                            <div id="right">
                                <table id="table2">
                                    <colgroup>
                                        <col width="130"/>
                                        <col width="180"/>
                                        <col width="180"/>
                                        <col width="180"/>
                                        <col width="180"/>
                                        <col width="180"/>
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <td></td>
                                            <td class="redips-mark dark">PONEDELJAK</td>
                                            <td class="redips-mark dark">UTORAK</td>
                                            <td class="redips-mark dark">SREDA</td>
                                            <td class="redips-mark dark">CETVRTAK</td>
                                            <td class="redips-mark dark">PETAK</td>
                                        </tr>
                                        <tr>
                                            <td class="redips-mark dark">PRVI CAS</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td class="redips-mark dark">DRUGI CAS</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td class="redips-mark dark">TRECI CAS</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td class="redips-mark dark">CETVRTI CAS</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td class="redips-mark dark">PETI CAS</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td class="redips-mark dark">SESTI CAS</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div><!-- right container -->
                            <div class="button_container">
                                <input type="button" value="Save" class="button" title="Save timetable" onclick="save();"/>
                            </div> 
                </div><!-- drag container -->
            </div><!-- main container -->
        <script type="text/javascript">
            function save(){
                var classEntity = document.getElementById("classOptions").value;
                var table2 = document.getElementById("table2");
                var classOrders = table2.getElementsByTagName("tr");
                var subject;
                for (var i = 1; i < classOrders.length; i++) {
                    var daysOfTheWeek = classOrders[i].getElementsByTagName("td");
                    var classOrder = i;
                    for (var j = 1; j < daysOfTheWeek.length; j++) {
                        var dayOfTheWeek = j;
                        var subjects = daysOfTheWeek[j].childNodes[0];
                            if(subjects === undefined){
                                subject = null;
                            } else {
                                subject = subjects.id;
                            }
                            
                          var data = '{"classEntity":"'+Number(classEntity)+'"}';  
//                        var datas = [Number(classEntity), classOrder, dayOfTheWeek, Number(subject)];





//                        console.log(datas);



                    }

                }


            }    
                </script>
                
	</body>
</html>