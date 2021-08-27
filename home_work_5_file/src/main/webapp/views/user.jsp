<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Список всех польэователей</title>
    </head>
    <body>
        <h1>Список всех пользователей</h1>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/views/message.jsp';" value="Написать сообщение">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/';" value="Назад"><br><br>

        <hr>

        <p>Пользователи:</p>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <c:forEach items="${set}" var="item" >
            ${"Логин: "}
            ${ item.getLogin() }
            ${"; Пользователь - "}
            ${ item.getFirstName() }
            ${ item.getLastName() }
            ${"; Дата рождения - "}
            ${ item.getAge() }<br>
        </c:forEach>

    </body>
</html>