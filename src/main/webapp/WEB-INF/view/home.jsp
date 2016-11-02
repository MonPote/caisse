<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Xavier
  Date: 29/09/2016
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Entrepot">
    <title>YaKaramel</title>
    <link rel="shortcut icon" href="<c:url value="http://image.flaticon.com/teams/1-freepik.jpg" />">
    <link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" /> ">
    <!-- Custom styles for our template -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.css" />" media="screen">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" >
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9] -->
    <script src="<c:url value="/resources/js/html5shiv.js" />"></script>
    <script src="<c:url value="/resources/js/respond.min.js" />"></script>
    <![endif]-->
</head>

<body id="home">
<!-- Fixed navbar -->
<jsp:include page="header.jsp"/>
<!-- /.navbar -->
<!-- Header -->
<header id="head">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-sm-6">
                <div class="panel panel-primary">
                    <div class="panel-body">
                        <h3>Bienvenue sur le site du Back Office !</h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- /Header -->
<!-- Intro -->
<div class="container text-center">
    <br>
    <br>
    <h2 class="thin">${strg}</h2>
    <br>
    <p>${strh}</p>
</div>
<!-- /Intro-->
<jsp:include page="footer.jsp"/>
<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/headroom.min.js" /> "></script>
<script src="<c:url value="/resources/js/jQuery.headroom.min.js" /> "></script>
<script src="<c:url value="/resources/js/template.js" /> "></script>
<script src="<c:url value="/resources/js/tooltips.js" /> "></script>
</body>

</html>
