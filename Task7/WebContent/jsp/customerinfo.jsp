<jsp:include
	page="${accountType == 'E' ? 'admin-top.jsp' : 'customer-top.jsp'}" />

<div class="container">
	<div class="page-header">
		<h3>Account Information</h3>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">${customerForm.lastName}, ${customerForm.firstName}</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal" role="form" action="">
				<div class="form-group">
					<label class="col-sm-2 control-label">Last Trading Day</label>
					<div class="col-sm-10">
						<p class="form-control-static">last trading day</p>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">Username</label>
					<div class="col-sm-10">
						<p class="form-control-static">${customerForm.username}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Address</label>
					<div class="col-sm-10">
						<p class="form-control-static">${customerForm.address1}</p>
						<p class="form-control-static">${customerForm.address2}</p>
						<p class="form-control-static">${customerForm.city}, ${customerForm.state} ${customerForm.zipcode}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Available Balance</label>
					<div class="col-sm-10">
						<p class="form-control-static">$${customerForm.balance}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Ledger Balance</label>
					<div class="col-sm-10">
						<p class="form-control-static">$xxxx</p>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Funds</h3>
		</div>
		<div class="panel-body">fund table here</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Pending Transactions</h3>
		</div>
		<div class="panel-body">fund table here</div>
	</div>

</div>

<jsp:include page="bottom.jsp" />