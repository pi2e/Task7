<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="admin-top.jsp" />


<div class="container">
	<jsp:include page="errors.jsp" />
	<jsp:include page="success.jsp" />
	<form method="post" action="depositMultipleCheck.do">
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
							<th>Cash</th>
							<th>Available Balance</th>
							<th>Deposit Check</th>
						</tr>

					</thead>

					<tbody>
						<c:forEach var="customer" items="${customers}" varStatus="status">
							<tr>
								<td>${customer.lastName}</td>
								<td>${customer.firstName}</td>
								<td><a
									href="viewcustomer.do?custId=${customer.customerId }">${customer.username}</a></td>
								<td>${cash[status.index]}</td>
								<td>${balance[status.index]}</td>
								<td>$ <input type=text name="${customer.customerId}" value="${depositList[status.index]}"></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>

				<div>
					<input type="submit" class="btn btn-primary" name="submit"
						id="submit" value="Submit" />
				</div>
			</div>
		</div>
	</form>

</div>

<jsp:include page="bottom.jsp" />