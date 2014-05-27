<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<html>  
    <head>  
        <title>${param.title}</title>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css"  
              href="${pageContext.request.contextPath}/style.css" />  
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    </head>  
    <body>  
        <c:if test="${connecte}">
        <jsp:include page="header.jsp"/>  

        <jsp:include page="menu.jsp"/> 
        
        <h1>${param.title}</h1>  
        
        <jsp:include page="${param.content}.jsp"/> 

        </c:if>
        
        <c:if test="${!connecte}">
            <a href="login.jsp">Veuillez vous connecter</a>
        </c:if>

    </body>  
</html> 
