<jsp:include page="admin-top.jsp" />

<div class="container">

	<jsp:include page="errors.jsp" />

	<div class="page-header">
		<h3>Change Customer Password</h3>
	</div>

	<form class="form-horizontal" method="post" action="changePwd.do?custId=${customer.customerId}">
		<div class="form-group">
			<label for="inputCustermerName" class="control-label col-xs-2">Customer
				Name</label>
			<div class="col-xs-3">
				<p class="form-control-static">${customer.lastName}, ${customer.firstName}</p>
			</div>
		</div>

		<div class="form-group">
			<label for="inputCustermerName" class="control-label col-xs-2">Username</label>
			<div class="col-xs-3">
				<p class="form-control-static">${customer.username}</p>
				<input type="hidden" class="form-control"
					name="userId" id="userId" value="${customer.customerId}">
			</div>
		</div>

		<div class="form-group">
			<label for="password" class="control-label col-xs-2">New Password</label>
			<div class="col-xs-3">
				<input type="password" class="form-control" id="password"
					placeholder="password" value="${form.newPassword}" name="newPassword">
			</div>
		</div>
		
		<div class="form-group">
			<label for="password" class="control-label col-xs-2">Confirm Password</label>
			<div class="col-xs-3">
				<input type="password" class="form-control" id="password"
					placeholder="password" value="${form.confirmPassword}" name="confirmPassword">
			</div>
		</div>

		<div class="form-group">
			<div class="col-xs-offset-2 col-xs-10">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</div>
	</form>

</div>

<jsp:include page="bottom.jsp" />