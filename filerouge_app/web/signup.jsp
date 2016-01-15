<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Signup</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
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
</div>

</body>
</html>
