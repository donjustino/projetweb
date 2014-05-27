<%--<jsp:include page="template.jsp">  
    <jsp:param name="content" value="main"/>
</jsp:include> --%>

<%
    String redirectURL = "login.jsp";
    response.sendRedirect(redirectURL);
%>