<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Список сотрудников</title>
    </head>
    <body>
        <h1>Список сотрудников</h1>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <ol>
        <c:forEach items="${listOfEmployees}" var="item" >
        <li><a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/newEmployeeCard?id=${item.getId()}">${item.getName()}<a/></li>
        </c:forEach>
        </ol>

        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employeesPage"> ${" 1 "} <a/>
        ${" < "}
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employeesPage?size=20&page=${pages[0]}">
            <c:if test="${pages[0] != 0}">
                ${ pages[0] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employeesPage?size=20&page=${pages[1]}">
            <c:if test="${pages[1] != 0}">
                ${ pages[1] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employeesPage?size=20&page=${pages[2]}">
            <c:if test="${pages[2] != 0}">
                ${ pages[2] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employeesPage?size=20&page=${pages[3]}">
            <c:if test="${pages[3] != 0}">
                ${ pages[3] }
            </c:if>
        <a/>
        <a style = "color:red" href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employeesPage?size=20&page=${pages[4]}">
        <b> ${pages[4]} </b><a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employeesPage?size=20&page=${pages[5]}">
            <c:if test="${pages[5] != 0}">
                ${ pages[5] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employeesPage?size=20&page=${pages[6]}">
            <c:if test="${pages[6] != 0}">
                ${ pages[6] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employeesPage?size=20&page=${pages[7]}">
            <c:if test="${pages[7] != 0}">
                ${ pages[7] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employeesPage?size=20&page=${pages[8]}">
            <c:if test="${pages[8] != 0}">
                ${ pages[8] }
            </c:if>
        <a/>
        ${" > "}
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employeesPage?size=20&page=${countOfPages}"> ${countOfPages} <a/>

        <br><br>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/';" value="Назад">
        </body>
</html>