<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Добавление сотрудников</title>
    </head>
    <body>
    <form action="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/addEmployee" method="POST">
        <h1>Добавление сотрудника в базу данных</h1>
        <p>Для добавления сотрудника необходимо указать имя и зарплату</p>
            <p>Имя:</p><input name="name" type="text" placeholder="Введите имя сотрудника" size="50">
            <p>Зарплата:</p><input name="salary" type="text" placeholder="Укажите зарплату сотрудника" size="50">

        <br>
        <p style = "color:Red">Внимание!!! Не был указан один из обязательных параметров</p>
        <br>

         <input type="submit" value="Добавить"/>
    </form>
</html>