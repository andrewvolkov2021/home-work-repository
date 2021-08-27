<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>О приложении</title>
    </head>
    <body>
        <h1>Информация о приложении</h1>
       <input type="button" onclick="location.href='/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/views/message.jsp';" value="Написать сообщение">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/';" value="Назад"><br><br>

        <hr>

        <p>Приложение: Мессенджер</p>
        <p>Версия: 1.3</p><br/>

        ${"Время запуска приложения: "}
        ${time}<br>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <c:if test="${typeOfStorage.equalsIgnoreCase('file')}">
            <p>Способ хранения данных: в файле</p>
        </c:if>
        <c:if test="${typeOfStorage.equalsIgnoreCase('memory_of_app')}">
                <p>Способ хранения данных: в памяти приложения</p>
        </c:if>

    </body>
</html>