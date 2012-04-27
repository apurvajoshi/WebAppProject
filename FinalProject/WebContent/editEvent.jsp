<%@page import="edu.cmu.cs.webapp.whatsuptonight.databean.Event"%>
<%@page import="edu.cmu.cs.webapp.whatsuptonight.databean.Ticket"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>

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
	
</head>

<body onload="MM_preloadImages('images/event1.png','images/viewevents1.png','images/tickets1.png')">
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
    	<% 
    		Event event = (Event)request.getAttribute("event");
    		Ticket ticket = (Ticket)request.getAttribute("ticket");
    	%>
    <form method="post" action="updateEvent.do?eventId=<%= event.getEventId() %>" enctype="multipart/form-data">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      	<tr>
        	<td width="14%">&nbsp;</td>
        	<td width="72%">
        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
          			<tr>
			            <td class="discover"><br/>Edit Event<br/><br/>
			                1. Edit your event details!!!
			            </td>
          			</tr>
          			<tr>
            			<td align="left"><br />
							<table width="600px" border="0" cellspacing="0" cellpadding="0">
							  <tr>
							    <td width="125" class="register_label">Event Title :</td>
							    <td width="475" colspan="3"><input type="text" name="title" value="<%= event.getTitle() %>" /></td>
							  </tr>
							  <tr>
							    <td class="register_label">Event Details :</td>
							    <td colspan="3"><textarea name="desc" rows="5" ><%= event.getDescription()%></textarea></td>
							  </tr>
							  <tr>
							    <td class="register_label">Upload Event Picture:</td>
							    <td colspan="3"><input type="file" name="file" value="" /></td>
							  </tr>
							  <tr>
							    <td>&nbsp;</td>
							    <td colspan="3">&nbsp;</td>
							  </tr>
							  <tr>
							    <td class="register_label">Location :</td>
							    <td colspan="3"><input type="text" name="location" value="<%= event.getLocation()%>" /></td>
							  </tr>
							  <tr>
							    <td class="register_label">City :</td>
							    <td colspan="3"><input type="text" name="city" value="<%= event.getCity() %>" /></td>
							  </tr>
							  <tr>
							    <td class="register_label">&nbsp;</td>
							    <td colspan="3">&nbsp;</td>
							  </tr>
							  <tr>
							    <td>&nbsp;</td>
							    <td colspan="3">&nbsp;</td>
							  </tr>
							  <tr>
							    <td class="register_label">Event Start Date :</td>
							    <% 
							    	Format f1 = new SimpleDateFormat("mm/dd/yyyy");
							    %>
							    <td><input type="text" name="startDate" id="startdatepicker" value="<%= f1.format(event.getStartDate()) %>" /></td>
							    <td><span class="register_label">Event End Date :</span></td>
							    <td>							    	
								    <select name="startHour">
								    <%						
								    	int stHr = event.getStartDate().getHours();
								    	for(int j=0; j<23; j++) {
								    		if(stHr == j) {
								    %>
								      			<option selected="selected" value="<%=j%>"><%=j %></option> 
								    <% 
								    		} else {
								    %>
								    			<option value="<%=j%>"><%=j %></option>
								    <% 
								    		}
								    	}
								    %> 
								    	
								   
								    </select>
								    &nbsp;
								    <select name="startMins" value="${form.startMins}">
								    <%
								    	int stMin = event.getStartDate().getMinutes();
								    	for(int j=0; j<60; j+=15) {
								    		if(stMin == j) {
								    %>
								      		<option selected="selected" value="<%=j%>"><%=j %></option> 
								    <% 
								    		} else {
								    %>
								    		<option value="<%=j%>"><%=j %></option>
								    <% 
								    		}
								    	}
								    %> 
								    </select>
								
							    
							    </td>
							  </tr>
							  <tr>
							    <td class="register_label">Event Start Time :</td>
							    <td><input type="text" name="endDate" id="enddatepicker" value="<%= f1.format(event.getEndDate()) %>" /></td>
							    <td><span class="register_label">Event End Time :</span></td>
							    <td>
							    	<select name="endHour" value="${form.endHour}">
								    <%
								    	int edHr = event.getEndDate().getHours();
								    	for(int j=0; j<23; j++) {
								    		if(edHr == j) {
								    %>
								      		<option selected="selected" value="<%=j%>"><%=j %></option> 
								    <% 
								    		} else {
								    %>
								    		<option value="<%=j%>"><%=j %></option>
								    <% 
								    		}
								    	}
								    %>  
								    </select>
								    &nbsp;
								    <select name="endMins" value="${form.endMins}">
								    <%
								    	int edMin = event.getEndDate().getMinutes();
								    	for(int j=0; j<60; j+=15) {
								    		if(edMin == j) {
								    %>
								      		<option selected="selected" value="<%=j%>"><%=j %></option> 
								     <% 
								    		} else {
								    %>
								    		<option value="<%=j%>"><%=j %></option>
								    <% 
								    		}
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
							    <td colspan="3"><select name="select" value="${event.category}">
							      <option value="1">Sports</option>
							      <option value="2">Business</option>
							      <option value="3">Entertainment</option>
							      <option value="4">Food</option>
							    </select>
							    </td>
							  </tr>
							  <tr>
							    <td class="register_label">Host :</td>
							    <td colspan="3"><input type="text" name="host" value="<%= event.getOrganization() %>" /></td>
							  </tr>							  
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
							        <td><input type="text" name="ticketName" value="<%= ticket.getTicketName() %>" /></td>
							        <td><input type="text" width="60px" name="ticketQty" value="<%= ticket.getTicketQty() %>" /></td>
							        <td><input type="text" width="60px" name="ticketPrice" value="<%= ticket.getTicketPrice()%>" /></td>
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
						<input name="action" type="submit" class="button" value="Save Event"/>
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
