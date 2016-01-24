<jsp:include page="fragments/header.jsp" />
	<div class="row">
	    <div class="col-md-12">
	        <h3>${title}</h3>
	        ${message}
	    </div>
	</div>
	<div class="row">
		<div class="col-md-1"></div>
	    <div class="col-md-10">
	        <form role="form" method="POST" action="${pageContext.request.contextPath}/ajouterService">
			    <div class="form-group">
			      <label>Nom de service:</label>
			      <input name="serviceName" type="text" class="form-control">
			    </div>
			    <div class="form-group">
			      <label>Description:</label>
			      <input name="serviceDescription" type="text" class="form-control">
			    </div>
			    <button type="submit" class="btn btn-default">Enregistrer</button>
			    <br>
			  </form>
	    </div>
	    <div class="col-md-1"></div>
	</div>
<jsp:include page="fragments/footer.jsp" />