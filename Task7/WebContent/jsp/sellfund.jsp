<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="customer-top.jsp" />

<div class="container">
	<div class="page-header">
		<h3>Sell Fund</h3>
	</div>
	<jsp:include page="errors.jsp" />
	<form class="form-horizontal" action="sellFund.do" method="post">

		<div class="form-group">
			<label for="inputTicker" class="control-label col-xs-2">Ticker</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" placeholder="Ticker"
					name="ticker" value="${form.ticker}">
			</div>
		</div>

		<div class="form-group">
			<label for="inputDollar" class="control-label col-xs-2">Number
				of Shares</label>
			<div class="col-xs-3">
				<input type="text" class="form-control"
					placeholder="Number of Shares" name="shares" value="${form.shares}">
			</div>
		</div>



		<div class="form-group">
			<div class="col-xs-offset-2 col-xs-10">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</div>
	</form>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Funds</h3>
		</div>
		<div class="panel-body">
			<table class="table">
				<thead style="text-align: center;">

					<tr>
						<th>Ticker</th>
						<th>Fund Name</th>
						<th class="text-right">Current Price</th>
						<th class="text-right">Number of Shares</th>
						<th class="text-right">Available Shares</th>
						<th class="text-right">Position Value</th>
						<th></th>
					</tr>

				</thead>

				<tbody>
					<c:forEach var="fundVO" items="${fundVOList}">
						<tr>
							<td><a href="viewFund.do?fundId=${fundVO.fundId}">${fundVO.ticker}</a></td>
							<td>${fundVO.fundName}</td>
							<td class="text-right">$${fundVO.currentPrice}</td>
							<td class="text-right">${fundVO.shares}</td>
							<td class="text-right">${fundVO.availableShares}</td>
							<td class="text-right">$${fundVO.positionValue}</td>
							<td class="text-right"><c:choose>
									<c:when test="${accountType == 'E'}">
									</c:when>
									<c:otherwise>
										<form method="post" action="sellFund.do">
											<input type="hidden" name="sellFund" value="${fundVO.ticker}" /> 
											<input
												type="submit" class="btn btn-primary" name="submit"
												id="submit" value="Sell"/>
										</form>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>


<jsp:include page="bottom.jsp" />