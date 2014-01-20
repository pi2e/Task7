<jsp:include page="customer-top.jsp" />




	<div class="container">
	<form method="post" action="depositCheck.do">
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Customer List</h3>
		  </div>
		  <div class="panel-body">
		    	<table class="table">
				        <thead style="text-align: center;">
							
							<tr>
								<th >User Name</th>
								<th >First Name</th>
								<th >Last Name</th>
								<th >Email Address</th>
								<th> Deposit Check</th>
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
		  </div>
		
		</form>
		</div>












<jsp:include page="bottom.jsp" />