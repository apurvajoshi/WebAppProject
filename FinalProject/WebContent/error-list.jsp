<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="error" items="${errors}">
	<div style="font-size:12px; font-family:calibri; color:red; "> ${error} </div>
</c:forEach>

