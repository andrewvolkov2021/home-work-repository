<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Список сотрудников</title>
    </head>
    <body>
        <h1>Список сотрудников</h1>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <ol start = "${startPosition}">
            <c:forEach items="${listOfEmployees}" var="item" >
                <li><a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee/${item.getId()}">${item.getName()}<a/></li>
            </c:forEach>
        </ol>

        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?paramEmployee=${'newPage'}"> ${" 1 "} <a/>
        ${" < "}
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?size=20&page=${pages[0]}&paramEmployee=${'newPage'}">
            <c:if test="${pages[0] != 0}">
                ${ pages[0] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?size=20&page=${pages[1]}&paramEmployee=${'newPage'}">
            <c:if test="${pages[1] != 0}">
                ${ pages[1] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?size=20&page=${pages[2]}&paramEmployee=${'newPage'}">
            <c:if test="${pages[2] != 0}">
                ${ pages[2] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?size=20&page=${pages[3]}&paramEmployee=${'newPage'}">
            <c:if test="${pages[3] != 0}">
                ${ pages[3] }
            </c:if>
        <a/>
        <a style = "color:red" href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?size=20&page=${pages[4]}&paramEmployee=${'newPage'}">
        <b> ${pages[4]} </b><a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?size=20&page=${pages[5]}&paramEmployee=${'newPage'}">
            <c:if test="${pages[5] != 0}">
                ${ pages[5] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?size=20&page=${pages[6]}&paramEmployee=${'newPage'}">
            <c:if test="${pages[6] != 0}">
                ${ pages[6] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?size=20&page=${pages[7]}&paramEmployee=${'newPage'}">
            <c:if test="${pages[7] != 0}">
                ${ pages[7] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?size=20&page=${pages[8]}&paramEmployee=${'newPage'}">
            <c:if test="${pages[8] != 0}">
                ${ pages[8] }
            </c:if>
        <a/>
        ${" > "}
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?size=20&page=${countOfPages}&paramEmployee=${'newPage'}"> ${countOfPages} <a/>

        <br><br>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/';" value="Назад">

        <hr>

        <h2>Поиск сотрудников</h2>
        <form action="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/sorting" method="GET">
            <p>Имя:<input name="searchName" type="text" placeholder="Введите имя сотрудника" size="50"></p>
            <p>Зарплата: от <input name="minSearchSalary" type="number" min="0" max="99999999.99" step="0.01" placeholder="min" size="50">
            до <input name="maxSearchSalary" type="number" min="0" max="99999999.99" step="0.01" placeholder="max" size="50">
            </p>
            <input type="submit" value="Отсортировать"/>
        </form>
        </body>
</html>