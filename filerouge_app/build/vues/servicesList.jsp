<jsp:include page="fragments/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<div class="row">
	    <div class="col-lg-12">
	        <h3>${title}</h3>
	    </div>
	</div>
	<div class="row text-center">
	<c:forEach var="service" items="${services}">
	    <div class="col-md-6 col-sm-6 hero-feature">
	        <div class="thumbnail">
	            <div class="caption">
	                <h3>${service.titre}</h3>
	                <p>${service.description}</p>
	                <p>
						<c:set var="contains" value="false" />
						<c:forEach var="demandeService" items="${userServicesDemande}">
						  <c:if test="${demandeService.id eq service.id}">
						    <c:set var="contains" value="true" />
						  </c:if>
						</c:forEach>
		                <c:choose>
						    <c:when test="${contains}">
						        <a href="${pageContext.request.contextPath}/retirerAssociation/demande/${service.id}" class="btn btn-default">Je ne demande plus</a>
						    </c:when>
						    <c:otherwise>
						        <a href="${pageContext.request.contextPath}/ajouterAssociation/demande/${service.id}" class="btn btn-default">Je demande</a>
						    </c:otherwise>
						</c:choose>
						<c:set var="contains" value="false" />
						<c:forEach var="demandeService" items="${userServicesOffre}">
						  <c:if test="${demandeService.id eq service.id}">
						    <c:set var="contains" value="true" />
						  </c:if>
						</c:forEach>
						<c:choose>
						    <c:when test="${contains}">
						        <a href="${pageContext.request.contextPath}/retirerAssociation/offre/${service.id}" class="btn btn-default">Je n'ai plus besoin</a>
						    </c:when>
						    <c:otherwise>
						        <a href="${pageContext.request.contextPath}/ajouterAssociation/offre/${service.id}" class="btn btn-default">Je fournis</a>
						    </c:otherwise>
						</c:choose>
	                </p>
	            </div>
	        </div>
	    </div>
	</c:forEach>
	</div>
<jsp:include page="fragments/footer.jsp" />