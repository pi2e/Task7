<jsp:include page="admin-top.jsp" />

<div class="container">

<jsp:include page="errors.jsp" />

	<div class="page-header">
		<h3>New Customer</h3>
	</div>

	<form class="form-horizontal" action="addCustomer.do" method="post">
		<div class="form-group">
			<label for="inputUsername" class="control-label col-xs-2">Username*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputUsername"
					placeholder="Username" name="username" value="${form.username}">
					<span class="help-block">At least 6 characters</span>
			</div>
		</div>

		<div class="form-group">
			<label for="inputLastName" class="control-label col-xs-2">First Name*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputLastName"
					placeholder="First Name" name="firstName" value="${form.firstName}">
			</div>
		</div>


		<div class="form-group">
			<label for="inputFirstName" class="control-label col-xs-2">Last
				Name*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputfirstName"
					placeholder="Last Name" name="lastName" value="${form.lastName}">
			</div>
		</div>

		<div class="form-group">
			<label for="inputAddressLine1" class="control-label col-xs-2">Address
				Line 1*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputAddressLine1"
					placeholder="Address Line 1" name="address1" value="${form.address1}">
			</div>
		</div>


		<div class="form-group">
			<label for="inputAddressLine2" class="control-label col-xs-2">Address
				Line 2</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputAddressLine1"
					placeholder="Address Line 2" name="address2" value="${form.address2}">
			</div>
		</div>

		<div class="form-group">
			<label for="inputCity" class="control-label col-xs-2">City*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="city" placeholder="City" name="city" value="${form.city}">
			</div>
		</div>

		<div class="form-group">
			<label for="inputState" class="control-label col-xs-2">State*</label>
		<div class="col-xs-3">
				<select name="state" class="form-control" value="${form.state}">
					<option value="AK">AK</option>
					<option value="AL">AL</option>
					<option value="AR">AR</option>
					<option value="AZ">AZ</option>
					<option value="CA">CA</option>
					<option value="CO">CO</option>
					<option value="CT">CT</option>
					<option value="DC">DC</option>
					<option value="DE">DE</option>
					<option value="FL">FL</option>
					<option value="GA">GA</option>
					<option value="HI">HI</option>
					<option value="IA">IA</option>
					<option value="ID">ID</option>
					<option value="IL">IL</option>
					<option value="IN">IN</option>
					<option value="KS">KS</option>
					<option value="KY">KY</option>
					<option value="LA">LA</option>
					<option value="MA">MA</option>
					<option value="MD">MD</option>
					<option value="ME">ME</option>
					<option value="MI">MI</option>
					<option value="MN">MN</option>
					<option value="MO">MO</option>
					<option value="MS">MS</option>
					<option value="MT">MT</option>
					<option value="NC">NC</option>
					<option value="ND">ND</option>
					<option value="NE">NE</option>
					<option value="NH">NH</option>
					<option value="NJ">NJ</option>
					<option value="NM">NM</option>
					<option value="NV">NV</option>
					<option value="NY">NY</option>
					<option value="OH">OH</option>
					<option value="OK">OK</option>
					<option value="OR">OR</option>
					<option value="PA">PA</option>
					<option value="RI">RI</option>
					<option value="SC">SC</option>
					<option value="SD">SD</option>
					<option value="TN">TN</option>
					<option value="TX">TX</option>
					<option value="UT">UT</option>
					<option value="VA">VA</option>
					<option value="VT">VT</option>
					<option value="WA">WA</option>
					<option value="WI">WI</option>
					<option value="WV">WV</option>
					<option value="WY">WY</option>
				</select>
			</div>
	
		</div>

		<div class="form-group">
			<label for="inputZip" class="control-label col-xs-2">Zip Code*</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="inputZip"
					placeholder="Zip Code" name="zipcode" value="${form.zipcode}">
			</div>
		</div>

		<div class="form-group">
			<label for="inputPassword" class="control-label col-xs-2">Password*</label>
			<div class="col-xs-3">
				<input type="password" class="form-control" id="inputPassword"
					placeholder="Password" name="password1">
					<span class="help-block">At least 6 characters</span>
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