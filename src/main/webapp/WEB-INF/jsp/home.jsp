<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <link type="text/css" href="css/style.css">
</head>
<body>
<h1>Добро пожалова на страницу со статьями!</h1>



<c:forEach var="post" items="${posts}">
    <div id="post">
        <h3>${post.title}</h3>
        <p>${post.description}</p>
        <h6>Автор: ${post.author} <span><a href="${pageContext.request.contextPath}/do/post?id=${post.id}">читать далее...</a></span></h6>
    </div>
</c:forEach>

</body>
</html>
