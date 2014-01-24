<jsp:include page="admin-top.jsp" />

<div class="container">

<jsp:include page="errors.jsp" />


	<div class="page-header">
		<h3>New Employee</h3>
	</div>

	<form class="form-horizontal" action="addEmployee.do" method="post">
		<div class="form-group">
			<label for="inputUsername" class="control-label col-xs-2">Username*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputUsername"
					placeholder="Username" name="username" value="${form.username}">
			</div>
		</div>

		<div class="form-group">
			<label for="inputFirstName" class="control-label col-xs-2">First
				Name*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputFirstName"
					placeholder="First Name" name="firstName" value="${form.firstname}">
			</div>
		</div>

		<div class="form-group">
			<label for="inputLastName" class="control-label col-xs-2">Last
				Name*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputLastName"
					placeholder="Last Name" name="LastName" value="${form.lastname}">
			</div>
		</div>

		<div class="form-group">
			<label for="inputPassword" class="control-label col-xs-2">Password*</label>
			<div class="col-xs-3">
				<input type="password" class="form-control" id="inputPassword"
					placeholder="Password" name="password1" value="${form.password1}">
			</div>
		</div>


		<div class="form-group">
			<label for="confirmPassword" class="control-label col-xs-2">Confirm
				Password*</label>
			<div class="col-xs-3">
				<input type="password" class="form-control" id="confirmPassword"
					placeholder="Password" name="password2" value="${form.password2}">
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
