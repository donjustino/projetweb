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
         
        <label class="control-label" for="textinput">Bonjour, ${login} </label><br>
            <a href="ServletLogin?action=deconnexion">Déconnexion</a>
            <a href="ServletUsers?action=modifiercompte">Gérer compte</a>
        
        <hr>
    </body>
</html>
