<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include
	page="${accountType == 'E' ? 'admin-top.jsp' : 'customer-top.jsp'}" />

	<div class="container">
		<div class="page-header">
			<h2 class="text-muted">${fund.symbol} <small>${fund.fundName}</small></h2>
			<h3>${price} &nbsp; <span class="label label-success">+7.60 (0.66%)</span></h3>
		</div>
		
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Details</h3>
		  </div>
		  <div class="panel-body">
		    <form class="form-horizontal" role="form">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">All Time High</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">2000</p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">All Time Low</label>
			    <div class="col-sm-10">
			      <p class="form-control-static">40</p>
			    </div>
			  </div>
			</form>
		  </div>
		</div>
		
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Price History</h3>
		  </div>
		  <div class="panel-body">
		    	<jsp:include page="fundChart.jsp" />
		  </div>
		</div>
	
	</div>
	
<jsp:include page="bottom.jsp" />