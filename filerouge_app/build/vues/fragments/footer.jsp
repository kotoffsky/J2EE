<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
</div><!-- /.col-md-8 -->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-md-4">
       <div class="well">
       		<c:choose>
			    <c:when test="${empty connected}">
				    <h3>Identifiez-vous</h3>
				    <form role="form" method="POST" action="${pageContext.request.contextPath}/login">
				      <div class="form-group">
				        <label>Email:</label>
				        <input name="email" type="email" class="form-control">
				      </div>
				      <div class="form-group">
				        <label>Mot de passe:</label>
				        <input name="password" type="password" class="form-control">
				      </div>
				      <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-log-in"></span>&nbsp;Entrer</button>
				      <a href="${pageContext.request.contextPath}/signup.jsp" type="button" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-heart"></span>&nbsp;Inscription</a>
				    </form>
			    </c:when>
			    <c:otherwise>
			       <h2>Bienvenue ${name} ${firstName}</h2>
			       <a type="button" class="btn btn-default btn-sm pull-right" href="${pageContext.request.contextPath}/logout">Logout&nbsp;<span class="glyphicon glyphicon-log-out"></span></a>
			       <h3>Je fournis</h3>
			       	<ul class="list-group">
			       	<c:forEach var="service" items="${userServicesOffre}">
			            <li class="list-group-item">
				            <a class="btn btn-xs btn-warning pull-right" href="${pageContext.request.contextPath}/retirerAssociation/offre/${service[0].id}">
				              <span class="glyphicon glyphicon-trash"></span>
				            </a>
			                <span>${service[0].titre}</span><br>
			                <jsp:useBean id="dateValueOffre" class="java.util.Date"/>
							<jsp:setProperty name="dateValueOffre" property="time" value="${service[1].dateDeLimite * 1000}"/>
			                <span>Actuel jusqu'à : <b><fmt:formatDate value="${dateValueOffre}" pattern="dd-MM-yyyy HH:mm"/></b></span>
			            </li>
					</c:forEach>
					</ul>
					<h3>Je demande</h3>
			       	<ul class="list-group user-list">
			       	<c:forEach var="service" items="${userServicesDemande}">
			            <li class="list-group-item">
				            <a class="btn btn-xs btn-warning pull-right" href="${pageContext.request.contextPath}/retirerAssociation/demande/${service[0].id}">
				              <span class="glyphicon glyphicon-trash"></span>
				            </a>
			                <span>${service[0].titre}</span><br>
			                <jsp:useBean id="dateValueDemande" class="java.util.Date"/>
							<jsp:setProperty name="dateValueDemande" property="time" value="${service[1].dateDeLimite * 1000}"/>
			                <span>Actuel jusqu'à : <b><fmt:formatDate value="${dateValueDemande}" pattern="dd-MM-yyyy HH:mm"/></b></span>
			            </li>
					</c:forEach>
					</ul>
					<div class="row">
						<div class="col-md-12">
				        	<a type="button" class="btn btn-block btn-lg btn-warning lancer-cycle" href="${pageContext.request.contextPath}/collaborationRecherche"><span class="glyphicon glyphicon-play-circle"></span>&nbsp;Trouver des collaborateurs</a>
				        </div>
				    </div>
				    <br>
				    <div class="row">
				        <div class="col-md-2"></div>
				        <div class="col-md-8">
				        	<a type="button" class="btn btn-block btn-success" href="${pageContext.request.contextPath}/serviceAjouterFormulaire"><span class="glyphicon glyphicon-plus"></span>&nbsp;Ajouter un service</a>
				        </div>
				        <div class="col-md-2"></div>
			        </div>
			    </c:otherwise>
			</c:choose>
           
       </div>

   </div><!-- col-4  -->
</div><!-- /.row -->
</div>

</body>
</html>
