
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>

<table>
<%--    <%HttpSession sess = request.getSession();--%>
<%--        User[] users= (User[]) sess.getAttribute("person");--%>
<%--        for (User u:users){--%>
<%--            response.getWriter().append(u.getUserName());--%>
<%--        }--%>
<%--    %>--%>
    <c:forEach items= "${person}" var="user">
        <tr>
            <td>${user}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
