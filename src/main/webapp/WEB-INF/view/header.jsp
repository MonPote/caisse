<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nea
  Date: 10/06/15
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar navbar-inverse navbar-fixed-top headroom">
  <div class="container">
    <div class="navbar-header">
      <!-- Button for smallest screens -->
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}"><img src="http://image.flaticon.com/teams/1-freepik.jpg" /></a>
    </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav pull-right">
        <li>
          <a href="${pageContext.request.contextPath}"><i class="fa fa-home"></i> Accueil</a>
        </li>
        </li>
      </ul>
    </div>
    <!--/.nav-collapse -->
  </div>
</div>
