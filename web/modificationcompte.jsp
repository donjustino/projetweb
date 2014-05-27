<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
    "http://www.w3.org/TR/html4/loose.dtd">  

<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saisie utilisateur</title>
    </head>
    <body>
    <link rel="stylesheet" type="text/css"  
          href="${pageContext.request.contextPath}/style.css" />  
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <c:forEach var="u" items="${listeDesUsers}" varStatus="status">  
        <div id="contenu">
            <form class="form-horizontal">
                <fieldset>

                    <legend>Modifier votre compte utilisateur</legend>

                    <!-- Mot de passe -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="password">Mot de passe :</label>  
                        <div class="col-md-4">
                            <input id="motdepasse" name="password" type="password" placeholder="... votre mot de passe ici" class="form-control input-md" required="">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label" for="abonnement">Type d'abonnement :</label><br>
                        <br>
                        <div class="col-lg-6">
                            <input type="radio" name="abonnement" value="gratuit"> Gratuit<br>
                            <input type="radio" name="abonnement" value="weekend"> Un Week-End<br>
                            <input type="radio" name="abonnement" value="semaine" checked> Une Semaine<br>
                            <input type="radio" name="abonnement" value="mois"> Un mois<br>
                            <input type="radio" name="abonnement" value="annee" checked> Une année<br>
                            <input type="radio" name="abonnement" value="vie" checked> A vie<br>
                        </div>
                    </div>

                    <!-- Button -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="buttonValid"></label>
                        <div class="col-md-4">
                            <input type="hidden" name="action" value="modifierCompte"/> 
                            <button id="boutonValid" name="submit" class="btn btn-success">Modifier votre compte</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </c:forEach>  
</body>
</html>
