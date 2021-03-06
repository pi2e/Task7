<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include
	page="${accountType == 'E' ? 'admin-top.jsp' : 'customer-top.jsp'}" />
<script>

function submitForm(userId,type){
if(type == 'c'){
 var url = "changePwd.do?custId="+userId;
}
else{
var url = "changePwd.do";
}
  changePwdForm.action = url;
  changePwdForm.submit();

}
</script>
<div class="container">

	<jsp:include page="errors.jsp" />
	<jsp:include page="success.jsp" />

	<div class="page-header">
		<h3>Change Password</h3>
	</div>

	<form class="form-horizontal" method="post" name="changePwdForm">

		<c:choose>
			<c:when test="${c == 'c'}">
				<div class="form-group">
					<label for="inputCustermerName" class="control-label col-xs-2">Customer
						Name</label>
					<div class="col-xs-3">
						<p class="form-control-static">${customer.lastName},
							${customer.firstName}</p>
					</div>
				</div>

				<div class="form-group">
					<label for="inputCustermerName" class="control-label col-xs-2">Username</label>
					<div class="col-xs-3">
						<p class="form-control-static">${customer.username}</p>
						<input type="hidden" class="form-control" name="userId"
							id="userId" value="${customer.customerId}">
					</div>
				</div>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>

		<div class="form-group">
			<label for="password" class="control-label col-xs-2">New
				Password</label>
			<div class="col-xs-3">
				<input type="password" class="form-control" id="password"
					placeholder="password" value="${form.newPassword}"
					name="newPassword"> <span class="help-block">At
					least 6 characters</span>
			</div>
		</div>

		<div class="form-group">
			<label for="password" class="control-label col-xs-2">Confirm
				Password</label>
			<div class="col-xs-3">
				<input type="password" class="form-control" id="password"
					placeholder="password" value="${form.confirmPassword}"
					name="confirmPassword">
			</div>
		</div>

		<div class="form-group">
			<div class="col-xs-offset-2 col-xs-10">
				<button type="submit" class="btn btn-primary" onClick="javascript:submitForm(${customer.customerId},${c })">Submit</button>
			</div>
		</div>
	</form>

</div>

<jsp:include page="bottom.jsp" />