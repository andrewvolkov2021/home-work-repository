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

        <br><br><br><br>

        <input type="submit" value="Добавить"/>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/getCard';" value="Карточка пользователя">
    </form>

    <br><hr>

    <form action="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/autoFill" method="POST">
        <p>Автомантическое заполнение базы</p>
        <p>Количество сотрудников:</p><input name="count" type="number" placeholder="Введите количество сотрудников" size="50">

        <br>
        <p style = "color:Red">Внимание!!! Не было указанно количество сотрудников</p>
        <br>

        <input type="submit" value="Добавить сотрудников"/>
    </form>

        <br><hr>
        <p>Получить из базы список отделов/должностей/сотрудников </p>

        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/listOfDepartments';" value="Список отделов">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/listOfPositions';" value="Список должностей">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/listOfEmployees';" value="Список сотрудников">
        </body>
    </body>
</html>