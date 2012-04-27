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
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="14%">&nbsp;</td>
        <td width="72%"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="discover"><br/>Start now, it's free and easy</td>
          </tr>
          <tr>
            <td align="left"><br />
            <form method="post" action="register.do">
            <table width="500px" border="0" cellspacing="0" cellpadding="0">
            	  <tr>
				    <td></td>
				    <td>
				    	<jsp:include page="error-list.jsp" />
				    </td>
				  </tr>
				  <tr>
				    <td width="100" class="register_label">First Name :</td>
				    <td width="400">
				      <input type="text" name="firstName" value="${form.firstName}" />
				    </td>
				  </tr>
				  <tr>
				    <td class="register_label">Last Name :</td>
				    <td><input type="text" name="lastName" value="${form.lastName}" /></td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td class="register_label">Email Id :</td>
				    <td><input type="text" name="emailId" value="${form.emailId}" /></td>
				  </tr>
				  <tr>
				    <td class="register_label">Password :</td>
				    <td><input type="password" name="password" value="${form.password}" /></td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td class="register_label">City :</td>
				    <td><input type="text" name="city" value="${form.city}" /></td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td class="register_label">Category :</td>
				    <td>
				      <p>
				        <label>
				          <input type="checkbox" name="category" value="Business"/>
				          Business</label>
				        <br />
				        <label>
				          <input type="checkbox" name="category" value="Technology" />
				          Technology</label>
				        <br />
				        <label>
				          <input type="checkbox" name="category" value="Art & Music" />
				          Art & Music</label>
				        <br />
				        <label>
				          <input type="checkbox" name="category" value="Food" />
				          Food</label>
				        <br />
				        <label>
				          <input type="checkbox" name="category" value="Travel" />
				          Travel</label>
				        <br />
				        <label>
				          <input type="checkbox" name="category" value="Sports" />
				          Sports</label>
				        <br />
				        <label>
				          <input type="checkbox" name="category" value="Entertainment" />
				          Entertainment</label>
				        <br />
				      </p>
				    </td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td></td>
				    <td>
				      <input name="action" type="submit" class="button" value="Register"/>
				    </td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				</table>
				</form>
            	
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
