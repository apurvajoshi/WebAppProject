<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="edu.cmu.cs.webapp.whatsuptonight.databean.MyTickets"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<body onload="MM_preloadImages('images/home1.png','images/event1.png','images/viewevents1.png','images/tickets1.png')">
<jsp:include page="header.jsp" />

  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="14%">&nbsp;</td>
        <td width="72%"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          
    
 <%
	ArrayList myTicketsList = (ArrayList)request.getAttribute("myTicketsList");
	if (myTicketsList != null) 
	{
%>        

          <tr>
            <td class="discover">
<%

		String msg = (String)request.getAttribute("msg");
		if(msg != null)
		{
%>
				<h3 style="color:red"><%=msg%> </h3>
<%
		}
%>
            
            <br/>
            My Tickets</td>
          </tr>
          <tr>
            <td class="discover">&nbsp;</td>
          </tr>

		<tr>
            <td class="discover">
            <table width="600px" border="0" cellspacing="5px" cellpadding="0">
              <tr>
                <td width="150px" class="home_eventheading">Event Name</td>
                <td width="200px" class="home_eventheading">Date</td>
                <td width="120px" class="home_eventheading" align="right">Tickets Quantity</td>
                <td width="100px" class="home_eventheading" align="right">Total Amount</td>
              </tr>
            </table>
            </td>
          </tr>
            <tr>
            <td><br/>
              <!-- table width="550px" border="0" cellspacing="2px" cellpadding="0">
                <tr>
                  <td-->
                    <table width="600px" border="0" cellspacing="5px" cellpadding="0">  
<%
		Format f = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
		for (int i = 0; i < myTicketsList.size(); i++) 
		{
			MyTickets myTicket = (MyTickets)myTicketsList.get(i);
%>                              
                      <tr>
                        <td width="150px" height="25px" class="home_eventheading"><a href="printTicket.do?id=<%=myTicket.getEventId()%>">
                        <%=myTicket.getTitle() %></a></td>
                        <td width="200px" class="register_label"><%=f.format(myTicket.getDate())%></td>
                        <td width="120px" class="register_label" align="right">
                        	<%=myTicket.getTicketQty() %>
                        </td>
                        <td width="100px" class="register_label" align="right">
                        	$<%=myTicket.getAmount() %>
                        </td>
                        </tr>
                      <tr>
                        <td colspan="4" class="home_eventwhen" height="30px"><hr/></td>
                      </tr>     
 <%
		}
 
 %>                     
                      
<%
	}
	else
	{
%>                  
     	<c:forEach var="error" items="${errors}">
			<h4 style="color:red"> ${error} </h4>
		</c:forEach>
<%
	}
%>                 
                                 
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
