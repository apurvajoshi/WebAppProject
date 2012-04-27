<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>Untitled Document</title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<!-- TemplateEndEditable -->
<style type="text/css">
@import url("stylesheet.css");
</style>
	
<link rel="stylesheet" href="css/jquery.ui.all.css"/>
	<script src="javascript/jquery-1.7.2.js"></script>
	<script src="javascript/jquery.ui.core.js"></script>
	<script src="javascript/jquery.ui.widget.js"></script>
	<script src="javascript/jquery.ui.datepicker.js"></script>
	<link rel="stylesheet" href="css/demos.css"/>
<script>
$(function() {
	$( "#startdatepicker" ).datepicker({
		showOn: "button",
		buttonImage: "images/calendar.gif",
		buttonImageOnly: true
	});
});
	</script>
	<script>
$(function() {
	$( "#enddatepicker" ).datepicker({
		showOn: "button",
		buttonImage: "images/calendar.gif",
		buttonImageOnly: true
	});
});
	</script>
	<script>
	function selectDropDownList(num1, num2, num3, num4)
	{
		var selObj1 = document.getElementById('startHour');
		selObj1.selectedIndex = num1;
			
		var selObj2 = document.getElementById('startMins');
		selObj2.selectedIndex = num2;
		
		var selObj3 = document.getElementById('endHour');
		selObj3.selectedIndex = num3;
		
		var selObj4 = document.getElementById('endMins');
		selObj4.selectedIndex = num4;
	}	
	</script>
</head>

