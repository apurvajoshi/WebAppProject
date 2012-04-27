
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>


<body onload="MM_preloadImages('images/event1.png','images/viewevents1.png','images/tickets1.png')">
<jsp:include page="header.jsp" />


<tr>

    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="14%">&nbsp;</td>
        <td width="72%"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="discover">
            <br/>								
            	<jsp:include page="error-list.jsp" />
            <br/>
            ${event.title}</td>
          </tr>
          <tr>
            <td class="discover"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td><table width="400px" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td class="home_eventdate">Ticket Information</td>
                  </tr>
                  <tr>
                    <td>
                    <form method="post" id="form1" action="getCardDetails.do">        
                    	<table width="400px" border="0" cellspacing="0" cellpadding="0">
						  <tr>
					    	<td class="home_eventwhen">Ticket Class</td>
					    	<td class="home_eventwhen">Price</td>
					    	<td class="home_eventwhen">Quantity</td>
					  	  </tr>
					  	  <tr>
					    	<td class="eventdeatils_normal">${ticket.ticketName}</td>
					    	<td class="eventdeatils_normal">$ ${ticket.ticketPrice}</td>
					    	<td><label for="quantity"></label>
					      	<select name="quantity" id="quantity">
					        <option value="1">1</option>
					        <option value="2">2</option>
					        <option value="3">3</option>
					        <option value="4">4</option>
					        <option value="5">5</option>
					        <option value="6">6</option>
					      	</select>
					      	</td>
 						 </tr>
					  	<tr>
					    	<td align="right" colspan="3"><input name="orderbutton" type="submit" value="Book Tickets" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						    </tr>
						</table>
					</form>
                    
                    </td>
                    
                  </tr>
                </table></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>
                
                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
					    <td class="home_eventdate">Event Details</td>
					  </tr>
					  <tr>
					    <td class="eventdeatils_normal">
					    ${event.description} <br/><br/>
					When: ${event.startDate}<br/>
					Where: ${event.location} ${event.city}<br/>					    
					    </td>
					  </tr>
					  <tr>
					    <td class="home_eventdate"></td>
					  </tr>
					  <tr>
					    <td class="home_eventdate"><br/>
					    <input width="500" height="400" type="image" name="imageField" id="imageField" src="image.do?eventId=${event.eventId}" />
					    </td>
					  </tr>
					</table>

                
                </td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td>
              
              
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
