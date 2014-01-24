<jsp:include page="customer-top.jsp" />

<div class="container">
	<jsp:include page="success.jsp" />
	<div class="page-header">
		<h3>Request Check</h3>
	</div>
	<jsp:include page="errors.jsp" />
	<form class="form-horizontal" method="post" role="form"
		action="requestCheck.do">

		<div class="form-group">
			<label for="ledgerBalance" class="control-label col-xs-2">Cash</label>
			<div class="col-xs-3">
				<p class="form-control-static">$${ ledgerBalance}</p>
			</div>
		</div>

		<div class="form-group">
			<label for="avaliableBalance" class="control-label col-xs-2">Available
				Balance</label>
			<div class="col-xs-3">
				<p class="form-control-static">$${availablebalance}</p>
			</div>
		</div>

		<div class="form-group">
			<label for="inputAmount" class="control-label col-xs-2">Amount</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="withdrawAmount"
					placeholder="Amount" value="${form.withdrawAmount}"
					name="withdrawAmount">
					<span class="help-block">Up to 2 decimal palaces</span>
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

