<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</div><!-- /.col-md-8 -->

<div class="col-md-4">
       <div class="well">
       		<c:choose>
			    <c:when test="${empty connected}">
				    <h3>Identifiez-vous</h3>
				    <form role="form" method="POST" action="login">
				      <div class="form-group">
				        <label>Email:</label>
				        <input name="email" type="email" class="form-control">
				      </div>
				      <div class="form-group">
				        <label>Mot de passe:</label>
				        <input name="password" type="password" class="form-control">
				      </div>
				      <div><a href="signup.jsp">Insciption</a></div>
				      <button type="submit" class="btn btn-default">Envoyer</button>
				    </form>
			    </c:when>
			    <c:otherwise>
			       <h2>Bienvenue ${name} ${firstName}</h2>
			       <h3>Je fournis</h3>
			       <div class="well nopadding">
			       	<ul class="list-group">
			       	<c:forEach var="service" items="${userServices}">
			            <li class="list-group-item">
				            <button class="btn btn-xs btn-warning pull-right">
				              <span class="glyphicon glyphicon-trash"></span>
				            </button>
			                <span>${service.titre}</span>
			            </li>
					</c:forEach>
					</ul>
			        <button type="button" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>&nbsp;Ajouter service</button>
			       </div>
			    </c:otherwise>
			</c:choose>
           
       </div>

   </div><!-- col-4  -->
</div><!-- /.row -->
</div>

</body>
</html>
