<%@page import="edu.cmu.cs.webapp.whatsuptonight.databean.User"%>
<%@page import="edu.cmu.cs.webapp.whatsuptonight.databean.UserCategory"%>
<%@page import="java.util.HashMap"%>
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
</head>

<body onload="MM_preloadImages('images/home1.png', 'images/event1.png','images/viewevents1.png','images/tickets1.png')">
<jsp:include page="header.jsp" />

<tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="14%">&nbsp;</td>
        <td width="72%"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="discover"><br/>Edit Profile</td>
          </tr>
          <tr>
            <td align="left"><br />
            <form method="post" action="updateProfile.do">
            <% 
            	User user = (User)session.getAttribute("user");
            	HashMap<String, Integer> hashMap = (HashMap<String, Integer>)session.getAttribute("userCategoryList");
            %>
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
				      <input type="text" name="firstName" value="<%= user.getFirstName()%>" />
				    </td>
				  </tr>
				  <tr>
				    <td class="register_label">Last Name :</td>
				    <td><input type="text" name="lastName" value="<%= user.getLastName() %>" /></td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td class="register_label">Email Id :</td>
				    <td><input disabled="disabled" type="text" name="emailId" value="<%=user.getEmailId() %>" /></td>
				  </tr>
				  <tr>
				    <td class="register_label">Password :</td>
				    <td><input type="password" name="password" value="<%= user.getPassword() %>" /></td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td class="register_label">City :</td>
				    <td><input type="text" name="city" value="<%= user.getCity() %>" /></td>
				  </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td class="register_label">Category :</td>
				    <td>
				      <p>
				      	<% 
				      		if(hashMap.get("Business") == null) {
				      	%>
				        <label>				        
				          <input type="checkbox" name="category" value="Business" />
				          Business</label>
				        <% 
				      		} else {
				        %>
				        	<label>				        
				          <input type="checkbox" checked="checked" name="category" value="Business" />
				          Business</label>
				        <% 
				      		}
				        %>
				        <br />
				        <% 
				      		if(hashMap.get("Technology") == null) {
				      	%>
				        <label>
				          <input type="checkbox" name="category" value="Technology" />
				          Technology</label>
				        <% 
				      		} else {
				        %>
				        	<label>				        
				          <input type="checkbox" checked="checked" name="category" value="Technology" />
				          Technology</label>
				        <% 
				      		}
				        %>
				        <br />
				        <% 
				      		if(hashMap.get("Art & Music") == null) {
				      	%>
				        <label>
				          <input type="checkbox" name="category" value="Art & Music" />
				          Art & Music</label>
				        <% 
				      		} else {
				        %>
				        	<label>				        
				          <input type="checkbox" checked="checked" name="category" value="Art & Music" />
				          Art & Music</label>
				        <% 
				      		}
				        %>				        
				        <br />
				        <% 
				      		if(hashMap.get("Food") == null) {
				      	%>
				        <label>
				          <input type="checkbox" name="category" value="Food" />
				          Food</label>
				        <% 
				      		} else {
				        %>
				        	<label>				        
				          <input type="checkbox" checked="checked" name="category" value="Food" />
				          Food</label>
				        <% 
				      		}
				        %>		
				        <br />
				        <% 
				      		if(hashMap.get("Travel") == null) {
				      	%>
				        <label>
				          <input type="checkbox" name="category" value="Travel" />
				          Travel</label>
				        <% 
				      		} else {
				        %>
				        	<label>				        
				          <input type="checkbox" checked="checked" name="category" value="Travel" />
				          Travel</label>
				        <% 
				      		}
				        %>		
				        <br />
				        <% 
				      		if(hashMap.get("Sports") == null) {
				      	%>
				        <label>
				          <input type="checkbox" name="category" value="Sports" />
				          Sports</label>
				          <% 
				      		} else {
				        %>
				        	<label>				        
				          <input type="checkbox" checked="checked" name="category" value="Sports" />
				          Sports</label>
				        <% 
				      		}
				        %>		
				        <br />
				        <% 
				      		if(hashMap.get("Business") == null) {
				      	%>
				        <label>
				          <input type="checkbox" name="category" value="Entertainment" />
				          Entertainment</label>
				          <% 
				      		} else {
				        %>
				        	<label>				        
				          <input type="checkbox" checked="checked" name="category" value="Entertainment" />
				          Entertainment</label>
				        <% 
				      		}
				        %>		
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
				      <input name="action" type="submit" class="button" value="Edit Profile"/>
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
