<jsp:include page="fragments/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	                    <a href="#" class="btn btn-default">Je fournis</a>
	                    <a href="#" class="btn btn-default">Je demande</a>
	                </p>
	            </div>
	        </div>
	    </div>
	</c:forEach>
	</div>
<jsp:include page="fragments/footer.jsp" />