<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Карточка отдела</title>
    </head>
    <body>
        <h1>Карточка отдела</h1>

        <table border = '1'>
            <capitation>Каротчка отдела</capitation>
            <tr>
                <th>Id отдела</th>
                <th>Название отдела</th>
                <th>Название родительского отдела</th>
            </tr>
            <tr>
                <td align="center">${department.getId()}</td>
                <td align="center">${department.getName()}</td>
                <td align="center">${department.getParentalDepartment().getName()}</td>
            </tr>
        </table>
        <br><br>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/';" value="Назад">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/listOfDepartments';" value="Назад к списку">
    </body>
</html>