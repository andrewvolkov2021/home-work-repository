<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Карточка сотрудника</title>
    </head>
    <body>
        <h1>Карточка сотрудника</h1>

        <table border = '1'>
            <capitation>Каротчка сотрудника</capitation>
            <tr>
                <th>Id сотрудника</th>
                <th>Имя сотрудника</th>
                <th>Зарплата</th>
                <th>Отдел</th>
                <th>Должность</th>
            </tr>
            <tr>
                <td align="center">${employee.getId()}</td>
                <td align="center">${employee.getName()}</td>
                <td align="center">${employee.getSalary()}</td>
                <td align="center">${employee.getDepartment().getName()}</td>
                <td align="center">${employee.getPosition().getName()}</td>
            </tr>
        </table>
        <br><br>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/';" value="Назад">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employeesPage';" value="Назад к списку">
    </body>
</html>