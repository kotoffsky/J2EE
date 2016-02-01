<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="vues/fragments/header.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
			  <h2>Créer un utilisateur</h2>
			  <form role="form" method="POST" action="createUser">
			    <div class="form-group">
			      <label>Email:</label>
			      <input name="email" type="email" class="form-control">
			    </div>
			    <div class="form-group">
			      <label>Mot de passe:</label>
			      <input name="password" type="password" class="form-control">
			    </div>
			    <div class="form-group">
			      <label>Prénom:</label>
			      <input name="firstName" type="text" class="form-control">
			    </div>
			    <div class="form-group">
			      <label>Nom:</label>
			      <input name="name" type="text" class="form-control">
			    </div>
			    <button type="submit" class="btn btn-default">Envoyer</button>
			  </form>
			 </div><!-- /.row -->
			</div><!-- /.row -->
		</div> <!-- /.container -->

</div><!-- col-8  -->
</div><!-- /.row -->
</div>

</body>
</html>
