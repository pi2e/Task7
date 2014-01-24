<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include
	page="${accountType == 'E' ? 'admin-top.jsp' : 'customer-top.jsp'}" />
<div class="container">
	<jsp:include page="success.jsp" />
	<div class="page-header">
		<h3>
			<a href="viewcustomer.do?custId=${customer.customerId}">${customer.lastName},
				${customer.firstName} (${customer.username})</a>
		</h3>
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
						<th>Shares</th>
						<th>Date</th>
						<th>Dollar Amount</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="trans" items="${pendingTransactions}">
						<tr>
							<c:choose>
								<c:when test="${trans.transactionType == 'buy'}">
									<td>${trans.transactionType}</td>
									<td>${trans.fundTicker}</td>
									<td>${trans.fundName}</td>
									<td></td>
									<td></td>
									<td>$${trans.amount}</td>
								</c:when>
								<c:when test="${trans.transactionType == 'sell'}">
									<td>${trans.transactionType}</td>
									<td>${trans.fundTicker}</td>
									<td>${trans.fundName}</td>
									<td>${trans.shares}</td>
									<td></td>
									<td></td>
								</c:when>
								<c:otherwise>
									<td>${trans.transactionType}</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td>$${trans.amount}</td>
								</c:otherwise>
							</c:choose>
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
						<th>Fund Price</th>
						<th>Shares</th>
						<th>Execution Date</th>
						<th>Dollar Amount</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach var="trans" items="${executedTransactions}">
						<tr>
							<c:choose>
								<c:when test="${trans.transactionType == 'buy'}">
									<td>${trans.transactionType}</td>
									<td>${trans.fundTicker}</td>
									<td>${trans.fundName}</td>	
									<td>$${trans.price}</td>
									<td>${trans.shares}</td>
									<td>${trans.executeDate}</td>
									<td>$${trans.amount}</td>
								</c:when>
								<c:when test="${trans.transactionType == 'sell'}">
									<td>${trans.transactionType}</td>
									<td>${trans.fundTicker}</td>
									<td>${trans.fundName}</td>	
									<td>$${trans.price}</td>
									<td>${trans.shares}</td>
									<td>${trans.executeDate}</td>
									<td>$${trans.amount}</td>
								</c:when>
								<c:otherwise>
									<td>${trans.transactionType}</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td>${trans.executeDate}</td>
									<td>$${trans.amount}</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>

<jsp:include page="bottom.jsp" />