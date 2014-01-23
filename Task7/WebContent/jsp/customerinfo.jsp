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
								<p class="form-control-static">last trading day</p>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-4 control-label">Cash Balance</label>
							<div class="col-md-6">
								<p class="form-control-static">$${balance}</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">Ledger Balance</label>
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

	<ul class="nav nav-pills">
		<li><a href="viewCustomerTransaction.do?custId=${customer.customerId}">Transaction History</a></li>
		<li><a href="depositCheck.do?custId=${customer.customerId }">Deposit Check</a></li>
		<li><a href="#">Change Password</a></li>
	</ul>
	<br/>
	
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Funds</h3>
		</div>
		<div class="panel-body">
			<table class="table">
				<thead style="text-align: center;">

					<tr>
						<th>Fund Ticker</th>
						<th>Fund Name</th>
						<th>Current Price</th>
						<th>Number of Shares</th>
						<th>Position Value</th>
					</tr>

				</thead>

				<tbody>
					<c:forEach var="fundVO" items="${fundVOList}">
						<tr>
							<td><a href="viewFund.do?fundId=${fundVO.fundId}">${fundVO.ticker}</a></td>
							<td>${fundVO.fundName}</td>
							<td>$${fundVO.currentPrice}</td>
							<td>${fundVO.shares}</td>
							<td>$${fundVO.positionValue}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>

<jsp:include page="bottom.jsp" />