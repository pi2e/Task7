<jsp:include page="admin-top.jsp" />



	<div class="container">
		
		<form method="post" action="transitionFund.do">
	
	    <div class="panel panel-default">
		
			<div class="panel-heading">
	          <h3>Transition Day</h3>
			</div>
		
			<div class="panel-body">	
	          <h4>Latest Transaction Date: </h4>
	          <h4>Input Next Transaction Date:</h4>              
	          <input type="text" value="${nextDay}" name="dateInput"/>
	          <i>*input format: yyyy/mm/dd</i>
			</div>

			</div> 
		
		<div class="panel panel-default">
			
			<div class="panel-heading">
				<h3 class="panel-title">Fund List</h3>
			</div>
			 <div class="panel-body">
			
				<table class="table">
				        <thead style="text-align: center;">

							<tr>
		
								<th >Fund Ticker</th>
								<th >Fund Name</th>
								<th >Latest Price</th>
				            </tr>

					</thead>

						<tbody>


						</tbody>
					</table>
			 <div >
				<input type="hidden" value="submitIn" name="requestedSubmit"/>
				<input type="submit" class="submit_btn" name="submit" id="submit" value="Submit" />
				</div>
 			 </div>
	
		</form>

</div>





<jsp:include page="bottom.jsp" />