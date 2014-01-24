<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include
	page="${accountType == 'E' ? 'admin-top.jsp' : 'customer-top.jsp'}" />

<div class="container">
<jsp:include page="success.jsp" />
	<jsp:include page="errors.jsp" />
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
						<th>Current Price</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="fund" items="${funds}" varStatus="status">
						<tr>
							<td><a href="viewFund.do?fundId=${fund.fundId}">${fund.getSymbol()}</a></td>
							<td>${fund.getFundName()}</td>
							<td><c:choose>
									<c:when test="${fundPrices[status.index] == ''}">
									</c:when>
									<c:otherwise>
									&nbsp;<Strong> $${fundPrices[status.index]} </Strong>
									</c:otherwise>
								</c:choose><c:choose>
									<c:when
										test="${fn:startsWith(priceDifference[status.index], '-')}">
										<span class="label label-danger">${priceDifference[status.index]}</span>
									</c:when>
									<c:when test="${priceDifference[status.index] == ''}">
									</c:when>
									<c:otherwise>
										<span class="label label-success">+${priceDifference[status.index]}</span>
									</c:otherwise>
								</c:choose></td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</div>

<jsp:include page="bottom.jsp" />