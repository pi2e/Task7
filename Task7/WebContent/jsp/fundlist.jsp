<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="${accountType == 'E' ? 'admin-top.jsp' : 'customer-top.jsp'}" />

<div class="container">
	<div class="page-header">
		<h3>Available Funds</h3>
	</div>
	
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Fund List</h3>
		</div>

		<div class="panel-body">
			<table class="table">
				<thead style="text-align: center;">
					<tr>
						<th>Ticker</th>
						<th>Fund Name</th>
						<th>Price</th>
					</tr>
				</thead>
				<tbody>


				</tbody>
			</table>
		</div>
	</div>
</div>

	<jsp:include page="bottom.jsp" />