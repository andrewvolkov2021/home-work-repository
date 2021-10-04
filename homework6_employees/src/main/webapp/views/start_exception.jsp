<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Добавление сотрудников</title>
    </head>
    <body>

        <h1>База данных Employees</h1>
        <h2>Добавление сотрудника в базу данных</h2>
        <form action="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?paramNewEmployee=${'newEmployee'}" method="POST">
            <p>Для добавления сотрудника в базу данных необходимо указать обязательные параметры: имя и зарплату</p>
            <p>Имя:</p><input name="name" type="text" placeholder="Введите имя сотрудника" size="50">
            <p>Зарплата:</p><input name="salary" type="number" " min="0" max="99999999.99" step="0.01" placeholder="Зарплата" size="50">

            <br>
            <p style = "color:Red">Внимание!!! Не был указан один из обязательных параметров</p>

            <input type="submit" value="Добавить"/>
            <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?getId=${'getId'}';" value="Карточка сотрудника">
        </form>

        <hr>

        <h2>Автомантическое заполнение базы данных</h2>
        <p>Для автоматического заполнения базы данных необходимоуказать количество сотрудников</p>
        <form action="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/autoFill" method="POST">
            <p>Количество сотрудников:</p><input name="count" type="number" placeholder="Количество" size="50">
            <br><br>
            <input type="submit" value="Добавить сотрудников"/>
        </form>

        <hr>
        <p>Получить из базы список отделов/должностей/сотрудников </p>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/department';" value="Список отделов">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/position';" value="Список должностей">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?paramEmployee=${'newPage'}';" value="Список сотрудников">

    </body>
</html>