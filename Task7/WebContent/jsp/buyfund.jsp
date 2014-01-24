<jsp:include page="customer-top.jsp" />

<div class="container">
	<div class="page-header">
		<h3>Buy Fund</h3>
	</div>
	<jsp:include page="errors.jsp" />
	<form class="form-horizontal" action="buyFund.do" method="post">

		<div class="form-group">
			<label for="inputTicker" class="control-label col-xs-2">Available
				Balance</label>
			<div class="col-xs-3">
				<p class="form-control-static">$${balance}</p>
			</div>
		</div>

		<div class="form-group">
			<label for="inputTicker" class="control-label col-xs-2">Ticker</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" placeholder="Ticker"
					name="ticker" value="${form.ticker}">
			</div>
		</div>

		<div class="form-group">
			<label for="inputDollar" class="control-label col-xs-2">Dollar
				Amount</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" placeholder="Dollar Amount"
					name="amount" value="${form.amount}">
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