<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<p class="text-success">
	<c:forEach var="success" items="${success}">
	<br/>
	${success}
	</c:forEach>
	</p>