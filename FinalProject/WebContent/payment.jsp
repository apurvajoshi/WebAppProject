

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

</head>
<body>
<c:forEach var="error" items="${errors}">
	<h4 style="color:red"> ${error} </h4>
</c:forEach>
<p> Works ?? </p>

</body>
</html>