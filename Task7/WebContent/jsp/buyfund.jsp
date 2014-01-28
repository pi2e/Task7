<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Available Funds</h3>
		</div>

		<div class="panel-body">
			<table class="table">
				<thead style="text-align: right;">
					<tr>
						<th>Ticker</th>
						<th>Fund Name</th>
						<th class="text-right">Current Price</th>
						<th></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="fund" items="${funds}" varStatus="status">
						<tr>
							<td><a href="viewFund.do?fundId=${fund.fundId}">${fund.getSymbol()}</a></td>
							<td>${fund.getFundName()}</td>
							<td class="text-right"><c:choose>
									<c:when
										test="${fn:startsWith(priceDifference[status.index], '-')}">
										<span class="label label-danger">${priceDifference[status.index]}</span>
									</c:when>
									<c:when test="${priceDifference[status.index] == ''}">
									</c:when>
									<c:otherwise>
										<span class="label label-success">+${priceDifference[status.index]}</span>
									</c:otherwise>
								</c:choose> <c:choose>
									<c:when test="${fundPrices[status.index] == ''}">
									</c:when>
									<c:otherwise>
									&nbsp;<Strong> $${fundPrices[status.index]} </Strong>
									</c:otherwise>
								</c:choose></td>
							<td class="text-right"><c:choose>
									<c:when test="${accountType == 'E'}">
									</c:when>
									<c:otherwise>
										<form method="post" action="buyFund.do">
											<input type="hidden" name="buyFund" value="${fund.fundName}" />
											<input type="submit" class="btn btn-primary" name="submit"
												id="submit" value="Buy" />
										</form>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>


	<jsp:include page="bottom.jsp" />