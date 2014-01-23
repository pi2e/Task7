<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include
	page="${accountType == 'E' ? 'admin-top.jsp' : 'customer-top.jsp'}" />
<div class="container">
	<div class="page-header">
		<h3><a href="viewcustomer.do?custId=${customer.customerId}">${customer.lastName}, ${customer.firstName} (${customer.username})</a></h3>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Pending Transactions</h3>
		</div>
		<div class="panel-body">
			<table class="table">
				<thead>
					<tr>
						<th>Type</th>
						<th>Ticker</th>
						<th>Fund Name</th>
						<th>Amount</th>
						<th>Fund Price</th>
						<th>Shares</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="trans" items="${pendingTransactions}">
						<tr>
							<td>${trans.transactionType}</td>
							<td>${trans.fundTicker}</td>
							<td>${trans.fundName}</td>
							<td>$${trans.amount}</td>
							<td>$${trans.price}</td>
							<td>${trans.shares}</td>
							<td></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Executed Transactions</h3>
		</div>
		<div class="panel-body">
			<table class="table">
				<thead>
					<tr>
						<th>Type</th>
						<th>Ticker</th>
						<th>Fund Name</th>
						<th>Amount</th>
						<th>Fund Price</th>
						<th>Shares</th>
						<th>Date</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach var="trans" items="${executedTransactions}">
						<tr>
							<td>${trans.transactionType}</td>
							<td>${trans.fundTicker}</td>
							<td>${trans.fundName}</td>
							<td>$${trans.amount}</td>
							<td>$${trans.price}</td>
							<td>${trans.shares}</td>
							<td>${trans.executeDate}</td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>

<jsp:include page="bottom.jsp" />