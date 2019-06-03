<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta name="viewport" content="width=device-width, user-scalable=no"/><!-- "position: fixed" fix for Android 2.2+ -->
        <link rel="stylesheet" href="resources/styleschedule.css" type="text/css" media="screen"/>
        <script type="text/javascript" src="resources/redips-drag-min.js"></script>
        <script type="text/javascript" src="resources/script.js"></script>
        <title>Class Schedule</title>
    </head>
	<body>
            <div style="background-color:#eee;padding:10px;text-align:center;font-size:20px;font-weight:bold">Class Schedule</div>
            <br/>
            <div id="main_container">
                <!-- tables inside this DIV could have draggable content -->
                <div id="redips-drag">
                    <!-- left container (table with subjects) -->
                    <div id="left">
                        <table id="table1">
                            <colgroup>
                                <col width="250"/>
                            </colgroup>
                            <tbody>
                                <tr><td class="dark"><div id="sr" class="redips-drag redips-clone sr">Srpski jezik</div></td></tr>
                                <tr><td class="dark"><div id="ma" class="redips-drag redips-clone ma">Matematika</div></td></tr>
                                <tr><td class="dark"><div id="sn" class="redips-drag redips-clone sn">Svet oko nas</div></td></tr>
                                <tr><td class="dark"><div id="lk" class="redips-drag redips-clone lk">Likovna kultura</div></td></tr>
                                <tr><td class="dark"><div id="fv" class="redips-drag redips-clone fv">Fizicko vaspitanje</div></td></tr>
                                <tr><td class="dark"><div id="mk" class="redips-drag redips-clone mk">Muzicka kultura</div></td></tr>
                                <tr><td class="dark"><div id="sj" class="redips-drag redips-clone sj">Strani jezik</div></td></tr>
                                <tr><td class="dark"><div id="vn" class="redips-drag redips-clone vn">Verska nastava</div></td></tr>
                                <tr><td class="dark"><div id="gv" class="redips-drag redips-clone gv">Gradjansko vaspitanje</div></td></tr>
                                <tr><td class="redips-trash" title="Trash">Trash</td></tr>
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
								<!-- if checkbox is checked, clone school subjects to the whole table row  -->
								<td>
									
								</td>
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
				<input type="button" value="Save" class="button" onclick="redips.save()" title="Save timetable"/>
			</div> 
			</div><!-- drag container -->
		</div><!-- main container -->
	</body>
</html>