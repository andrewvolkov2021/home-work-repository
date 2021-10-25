<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Карточка сотрудника по ID</title>
    </head>
    <body>
        <form action="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee/card" method="GET">
            <h2>Получение карточки сотрудника по ID</h2>
            <p>Для получения карточки сотрудника укажите номер ID</p>

            <p>ID:</p><input name="id" type="number" placeholder="Введите ID сотрудника" size="50">

            <br><br><br>

            <input type="submit" value="Получить"/>
            <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/';" value="Назад">
        </form>
    </body>
</html>