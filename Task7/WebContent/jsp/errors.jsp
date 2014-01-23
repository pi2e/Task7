<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:forEach var="error" items="${errors}">
	<p class="label label-danger">${error}</p>
	<br/><br/>
	</c:forEach>