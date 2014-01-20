<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">

    <title>Carnegie Financial Services</title>

    <!-- Bootstrap core CSS -->
    <link href="dist/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="dist/css/template.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
    <div class="navbar navbar-fixed-top navbar-default" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-flash purple"></span> Carnegie Financial Services</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
				<span class="glyphicon glyphicon-user"></span>
				Admin: ${user.lastName}, ${user.firstName} <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">View Account</a></li>
                <li><a href="#">Change Password</a></li>
              </ul>
            </li>
			<li><a href="#contact">Logout</a></li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
	</div> <!-- /.navbar -->

	<div class="navbar navbar-inverse" role="navigation">
	<div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
			<li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Customer <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="addcustomer.do">Register Customer</a></li>
				<li><a href="#">Deposit Cash</a></li>
                <li><a href="#">View Customers</a></li>
              </ul>
            </li>
			<li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Fund <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">Add New Fund</a></li>
                <li><a href="#">View Funds</a></li>
              </ul>
            </li>
            <li><a href="#contact">Register Employee</a></li>
			<li><a href="#contact">Transaction History</a></li>
			<li><a href="#contact">Transition</a></li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->


    </div><!-- /.navbar -->
