<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include
	page="${accountType == 'E' ? 'admin-top.jsp' : 'customer-top.jsp'}" />

<div class="container">
	<jsp:include page="errors.jsp" />
	<div class="page-header">
		<h3>Account Information</h3>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">${customer.lastName},
				${customer.firstName}</h3>
		</div>
		<div class="panel-body">

			<form class="form-horizontal" role="form" action="">

				<div class="row">
					<div class="col-md-6">

						<div class="form-group">
							<label class="col-md-4 control-label">Last Trading Day</label>
							<div class="col-md-6">
								<p class="form-control-static">${lastTradingDay}</p>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-4 control-label">Available Balance</label>
							<div class="col-md-6">
								<p class="form-control-static">$${balance}</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">Cash</label>
							<div class="col-md-6">
								<p class="form-control-static">$${ledgerBalance}</p>
							</div>
						</div>

					</div>
					<div class="col-md-6">

						<div class="form-group">
							<label class="col-md-4 control-label">Username</label>
							<div class="col-md-6">
								<p class="form-control-static">${customer.username}</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">Address</label>
							<div class="col-md-6">
								<p class="form-control-static">${customer.addressLine1}</p>
								<p class="form-control-static">${customer.addressLine2}</p>
								<p class="form-control-static">${customer.city},
									${customer.state} ${customer.zipCode}</p>
							</div>
						</div>

					</div>
				</div>

			</form>
		</div>
	</div>
	<c:if test="${accountType == 'E' }">
		<ul class="nav nav-pills">
			<li><a
				href="viewCustomerTransaction.do?custId=${customer.customerId}">Transaction
					History</a></li>
			<li><a href="depositCheck.do?custId=${customer.customerId }">Deposit
					Check</a></li>
			<li><a href="changePwd.do?custId=${customer.customerId }">Change
					Customer Password</a></li>
		</ul>
		<br />
	</c:if>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Funds</h3>
		</div>
		<div class="panel-body">
			<table class="table">
				<thead style="text-align: center;">

					<tr>
						<th>Ticker</th>
						<th>Fund Name</th>
						<th class="text-right">Current Price</th>
						<th class="text-right">Number of Shares</th>
						<th class="text-right">Available Shares</th>
						<th class="text-right">Position Value</th>
						<th></th>
					</tr>

				</thead>

				<tbody>
					<c:forEach var="fundVO" items="${fundVOList}">
						<tr>
							<td><a href="viewFund.do?fundId=${fundVO.fundId}">${fundVO.ticker}</a></td>
							<td>${fundVO.fundName}</td>
							<td class="text-right">$${fundVO.currentPrice}</td>
							<td class="text-right">${fundVO.shares}</td>
							<td class="text-right">${fundVO.availableShares}</td>
							<td class="text-right">$${fundVO.positionValue}</td>
							<td class="text-right"><c:choose>
									<c:when test="${accountType == 'E'}">
									</c:when>
									<c:otherwise>
										<form method="post" action="sellFund.do">
											<input type="hidden" name="sellFund" value="${fundVO.ticker}" /> 
											<input
												type="submit" class="btn btn-primary" name="submit"
												id="submit" value="Sell"/>
										</form>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>

		</div>
	</div>

</div>

<jsp:include page="bottom.jsp" />
