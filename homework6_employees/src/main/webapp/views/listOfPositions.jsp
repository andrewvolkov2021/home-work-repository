<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Список профессий</title>
    </head>
    <body>
        <h1>Список профессий</h1>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <ol>
        <c:forEach items="${listOfPositions}" var="item" >
        <li><a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/position/${item.getId()}">${item.getName()}<a/></li>
        </c:forEach>
        </ol>

        <br>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/';" value="Назад">
        </body>
</html>