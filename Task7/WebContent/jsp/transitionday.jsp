<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include
	page="${accountType == 'E' ? 'admin-top.jsp' : 'customer-top.jsp'}" />



	<div class="container">
		
		<form method="post" action="transitionday.do">
	
	    <div class="panel panel-default">
		
			<div class="panel-heading">
	          <h3>Transition Day</h3>
			</div>
		
			<div class="panel-body">	
	          <h4>Latest Transaction Date: </h4>
	          <h4>Input Next Transaction Date:</h4>              
	          <input type="text" value="${nextDay}" name="inputdate"/>
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
					<c:forEach var="fund" items="${funds}">
						<tr>
							<td><a href="#">${fund.getSymbol()}</a></td>
							<td>${fund.getFundName()}</td>
							<td>
							<input type="text" name="${fund.fundId}" value="" size="10">
							</td>
						</tr>
					</c:forEach>
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