<body onload="MM_preloadImages('images/event1.png','images/viewevents1.png','images/tickets1.png'); selectDropDownList(${form.startHour},${form.startMins},${form.endHour},${form.endMins})">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="50" valign="middle" bgcolor="#3B5998"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="14%">&nbsp;</td>
        <td width="72%" class="banner" valign="middle"><span style="color:#FFF">WatzUpTonight</span></td>
        <td width="14%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td></td>
  </tr>
  <tr>
    <td>
    <form method="post" action="createEvent.do" enctype="multipart/form-data">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      	<tr>
        	<td width="14%">&nbsp;</td>
        	<td width="72%">
        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
          			<tr>
			            <td class="discover"><br/>Create Event<br/><br/>
			                1. Add your event details!!!
			            </td>
          			</tr>
          			<tr>
			            <td>
			            	<jsp:include page="error-list.jsp" />
			            </td>
          			</tr>
          			<tr>
            			<td align="left"><br />
							<table width="600px" border="0" cellspacing="0" cellpadding="0">
							  <tr>
							    <td width="125" class="register_label">Event Title :</td>
							    <td width="475" colspan="3"><input type="text" name="title" value="${form.title}" /></td>
							  </tr>
							  <tr>
							    <td class="register_label">Event Details :</td>
							    <td colspan="3"><textarea name="desc" rows="5" value="${form.desc}"></textarea></td>
							  </tr>
							  <tr>
							    <td class="register_label">Upload Event Picture:</td>
							    <td colspan="3"><input type="file" name="file" value="${form.file}" /></td>
							  </tr>
							  <tr>
							    <td>&nbsp;</td>
							    <td colspan="3">&nbsp;</td>
							  </tr>
							  <tr>
							    <td class="register_label">Location :</td>
							    <td colspan="3"><input type="text" name="location" value="${form.location}" /></td>
							  </tr>
							  <tr>
							    <td class="register_label">City :</td>
							    <td colspan="3"><input type="text" name="city" value="${form.city}" /></td>
							  </tr>
							  <tr>
							    <td class="register_label">&nbsp;</td>
							    <td colspan="3">&nbsp;</td>
							  </tr>
							  <tr>							
							  </tr>
							  <tr>
							    <td>&nbsp;</td>
							    <td colspan="3">&nbsp;</td>
							  </tr>
							  <tr>
							    <td class="register_label">Event Start Date :</td>
							    <td><input type="text" name="startDate" id="startdatepicker" value="${form.startDate}" /></td>
							    <td><span class="register_label">Event Start Time :</span></td>
							    <td>
								    <select name="startHour" value="${form.startHour}">
								    <%
								    	for(int j=0; j<23; j++) {
								    %>
								      <option value="<%=j%>"><%=j %></option> 
								    <% 
								    	}
								    %>  
								    </select>
								    &nbsp;
								    <select name="startMins" value="${form.startMins}">
								    <%
								    	for(int j=0; j<60; j+=15) {
								    %>
								      <option value="<%=j%>"><%=j %></option> 
								    <% 
								    	}
								    %>  
								    </select>
								    							    
							    </td>
							  </tr>
							  <tr>
							    <td class="register_label">Event End Date :</td>
							    <td><input type="text" name="endDate" id="enddatepicker" value="${form.endDate}" /></td>
							    <td><span class="register_label">Event End Time :</span></td>
							    <td>
							    	<select name="endHour" value="${form.endHour}">
								    <%
								    	for(int j=0; j<23; j++) {
								    %>
								      <option value="<%=j%>"><%=j %></option> 
								    <% 
								    	}
								    %>  
								    </select>
								    &nbsp;
								    <select name="endMins" value="${form.endMins}">
								    <%
								    	for(int j=0; j<60; j+=15) {
								    %>
								      <option value="<%=j%>"><%=j %></option> 
								    <% 
								    	}
								    %>  
								    </select>
							    </td>
							  </tr>
							  <tr>
							    <td>&nbsp;</td>
							    <td colspan="3">&nbsp;</td>
							  </tr>							  
							  <tr>
							    <td>&nbsp;</td>
							    <td colspan="3">&nbsp;</td>
							  </tr>
							  <tr>
							    <td class="register_label">Category :</td>
							    <td colspan="3"><select name="category" value="${form.category}">
							      <option value="Sports">Sports</option>
							      <option value="Business">Business</option>
							      <option value="Entertainment">Entertainment</option>
							      <option value="Food">Food</option>
							    </select>
							    </td>
							  </tr>
							  <tr>
							    <td class="register_label">Host :</td>
							    <td colspan="3"><input type="text" name="host" value="${form.host}" /></td>
							  </tr>
							  <tr>
							  <tr>
							    <td>&nbsp;</td>
							    <td colspan="3">&nbsp;</td>
							  </tr>
							  <tr>
							    <td colspan="4"><hr/></td>
							  </tr>
							  <tr>
							    <td colspan="4">&nbsp;</td>
							  </tr>
							  <tr>
							    <td colspan="4" class="discover">2. Create Tickets</td>
							  </tr>
							  <tr>
							    <td colspan="4">&nbsp;</td>
							  </tr>
							  <tr>
							    <td colspan="4"><table width="400" border="0" cellspacing="0" cellpadding="0">
							      <tr>
							        <td class="register_label">Ticket Name</td>
							        <td class="register_label">Quantity</td>
							        <td class="register_label">Per Ticket Price</td>
							      </tr>
							      <tr>
							        <td><input type="text" name="ticketName" value="${form.ticketName}" /></td>
							        <td><input type="text" width="60px" name="ticketQty" value="${form.ticketQty}" /></td>
							        <td><input type="text" width="60px" name="ticketPrice" value="${form.ticketPrice}" /></td>
							      </tr>
							      <tr>
							        <td>&nbsp;</td>
							        <td>&nbsp;</td>
							        <td>&nbsp;</td>
							      </tr>
							</table>
						</td>
  					</tr>
					<tr>
						<td colspan="4">
						<input name="action" type="submit" class="button" value="Create Event"/>
						</td>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
				</table>
            </td>
          </tr>
          
        </table></td>
        <td>&nbsp;</td>
      </tr>
    </table></form></td>
  </tr>
</table>
</body>
</html>
