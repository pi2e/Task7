<jsp:include page="customer-top.jsp" />

<div class="container">
	<div class="page-header">
		<h3>Account Information</h3>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Doe, John</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal" role="form" action="">
				<div class="form-group">
					<label class="col-sm-2 control-label">Username</label>
					<div class="col-sm-10">
						<p class="form-control-static">johndoe111</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Address</label>
					<div class="col-sm-10">
						<p class="form-control-static">417 South Craig Street</p>
						<p class="form-control-static">#02-02</p>
						<p class="form-control-static">Pittsburgh, PA 15213</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Available Balance</label>
					<div class="col-sm-10">
						<p class="form-control-static">$1000</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Ledger Balance</label>
					<div class="col-sm-10">
						<p class="form-control-static">$1000</p>
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