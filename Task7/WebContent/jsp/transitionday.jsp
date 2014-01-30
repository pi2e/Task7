<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include
	page="${accountType == 'E' ? 'admin-top.jsp' : 'customer-top.jsp'}" />

<div class="container">
	<jsp:include page="errors.jsp" />
	<jsp:include page="success.jsp" />

	<form method="post" action="transitionday.do">

		<div class="panel panel-primary">

			<div class="panel-heading">
				<h2 class="panel-title">Transition Day</h2>
			</div>

			<div class="panel-body">
				<h5>
					Latest Transition Day: <i>${lastdate}</i>
				</h5>
				<br />
				<h5>Current Transition Day:</h5>
				<input type="text" value="${inputdate}" name="inputdate" /> <i>*input
					format: yyyy/mm/dd</i>
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

							<th>Fund Ticker</th>
							<th>Fund Name</th>
							<th class="text-right">Current Price</th>
							<th>New Price</th>
						</tr>

					</thead>

					<tbody>
						<c:forEach var="fund" items="${funds}" varStatus="status">
							<tr>
								<td><a href="#">${fund.getSymbol()}</a></td>
								<td>${fund.getFundName()}</td>
								<td>${lastprices[status.index]}</td>
								<td class="text-right"><input type="text" name="${fund.fundId}"
									value="${inputprice[status.index]}" size="10"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div>
					<input type="hidden" value="submitIn" name="requestedSubmit" /> <input
						type="submit" class="btn btn-primary" name="submit" id="submit"
						value="Submit" />
				</div>
			</div>
		</div>
	</form>

</div>





<jsp:include page="bottom.jsp" />