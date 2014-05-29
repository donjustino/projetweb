<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
    "http://www.w3.org/TR/html4/loose.dtd">  

<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogue de musique</title>
        <style type="text/css">
            #report { border-collapse:collapse;}
            #report h4 { margin:0px; padding:0px;}
            #report img { float:right;}
            #report ul { margin:10px 0 10px 40px; padding:0px;}
            #report th { background:#7CB8E2 url(header_bkg.png) repeat-x scroll center left; color:#fff; padding:7px 15px; text-align:left;}
            #report td { background:#C7DDEE none repeat-x scroll center left; color:#000; padding:7px 15px; }
            #report tr.odd td { background:#fff url(row_bkg.png) repeat-x scroll center left; cursor:pointer; }
            #report div.arrow { background:transparent url(img/arrows.png) no-repeat scroll 0px -16px; width:16px; height:16px; display:block;}
            #report div.up { background-position:0px 0px;}
        </style>
    </head>
    <body>
        <div id="contenu">
            <c:if test="${param['action'] == 'listerLesMusiques'}" >  
                <fieldset>
                    <legend>Liste des musiques</legend>
                    <form action="ServletUsers" method="get"> 
                        <table class="table" border="2" id="report">  
                            <!-- La ligne de titre du tableau des comptes -->  
                            <thead>
                                <tr>  
                                    <th>down</th>
                                    <th><b>Artiste</b></th>  
                                    <th><b>Titre</b></th>  
                                    <th><b>Année</b></th> 
                                    <th><b>Wikipedia</b></th>
                                    <th><b>Piste d'instrument</b></th> 
                                    <th><b>Ecouter</b></th>
                                </tr>  
                            </thead>
                            <!-- Ici on affiche les lignes, une par utilisateur -->  
                            <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                            <c:set var="total" value="0"/>  
                            <c:forEach var="u" items="${listeDesMusiques}" varStatus="status">  
                                <!--<tr class="${status.index%2==0 ? 'alt' : ''}">-->  
                                <tr>
                                    <td><div class="arrow">dddMainContent</div></td>
                                    <td>${u.artiste.nom}</td>  
                                    <td>${u.titre}</td> 
                                    <td>${u.annee}</td>  
                                    <td><a href="${u.artiste.url}"> lien</a></td>
                                    <td>
                                        <c:forEach var="p" items="${u.pistes}">
                                            ${p.nom} <br>
                                        </c:forEach>
                                    </td> 
                                    <td><a href="http://www.perdu.com"><img src="play.png" width="25" height="25" title="ecouter"/> </a>></td>
                                </tr> 
                                <tr>
                                    <td colspan="5">
                                        <h4>Additional information</h4>
                                        <ul>
                                            <li><a href="http://en.wikipedia.org/wiki/Usa">USA on Wikipedia</a></li>
                                            <li><a href="http://nationalatlas.gov/">National Atlas of the United States</a></li>
                                        </ul>   
                                    </td>
                                </tr>
                            </c:forEach>   
                        </table>  
                    </form>
                </fieldset>
            </c:if> 

        </div>
    </body>
    <script src="js/jExpand.js"></script>
</html>