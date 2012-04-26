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
    <td><table width="100%" height="300px" align="center" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="14%">&nbsp;</td>
        <td width="72%"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="50%" class="discover">
            
	            <table width="80%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
					    <td class="discover">
					    If it's happening out there you'll find it here.
					Browse 1000's of events. Or create your own events and sell tickets right here.
					    </td>
					  </tr>
					  <tr>
					    <td class="home_eventdate" align="center"><br/><br/>79,203 Tickets sold</td>
					  </tr>
				</table>          
            
            </td>
            <td width="50%">
            	  <form method="post" action="login.do">
				  <table align="center" height="100px" width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
				      	<td></td>
				      	<td>
				      		<jsp:include page="error-list.jsp" />
				      	</td>
				      </tr>
					  <tr>
					    <td width="20%" class="register_label">Email Id :</td>
					    <td width="80%">
					      <input type="text" name="emailId" value="${form.emailId}" />
					    </td>
					  </tr>
					  <tr>
					    <td class="register_label">Password :</td>
					    <td><input type="password" name="password" value="${form.password}" /></td>
					  </tr>
					  <tr>
					    <td></td>
					    <td>
					    	<input name="action" type="submit" class="button" value="Login"/></td>
					  </tr>
					  <tr>
					    <td></td>
					    <td>&nbsp;</td>
					  </tr>
					  <tr>
					    <td colspan="2" class="register_label">New User? <a href="register.jsp">Register here</a></td>
					    </tr>
				</table>
				</form>
            
            </td>
          </tr>
          <tr>
            <td align="left"></td>
            <td align="left">
            

            	
            </td>
          </tr>
          
        </table></td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
