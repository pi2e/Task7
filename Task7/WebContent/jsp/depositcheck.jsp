<jsp:include page="admin-top.jsp" />

<div class="container">

	<jsp:include page="errors.jsp" />

	<div class="page-header">
		<h3>Deposit Check</h3>
	</div>

	<form class="form-horizontal" method="post" action="depositCheck.do?custId=${customer.customerId}">
		<div class="form-group">
			<label for="inputCustermerName" class="control-label col-xs-2">Customer
				Name</label>
			<div class="col-xs-3">
				<p class="form-control-static">${customer.lastName}, ${customer.firstName}</p>
			</div>
		</div>

		<div class="form-group">
			<label for="inputCustermerName" class="control-label col-xs-2">Username</label>
			<div class="col-xs-3">
				<p class="form-control-static">${customer.username}</p>
				<input type="hidden" class="form-control"
					name="userId" id="userId" value="${customer.customerId}">
			</div>
		</div>

		<div class="form-group">
			<label for="inputAmount" class="control-label col-xs-2">Amount</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="amount"
					placeholder="Amount" value="${form.amount}" name="amount">
					<span class="help-block">Up to 2 decimal places</span>
			</div>
		</div>


		<div class="form-group">
			<div class="col-xs-offset-2 col-xs-10">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</div>
	</form>

</div>

<jsp:include page="bottom.jsp" />