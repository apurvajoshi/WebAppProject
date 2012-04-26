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
    <form method="post" action="updateEvent.do?eventId=${event.eventId}" enctype="multipart/form-data">
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
							    <td width="475" colspan="3"><input type="text" name="title" value="${event.title}" /></td>
							  </tr>
							  <tr>
							    <td class="register_label">Event Details :</td>
							    <td colspan="3"><textarea name="desc" rows="5" >${event.description}</textarea></td>
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
							    <td colspan="3"><input type="text" name="location" value="${event.location}" /></td>
							  </tr>
							  <tr>
							    <td class="register_label">City :</td>
							    <td colspan="3"><input type="text" name="city" value="${event.city}" /></td>
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
							    <td><input type="text" name="startDate" id="startdatepicker" value="${event.startDate}" /></td>
							    <td><span class="register_label">Event End Date :</span></td>
							    <td><input type="text" name="startTime" value="${event.startTime}" /></td>
							  </tr>
							  <tr>
							    <td class="register_label">Event Start Time :</td>
							    <td><input type="text" name="endDate" id="enddatepicker" value="${event.endDate}" /></td>
							    <td><span class="register_label">Event End Time :</span></td>
							    <td><input type="text" name="endTime" value="${event.endTime}" /></td>
							  </tr>
							  <tr>
							    <td>&nbsp;</td>
							    <td colspan="3">&nbsp;</td>
							  </tr>
							  <tr>
							    <td class="register_label">Privacy :</td>
							    <td colspan="3">
							      <%
							      		String str = request.getAttribute("event").toString();
							      		if(str.equals("Public")) {
							      	%>
							      <p>							      	
							        <label class="register_label">
							          <input type="radio" name="privacy" checked="checked" value="Public" />
							          Public</label>
							        <br />
							        <label class="register_label">
							          <input type="radio" name="privacy" value="Private"/>
							          Private</label>
							        <br />
							        </p>
							        <%
							      		} else { 
							      	%>
						      			 <p>							      	
								        <label class="register_label">
								          <input type="radio" name="privacy" value="Public" />
								          Public</label>
								        <br />
								        <label class="register_label">
								          <input type="radio" name="privacy" checked="checked" value="Private"/>
								          Private</label>
								        <br />
								        </p>							      		
							        <% 
							      		}
							        %>
							      </td>
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
							    <td colspan="3"><input type="text" name="host" value="${event.organization}" /></td>
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
							        <td><input type="text" name="ticketName" value="${ticket.ticketName}" /></td>
							        <td><input type="text" width="60px" name="ticketQty" value="${ticket.ticketQty}" /></td>
							        <td><input type="text" width="60px" name="ticketPrice" value="${ticket.ticketPrice}" /></td>
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
