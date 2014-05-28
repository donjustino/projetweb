<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
    "http://www.w3.org/TR/html4/loose.dtd">  

<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogue de musique</title>
    </head>
    <body>
        <div id="contenu">
            <c:if test="${param['action'] == 'listerLesMusiques'}" >  
                <fieldset>
                    <legend>Liste des musiques</legend>
                    <form action="ServletUsers" method="get"> 
                        <table class="table" border="2">  
                            <!-- La ligne de titre du tableau des comptes -->  
                            <thead>
                                <tr>  
                                    <th><b>Artiste</b></th>  
                                    <th><b>Titre</b></th>  
                                    <th><b>Année</b></th> 
                                    <th><b>Wikipedia</b></th>
                                    <th><b>Piste d'instrument</b></th> 
                                    <th><b>Acheter</b></th>

                                </tr>  
                            </thead>
                            <!-- Ici on affiche les lignes, une par utilisateur -->  
                            <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                            <c:set var="total" value="0"/>  
                            <c:forEach var="u" items="${listeDesMusiques}" varStatus="status">  
                                <tr class="${status.index%2==0 ? 'alt' : ''}">  


                                    <td>${u.artiste.nom}</td>  
                                    <td>${u.titre}</td> 
                                    <td>${u.annee}</td>  
                                    <td><a href="${u.artiste.url}"> lien</a></td>
                                    <td>
                                        <c:forEach var="p" items="${u.pistes}">
                                            ${p.nom} <br >
                                        </c:forEach>
                                    </td> 
                                    <td></td>
                                </tr>  
                            </c:forEach>   
                        </table>  
                    </form>
                </fieldset>
            </c:if> 
            
        </div>
    </body>
</html>