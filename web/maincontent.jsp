<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
    "http://www.w3.org/TR/html4/loose.dtd">  

<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogue de musique</title>
        <!-- Script Ajax JQuery -->
        <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js" type="text/javascript"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js" type="text/javascript"></script>
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
        <script type="text/javascript">
            $(document).ready(function() {
                $("#report tr:odd").addClass("odd");
                $("#report tr:not(.odd)").hide();
                $("#report tr:first-child").show();

                $("#report tr.odd").click(function() {
                    $(this).next("tr").toggle();
                    $(this).find(".arrow").toggleClass("up");
                });
                //$("#report").jExpand();
            });
        </script>   
    </head>
    <body>
        <div id="contenu">
            <c:if test="${param['action'] == 'listerLesMusiques'}" >  
                <fieldset>
                    <legend>Liste des musiques</legend>
                    <form action="ServletUsers" method="get"> 
                        <table class="table" border="2" id="report">  
                            <!-- La ligne de titre du tableau des comptes -->  
                            <tbody>
                                <tr>  
                                    <th>Piste</th>
                                    <th><b>Artiste</b></th>  
                                    <th><b>Titre</b></th>  
                                    <th><b>Wikipedia</b></th>
                                    <th><b>Ecouter</b></th>
                                </tr> 
                                <c:set var="total" value="0"/>  
                                <c:forEach var="u" items="${listeDesMusiques}" varStatus="status">  
                                    <tr class="${status.index%2==0 ? 'alt' : ''}">  
                                        <td><div class="arrow"></div></td>
                                        <td>${u.artiste.nom}</td>  
                                        <td>${u.titre}</td>  
                                        <td><a href="http://fr.wikipedia.org/wiki/${u.artiste.nom}" target=_blank>Lien</a></td>
                                         <td><a href="http://www.perdu.com"><img src="play.png" width="25" height="25" title="ecouter"/> </a>></td>

                                    </tr>  
                                    <tr>
                                        <td colspan="6">
                                            <c:forEach var="p" items="${u.pistes}">
                                                ${p.nom} <br>
                                            </c:forEach>
                                        </td>
                                    </tr>
                                </c:forEach>   
                            </tbody>
                        </table>  
                    </form>
                </fieldset>
            </c:if> 
        </div>
    </body>
    <script src="js/jExpand.js"></script>
</html>