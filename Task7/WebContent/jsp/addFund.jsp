<jsp:include page="admin-top.jsp" />

<div class="container">

	<jsp:include page="errors.jsp" />

	<div class="page-header">
		<h3>Create Fund</h3>
	</div>

	<form class="form-horizontal" action="addFund.do" method="post">
		<div class="form-group">
			<label for="inputFundName" class="control-label col-xs-2">Fund
				Name</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputFundName"
					placeholder="Fund Name" name="fundName" value="${form.fundName}">
			</div>
		</div>



		<div class="form-group">
			<label for="inputTickerName" class="control-label col-xs-2">Ticker
				Name</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputTickerName"
					placeholder="Ticker Name " name="ticker" value="${form.ticker}">
				<span class="help-block">Up to 5 characters</span>
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