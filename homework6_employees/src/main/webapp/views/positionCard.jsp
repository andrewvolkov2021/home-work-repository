<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Карточка должности</title>
    </head>
    <body>
        <h1>Карточка должности</h1>

        <table border = '1'>
            <capitation>Каротчка должности</capitation>
            <tr>
                <th>Id должности</th>
                <th>Название должности</th>
            </tr>
            <tr>
                <td align="center">${position.getId()}</td>
                <td align="center">${position.getName()}</td>
            </tr>
        </table>
        <br><br>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/';" value="Назад">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/listOfPositions';" value="Назад к списку">
    </body>
</html>