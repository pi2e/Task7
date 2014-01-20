<jsp:include page="customer-top.jsp" />


 <div class="container">
    	<div class="page-header">
    		<h3>Sell Fund</h3>
    	</div>

    	<form class="form-horizontal">


        <div class="form-group">
            <label for="inputTicker" class="control-label col-xs-2">Ticker</label>
            <div class="col-xs-3">
                <input type="text" class="form-control" id="inputTicker" placeholder="Ticker">
            </div>
        </div>

  <div class="form-group">
            <label for="inputNumber" class="control-label col-xs-2">Number of Shares</label>
            <div class="col-xs-3">
                <input type="number" class="form-control" id="inputNumber" placeholder="Number of Shares">
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