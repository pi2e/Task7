<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="admin-top.jsp" />


<div class="container">
	<form method="post" action="depositCheck.do">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Customers</h3>
			</div>
			<div class="panel-body">
				<table class="table">
					<thead style="text-align: center;">

						<tr>
							<th>Last Name</th>
							<th>First Name</th>
							<th>Username</th>
							<th>Holdings</th>
							<th>Available Balance</th>
							<th>Deposit Check</th>
						</tr>

					</thead>

					<tbody>
						<c:forEach var="customer" items="${customers}">
						<tr>
							<td>${customer.lastName}</td>
							<td>${customer.firstName}</td>
							<td><a href="#">${customer.getUsername()}</a></td>
							<td>xxx</td>
							<td>${customer.balance}</td>
							<td></td>
						</tr>
					</c:forEach>

					</tbody>
				</table>

				<div>
					<input type="hidden" value="submitIn" name="requestedSubmit" /> <input
						type="submit" class="submit_btn" name="submit" id="submit"
						value="Submit" />
				</div>
			</div>
		</div>
	</form>
	
</div>

<jsp:include page="bottom.jsp" />