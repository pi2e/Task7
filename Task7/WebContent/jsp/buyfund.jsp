<jsp:include page="customer-top.jsp" />

<div class="container">
    	<div class="page-header">
    		<h3>Buy Fund</h3>
    	</div>

    	<form class="form-horizontal">


        <div class="form-group">
            <label for="inputTicker" class="control-label col-xs-2">Ticker</label>
            <div class="col-xs-3">
                <input type="text" class="form-control" id="inputTicker" placeholder="Ticker">
            </div>
        </div>

  <div class="form-group">
            <label for="inputDollar" class="control-label col-xs-2">Dollar Amount</label>
            <div class="col-xs-3">
                <input type="number" class="form-control" id="inputDollarAmount" placeholder="Dollar Amount">
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