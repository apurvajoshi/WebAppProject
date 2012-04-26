<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="14%" bgcolor="#3B5998">&nbsp;</td>
        <td width="72%" height="35" bgcolor="#3B5998"><table width="360" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image1','','images/event1.png',1)"><img src="images/event.png" name="Image1" width="120" height="35" border="0" id="Image1" /></a></td>
            <td><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image2','','images/viewevents1.png',1)"><img src="images/viewevents.png" name="Image2" width="120" height="35" border="0" id="Image2" /></a></td>
            <td><a href="#" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image3','','images/tickets1.png',1)"><img src="images/tickets.png" name="Image3" width="120" height="35" border="0" id="Image3" /></a></td>
          </tr>
        </table></td>
        <td width="14%" bgcolor="#3B5998">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="14%">&nbsp;</td>
        <td width="72%"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="discover"><br/>Discover Events at Pittsburgh</td>
          </tr>
          <tr>
            <td align="left"><br />
            	<form>
                	<input name="searchString" type="text" />&nbsp;
                    <input name="searchbutton" type="button" value="Search Events" />
                </form>
            </td>
          </tr>
          <tr>
            <td><br/>
            	<table width="450px" border="0" cellspacing="0" cellpadding="0">
            	
            	<c:forEach var="event" items="${eventsList}">
                  <tr>
                    <td>
                    	<table width="450px" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="350" height="25px" class="home_eventheading"><a href="userEventRegistration.do?eventId=${event.eventId}">${event.title}</a></td>
                            <td width="100" class="home_eventdate">${event.startDate }</td>
                          </tr>
                          <tr>
                            <td class="home_eventwhen" height="18px"><b>When:</b>	${event.startDate}</td>
                            <td></td>
                          </tr>  
                          <tr>
                            <td class="home_eventwhen"><b>Where:</b>	${event.location}</td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td colspan="2" class="home_eventwhen" height="30px"><hr/></td>
                          </tr>                
                		</table>
            		</td>
           		</tr>
           		</c:forEach>
                
	        </table>
          
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
