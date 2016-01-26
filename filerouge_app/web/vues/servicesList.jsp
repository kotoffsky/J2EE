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
						  <c:if test="${demandeService[0].id eq service.id}">
						    <c:set var="contains" value="true" />
						  </c:if>
						</c:forEach>
		                <c:choose>
						    <c:when test="${contains}">
						        <a href="${pageContext.request.contextPath}/retirerAssociation/demande/${service.id}" class="btn btn-default"><span class="glyphicon glyphicon-remove text-danger"></span>&nbsp;Je n'ai plus besoin</a>
						    </c:when>
						    <c:otherwise>
						        <a href="${pageContext.request.contextPath}/ajouterAssociation/demande/${service.id}" class="btn btn-default demande"><span class="glyphicon glyphicon-plus text-success"></span>&nbsp;Je demande</a>
						    </c:otherwise>
						</c:choose>
						<c:set var="contains" value="false" />
						<c:forEach var="offreService" items="${userServicesOffre}">
						  <c:if test="${offreService.id eq service.id}">
						    <c:set var="contains" value="true" />
						  </c:if>
						</c:forEach>
						<c:choose>
						    <c:when test="${contains}">
						        <a href="${pageContext.request.contextPath}/retirerAssociation/offre/${service.id}" class="btn btn-default"><span class="glyphicon glyphicon-remove text-danger"></span>&nbsp;Je ne fournis plus</a>
						    </c:when>
						    <c:otherwise>
						        <a href="${pageContext.request.contextPath}/ajouterAssociation/offre/${service.id}" class="btn btn-default offre"><span class="glyphicon glyphicon-plus text-success"></span>&nbsp;Je fournis</a>
						    </c:otherwise>
						</c:choose>
	                </p>
	            </div>
	        </div>
	    </div>
	</c:forEach>
	
	<!-- JS modal with datepicker -->
	<!-- Small modal -->
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm">Small modal</button>
	
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	  <div class="modal-dialog modal-sm">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title"></h4>
	      </div>
	      <div class="modal-body">
	        <div style="overflow:hidden;">
			    <div class="form-group">
			        <div class="row">
			            <div class="col-md-12">
			                <div id="datetimepicker12">
			                	<input type="hidden" class="form-control datevalue" />
			                </div>
			            </div>
			        </div>
			    </div>
			</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary save-assoc">Enregistrer</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<script>
		$(function () {
            $('#datetimepicker12').datetimepicker({
                inline: true
            });
        });
        var link = '';
		$('.demande, .offre').click(function(e){
			e.preventDefault();
			e.stopPropagation();
			link = $(this).attr('href');
			$('.bs-example-modal-sm').find('.datevalue').val('');
			$('.bs-example-modal-sm').modal('show');
			if($(this).hasClass('demande')) {
				$('.bs-example-modal-sm').find('.modal-title').html('Précisez la date de limite de votre demande');
			}
			if($(this).hasClass('offre')) {
				$('.bs-example-modal-sm').find('.modal-title').html('Précisez la date de limite de votre offre');
			}
		});
		$('.save-assoc').click(function() {
			var datevalue = $('.bs-example-modal-sm').find('.datevalue').val();
			console.log(datevalue);
			if(datevalue=="" || datevalue==undefined){
				console.log("Choisissez la date de limite pour le service");
				var timestampVal = (new Date().getTime() / 1000).toFixed(0);
			} else {
				var timestampVal = (new Date(datevalue).getTime() / 1000).toFixed(0);
			}
			console.log(timestampVal);
			link += '/'+timestampVal;
			console.log(link);
			$('.bs-example-modal-sm').modal('hide');
			window.location.replace(link);
		});
	</script>
	
	</div>
<jsp:include page="fragments/footer.jsp" />