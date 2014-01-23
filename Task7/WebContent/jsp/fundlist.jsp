<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include
	page="${accountType == 'E' ? 'admin-top.jsp' : 'customer-top.jsp'}" />

<div class="container">
	<jsp:include page="success.jsp" />
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Available Funds</h3>
		</div>

		<div class="panel-body">
			<table class="table">
				<thead style="text-align: center;">
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
							<td>$${fundPrices[status.index]}</td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
		</div>
	</div>
</div>

<jsp:include page="bottom.jsp" />