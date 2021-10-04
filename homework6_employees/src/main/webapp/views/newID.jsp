<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>ID нового сотрудника</title>
    </head>
    <body>
        <h1>Уведомление</h1>
        <p>Новый сотрудник успешно добавлен в базу двнных</p>
        ${'ID нового сотрудника - '}
        ${id}

        <br><br>

        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/';" value="Назад">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?getId=${'getId'}';" value="Карточка сотрудника">
    </body>
</html>