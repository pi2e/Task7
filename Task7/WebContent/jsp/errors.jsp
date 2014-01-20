<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<p class="text-danger">
	<c:forEach var="error" items="${errors}">
	<br/>
	${error}
	</c:forEach>
	</p>