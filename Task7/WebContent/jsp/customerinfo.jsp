<jsp:include page="customer-top.jsp" />

<div class="container">
	<div class="page-header">
		<h3>Account Information</h3>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">${user.lastName}, ${user.firstName}</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal" role="form" action="">
				<div class="form-group">
					<label class="col-sm-2 control-label">Username</label>
					<div class="col-sm-10">
						<p class="form-control-static">${user.username}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Address</label>
					<div class="col-sm-10">
						<p class="form-control-static">${user.addressLine1}</p>
						<p class="form-control-static">${user.addressLine2}</p>
						<p class="form-control-static">${user.city}, ${user.state} ${user.zipCode}</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Available Balance</label>
					<div class="col-sm-10">
						<p class="form-control-static">$${user.balance}</p>
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