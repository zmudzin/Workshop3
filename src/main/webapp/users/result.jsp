
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>

<table>
    <c:forEach items="<%= session.getAttribute(\"users\") %>" var="user">
        <tr>
            <td>Użytkownik: ${user} <a href="/delete=${""}">Delete</a> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
