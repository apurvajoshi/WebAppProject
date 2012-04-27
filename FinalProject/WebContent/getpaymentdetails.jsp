<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Buy tickets </title>
<style type="text/css">
@import url("stylesheet.css");
</style>
		<script type="text/javascript" src="https://js.stripe.com/v1/"></script>
        <!-- jQuery is used only for this example; it isn't required to use Stripe -->
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
        <script type="text/javascript">
            // this identifies your website in the createToken call below
            Stripe.setPublishableKey('pk_XGfMNBAnTGOnoGSXzFGcbaMACeHGq');

            function stripeResponseHandler(status, response) {
                if (response.error) {
                    // re-enable the submit button
                    $('.submit-button').removeAttr("disabled");
                    // show the errors on the form
                    $(".payment-errors").html(response.error.message);
                } else {
                    var form$ = $("#payment-form");
                    // token contains id, last4, and card type
                    var token = response['id'];
                    // insert the token into the form so it gets submitted to the server
                    form$.append("<input type='hidden' name='stripeToken' value='" + token + "' />");
                    // and submit
                    form$.get(0).submit();
                }
            }

            $(document).ready(function() {
                $("#payment-form").submit(function(event) {
                    // disable the submit button to prevent repeated clicks
                    $('.submit-button').attr("disabled", "disabled");
                    // createToken returns immediately - the supplied callback submits the form if there are no errors
                    Stripe.createToken({
                        number: $('.card-number').val(),
                        cvc: $('.card-cvc').val(),
                        exp_month: $('.card-expiry-month').val(),
                        exp_year: $('.card-expiry-year').val()
                    }, stripeResponseHandler);
                    return false; // submit from callback
                });
            });

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
</table>

<c:forEach var="error" items="${errors}">
		<h4 style="color:red"> ${error} </h4>
</c:forEach>

<h3>Enter your card details</h3>
        <!-- to display errors returned by createToken -->
        <span class="payment-errors"></span>
        <form method="POST" id="payment-form" action="payment.do">        
            <div class="form-row">
                <label>Card Number</label>
                <input type="text" size="20" class="card-number" />
            </div>
            <div class="form-row">
                <label>CVC</label>
                <input type="text" size="4" class="card-cvc" />
            </div>
            <div class="form-row">
                <label>Expiration (MM/YYYY)</label>
                <input type="text" size="2" class="card-expiry-month"/>
                <span> / </span>
                <input type="text" size="4" class="card-expiry-year"/>
            </div>
            <button type="submit" class="submit-button">Submit Payment</button>
        </form>
</body>
</html>