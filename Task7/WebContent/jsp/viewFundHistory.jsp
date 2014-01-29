<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include
	page="${accountType == 'E' ? 'admin-top.jsp' : 'customer-top.jsp'}" />

<div class="container">
	<jsp:include page="success.jsp" />
	<jsp:include page="errors.jsp" />
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Fund Value History</h3>
		</div>
		
<div class="page-header">
			<input type="hidden" value=${fund.fundId } name="fundId" />
			<h2 class="text-muted">${fund.symbol}
				<small>${fund.fundName}</small>
			</h2>
			
			<h3> $ ${price}
			<c:choose>
			<c:when	test="${fn:startsWith(todayPriceChange, '-')}">
				&nbsp; 
				Recent Change
				
										<span class="label label-danger">${todayPriceChange }</span>
										&nbsp; <span class="label label-danger">${todayPercentChange }</span>
									</c:when>
									<c:when test="${todayPriceChange == '' || todayPriceChange eq null}">
									</c:when>
									<c:otherwise>
										<span class="label label-success">+${todayPriceChange}</span>
										&nbsp; <span class="label label-success">${todayPercentChange }</span>
									</c:otherwise>
								</c:choose> 
				
				<span class="label label-success"></span>
				
			</h3>
		</div>
		
		<div class="panel-body">
			<table class="table">
				<thead style="text-align: right;">
					<tr>
						<th>Date</th>
						<th class="text-right">Fund Value</th>
						<th class="text-right">Price Change</th>
						<th class="text-right">Percentage Change</th>
						<th></th>
					</tr>
				</thead>
			
				<tbody>
				<c:choose>
				<c:when test="${priceHistoryData ne null }">
					<c:forEach var="history" items="${priceHistoryData}" varStatus="status">
						<tr>
							<td>${history.priceDate }</td>
							<td class="text-right"><c:if test="${priceRecord[status.index] ne null}">
							$ ${priceRecord[status.index]}</c:if></td>
							<td class="text-right"><c:choose>
									<c:when
										test="${fn:startsWith(priceDifference[status.index], '-')}">
										<span class="label label-danger">${priceDifference[status.index]}</span>
									</c:when>
									<c:when test="${priceDifference[status.index] == '' || priceDifference[status.index] eq null}">
							 
									</c:when>
									<c:otherwise>
										<span class="label label-success">+${priceDifference[status.index]}</span>
									</c:otherwise>
								</c:choose> </td>
							<td class="text-right"><c:choose>
									<c:when
										test="${fn:startsWith(percentageDifference[status.index], '-')}">
										<span class="label label-danger">${percentageDifference[status.index]}</span>
									</c:when>
									<c:when test="${percentageDifference[status.index] == '' || percentageDifference[status.index] eq null}">
									</c:when>
									<c:otherwise>
										<span class="label label-success">+${percentageDifference[status.index]}</span>
									</c:otherwise>
								</c:choose> </td>
							
						</tr>
					</c:forEach>
				</tbody>
</c:when>
<c:otherwise>No Records found
</c:otherwise></c:choose>
			</table>
		</div>
		
	</div>
</div>

<jsp:include page="bottom.jsp" />