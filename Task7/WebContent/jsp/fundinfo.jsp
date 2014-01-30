<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include
	page="${accountType == 'E' ? 'admin-top.jsp' : 'customer-top.jsp'}" />

<div class="container">
	<form name="viewFundForm" class="form-horizontal" role="form" action="viewFund.do">
		<div class="page-header">
			<input type="hidden" value=${fund.fundId } name="fundId" />
			<h2 class="text-muted">${fund.symbol}
				<small>${fund.fundName}</small>
			</h2>
			<h3>${price}
				&nbsp; <c:choose>
				<c:when	test="${fn:startsWith(priceDifference, '-')}">
										<span class="label label-danger">${priceDifference }</span>
										</c:when>
									<c:when test="${priceDifference == '' || priceDifference eq null}">
									</c:when>
									<c:otherwise>
										<span class="label label-success">+${priceDifference}</span>
									</c:otherwise>
								</c:choose> 
			</h3>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Details</h3>
			</div>
			<div class="panel-body">
<c:choose>
				<c:when	test="${price ne null || price ne '' || price ne 'price unavailable'}">
				<div class="form-group">
					<label class="col-sm-2 control-label"> Highest for Year
						${year }</label>
					<div class="col-sm-10">
						<p class="form-control-static">${maxValue }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Lowest for Year
						${year }</label>
					<div class="col-sm-10">
						<p class="form-control-static">${minValue }</p>
					</div>
				</div>
				</c:when>
				<c:otherwise>
							<span>No data found</span>
									</c:otherwise>
								</c:choose> 
			</div>
		</div>
		<input type="hidden" name="subaction" value="${subaction }" id="subaction"/>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Price Change &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="text-right"> <a href="javascript:submitForm('view')" >View Price History</a></span></h3>
				
			</div>
			<div class="panel-body">
					Select Period: <select name="period" onchange="javascript:submitForm('change');"
							id="period">
							<option value="1">Last 10</option>
							<option value="2">Previous Month</option>
							
						</select>
						<br/><br/><br/>
				<c:choose>
					<c:when test="${arrayData eq null || fn:length(arrayData) < 3}">
						<span>No enough values</span>
					</c:when>
					<c:otherwise>
						
						<jsp:include page="fundChart.jsp" /></c:otherwise>
				</c:choose>
			</div>
		</div>
	</form>
</div>
<script>
document.getElementById("period").value = ${period};
	
	function submitForm(actionName){
	this.document.getElementById("subaction").value = actionName;
	viewFundForm.submit();
	}

	
</script>
<jsp:include page="bottom.jsp" />