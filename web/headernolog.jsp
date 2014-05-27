<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="image">
            <a href="${pageContext.request.contextPath}">  
                <img src="${pageContext.request.contextPath}/musique.jpg" width="100" height="50" />  
            </a> 
        </div>

        <div>
            <h1>Music Store</h1>
        </div>
        <hr>
         <fieldset text-align="center">
            <form action="ServletLogin" method="get" class="form-log">
                <!-- formulaire login / password -->
                <input id="textinput" name="log" type="text" placeholder="login" class="form-control input-md" required=""></br>
                <input id="passwordinput" name="pass" type="password" placeholder="mot de passe" class="form-control input-md" required=""></br>
                <input type="hidden" name="action" value="checkConnexion">
                <button id="singlebutton" name="submit" type="submit" class="btn btn-success">Connexion</button>         
            </form>
            <a href="ServletLogin?action=creerUnUtilisateur">Créer un utilisateur</a>
        </fieldset>
        <hr>
    </body>
</html>
