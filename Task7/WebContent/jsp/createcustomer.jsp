<jsp:include page="admin-top.jsp" />

<div class="container">

<jsp:include page="errors.jsp" />

	<div class="page-header">
		<h3>New Customer</h3>
	</div>

	<form class="form-horizontal">
		<div class="form-group">
			<label for="inputUsername" class="control-label col-xs-2">Username*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputUsername"
					placeholder="Username" name="username">
			</div>
		</div>

		<div class="form-group">
			<label for="inputLastName" class="control-label col-xs-2">First Name*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputLastName"
					placeholder="First Name" name="firstName">
			</div>
		</div>


		<div class="form-group">
			<label for="inputFirstName" class="control-label col-xs-2">Last
				Name*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputfirstName"
					placeholder="Last Name" name="lastName">
			</div>
		</div>


		<div class="form-group">
			<label for="inputAddressLine1" class="control-label col-xs-2">Address
				Line 1*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputAddressLine1"
					placeholder="Address Line 1" name="address1">
			</div>
		</div>


		<div class="form-group">
			<label for="inputAddressLine2" class="control-label col-xs-2">Address
				Line 2</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputAddressLine1"
					placeholder="Address Line 2" name="address2">
			</div>
		</div>

		<div class="form-group">
			<label for="inputCity" class="control-label col-xs-2">City*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="city" placeholder="City" name="city">
			</div>
		</div>

		<div class="form-group">
			<label for="inputState" class="control-label col-xs-2">State*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputState"
					placeholder="State" name="state">
			</div>
		</div>

		<div class="form-group">
			<label for="inputZip" class="control-label col-xs-2">Zip Code*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputZip"
					placeholder="Zip Code" name="zipcode">
			</div>
		</div>

		<div class="form-group">
			<label for="inputPassword" class="control-label col-xs-2">Password*</label>
			<div class="col-xs-3">
				<input type="password" class="form-control" id="inputPassword"
					placeholder="Password" name="password1">
			</div>
		</div>


		<div class="form-group">
			<label for="confirmPassword" class="control-label col-xs-2">Confirm
				Password*</label>
			<div class="col-xs-3">
				<input type="password" class="form-control" id="confirmPassword"
					placeholder="Password" name="password2">
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