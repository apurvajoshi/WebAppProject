<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>WatzUpTonight</title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<!-- TemplateEndEditable -->
<style type="text/css">
@import url("stylesheet.css");
</style>
<script type="text/javascript">
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
</script>
	
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
<jsp:include page="header.jsp" />
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
							      <option value="Business">Business</option>
							      <option value="Technology">Technology</option>
							      <option value="Art & Music">Art & Music</option>
							      <option value="Food">Food</option>
							      <option value="Travel">Travel</option>
							      <option value="Sports">Sports</option>
							      <option value="Entertainment">Entertainment</option>
							      
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
