<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh">
	<head>

		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta charset="utf-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">

		<!-- 最新 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet"
			href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">

		<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
		<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>

		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script
			src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>

		<style>
body {
	padding-top: 20px;
}

.footer {
	border-top: 1px solid #eee;
	margin-top: 40px;
	padding-top: 40px;
	padding-bottom: 40px;
}

/* Main marketing message and sign up button */
.jumbotron {
	text-align: center;
	background-color: transparent;
}

.jumbotron .btn {
	font-size: 21px;
	padding: 14px 24px;
}

/* Customize the nav-justified links to be fill the entire space of the .navbar */
.nav-justified {
	background-color: #eee;
	border-radius: 5px;
	border: 1px solid #ccc;
}

.nav-justified>li>a {
	margin-bottom: 0;
	padding-top: 15px;
	padding-bottom: 15px;
	color: #777;
	font-weight: bold;
	text-align: center;
	border-bottom: 1px solid #d5d5d5;
	background-color: #e5e5e5; /* Old browsers */
	background-repeat: repeat-x; /* Repeat the gradient */
	background-image: -moz-linear-gradient(top, #f5f5f5 0%, #e5e5e5 100%);
	/* FF3.6+ */
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #f5f5f5),
		color-stop(100%, #e5e5e5) ); /* Chrome,Safari4+ */
	background-image: -webkit-linear-gradient(top, #f5f5f5 0%, #e5e5e5 100%);
	/* Chrome 10+,Safari 5.1+ */
	background-image: -o-linear-gradient(top, #f5f5f5 0%, #e5e5e5 100%);
	/* Opera 11.10+ */
	filter: progid :   DXImageTransform.Microsoft.gradient (    
		startColorstr = 
		 '#f5f5f5', endColorstr =   '#e5e5e5', GradientType =   0 );
	/* IE6-9 */
	background-image: linear-gradient(top, #f5f5f5 0%, #e5e5e5 100%);
	/* W3C */
}

.nav-justified>.active>a,.nav-justified>.active>a:hover,.nav-justified>.active>a:focus
	{
	background-color: #ddd;
	background-image: none;
	box-shadow: inset 0 3px 7px rgba(0, 0, 0, .15);
}

.nav-justified>li:first-child>a {
	border-radius: 5px 5px 0 0;
}

.nav-justified>li:last-child>a {
	border-bottom: 0;
	border-radius: 0 0 5px 5px;
}

@media ( min-width : 768px) {
	.nav-justified {
		max-height: 52px;
	}
	.nav-justified>li>a {
		border-left: 1px solid #fff;
		border-right: 1px solid #d5d5d5;
	}
	.nav-justified>li:first-child>a {
		border-left: 0;
		border-radius: 5px 0 0 5px;
	}
	.nav-justified>li:last-child>a {
		border-radius: 0 5px 5px 0;
		border-right: 0;
	}
}

/* Responsive: Portrait tablets and up */
@media screen and (min-width: 768px) {
	/* Remove the padding we set earlier */
	.masthead,.marketing,.footer {
		padding-left: 0;
		padding-right: 0;
	}
}
</style>

<script>

		/**
		var addInterval;
		function addOne(){
			var now=parseInt($("#progress").attr("aria-valuenow"));
			if(now>=100){
				clearInterval(addInterval);
				return;
			}
			now++;
			$("#progress").attr("aria-valuenow",now);
			$("#progress").attr("style","width:"+now+"%");
		}
		
		$(document).ready(function(){
			addInterval=setInterval(addOne,200);
			
		});
		**/

		</script>
	</head>

	<body>

		<div class="container">

			<div class="masthead">
				<h3 class="text-muted">
					Project name
				</h3>
				<ul class="nav nav-justified">
					<li class="active">
						<a href="#">Home</a>
					</li>
					<li>
						<a href="#">Projects</a>
					</li>
					<li>
						<a href="#">Services</a>
					</li>
					<li>
						<a href="#">Downloads</a>
					</li>
					<li>
						<a href="#">About</a>
					</li>
					<li>
						<a href="#">Contact</a>
					</li>
				</ul>
			</div>
			

			<!-- Jumbotron -->
			<div class="jumbotron">
				<h1>
					Marketing stuff!
				</h1>
				<p class="lead">
					Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
					ut fermentum massa justo sit amet.
				</p>
				<p>
					<a class="btn btn-lg btn-success" href="#" role="button">Get
						started today</a>
				</p>
			</div>

			<!-- Example row of columns -->
			<div class="row">
				<div class="col-lg-4">
					<h2>
						Safari bug warning!
					</h2>
					<p class="text-danger">
						Safari exhibits a bug in which resizing your browser horizontally
						causes rendering errors in the justified nav that are cleared upon
						refreshing.
					</p>
					<p>
						Donec id elit non mi porta gravida at eget metus. Fusce dapibus,
						tellus ac cursus commodo, tortor mauris condimentum nibh, ut
						fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.
					</p>
					<p>
						<a class="btn btn-primary" href="#" role="button">View details
							&raquo;</a>
					</p>
				</div>
				<div class="col-lg-4">
					<h2>
						Heading
					</h2>
					<p>
						Donec id elit non mi porta gravida at eget metus. Fusce dapibus,
						tellus ac cursus commodo, tortor mauris condimentum nibh, ut
						fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.
					</p>
					<p>
						<a class="btn btn-primary" href="#" role="button">View details
							&raquo;</a>
					</p>
				</div>
				<div class="col-lg-4">
					<h2>
						Heading
					</h2>
					<p>
						Donec sed odio dui. Cras justo odio, dapibus ac facilisis in,
						egestas eget quam. Vestibulum id ligula porta felis euismod
						semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris
						condimentum nibh, ut fermentum massa.
					</p>
					<p>
						<a class="btn btn-primary" href="#" role="button">View details
							&raquo;</a>
					</p>
				</div>
			</div>

			<div class="input-group">
				<span class="input-group-addon">@</span>
				<input type="text" class="form-control" placeholder="Username">
			</div>

			<ol class="breadcrumb">
				<li>
					<a href="#">Home</a>
				</li>
				<li>
					<a href="#">Library</a>
				</li>
				<li class="active">
					Data
				</li>
			</ol>

			<div class="progress progress-striped active">
				<div id="progress" class="progress-bar" role="progressbar" aria-valuenow="0"
					aria-valuemin="0" aria-valuemax="100" style="width:1%">
					<span class="sr-only">45% Complete</span>
				</div>
			</div>
			
			
			<button type="button" class="btn btn-success btn-lg">
			  <span class="glyphicon glyphicon-star"></span> Star
			</button>
			
			<div class="btn-group">
			  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
			    Action <span class="caret"></span>
			  </button>
			  <ul class="dropdown-menu" role="menu">
			    <li><a href="#">Action</a></li>
			    <li><a href="#">Another action</a></li>
			    <li><a href="#">Something else here</a></li>
			    <li class="divider"></li>
			    <li><a href="#">Separated link</a></li>
			  </ul>
			</div>
			
			
			
			<!-- Site footer -->
			<div class="footer">
				<p>
					&copy; Company 2013
				</p>
			</div>
			
		</div>
		
		
	</body>
</html>
