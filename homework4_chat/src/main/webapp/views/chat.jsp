<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Мессенджер: Мои сообщения</title>
    </head>
    <body>
        <h1>Мои сообщения</h1>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/views/message.jsp';" value="Написать сообщение">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/';" value="Назад"><br><br>
        <input type="button" onclick='location.href="http://localhost:8080/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/chat"' value="Обновить мои сообщения">

        <p>Сообщения:</p>
        <br/>

        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <c:forEach items="${messages}" var="item" >
            ${item.getSender().getLogin()}
            ${" - "}
            ${item.getDate().toString()}<br>
            ${item.getText()}<br><br>
        </c:forEach>

    </body>
</html>