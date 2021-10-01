<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Фильтр сотрудников</title>
    </head>
    <body>
        <h2>Список сотрудников (фильтр)</h2>
        <h3>Параметры фильтрации</h3>
        <%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <p>Имя сотрудников: ${searchEmployee.getName()}</p>

        <p>MIN зарплата: <fmt:formatNumber type="number" value= "${searchEmployee.getMinSalary()}"/></p>

         <p>MAX зарплата: <fmt:formatNumber type="number" value= "${searchEmployee.getMaxSalary()}"/></p>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <ol start = "${startPosition}">
            <c:forEach items="${listOfEmployees}" var="item" >
                <li><a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?id=${item.getId()}">${item.getName()}<a/></li>
            </c:forEach>
        </ol>

        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/sorting?paramEmployee=${'newPage'}&searchName=${searchEmployee.getName()}&minSearchSalary=${searchEmployee.getMinSalary()}&maxSearchSalary=${searchEmployee.getMaxSalary()}"> ${" 1 "} <a/>
        ${" < "}
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/sorting?size=20&page=${pages[0]}&paramEmployee=${'newPage'}&searchName=${searchEmployee.getName()}&minSearchSalary=${searchEmployee.getMinSalary()}&maxSearchSalary=${searchEmployee.getMaxSalary()}">
            <c:if test="${pages[0] != 0}">
                ${ pages[0] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/sorting?size=20&page=${pages[1]}&paramEmployee=${'newPage'}&searchName=${searchEmployee.getName()}&minSearchSalary=${searchEmployee.getMinSalary()}&maxSearchSalary=${searchEmployee.getMaxSalary()}">
            <c:if test="${pages[1] != 0}">
                ${ pages[1] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/sorting?size=20&page=${pages[2]}&paramEmployee=${'newPage'}&searchName=${searchEmployee.getName()}&minSearchSalary=${searchEmployee.getMinSalary()}&maxSearchSalary=${searchEmployee.getMaxSalary()}">
            <c:if test="${pages[2] != 0}">
                ${ pages[2] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/sorting?size=20&page=${pages[3]}&paramEmployee=${'newPage'}&searchName=${searchEmployee.getName()}&minSearchSalary=${searchEmployee.getMinSalary()}&maxSearchSalary=${searchEmployee.getMaxSalary()}">
            <c:if test="${pages[3] != 0}">
                ${ pages[3] }
            </c:if>
        <a/>

        <a style = "color:red" href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/sorting?size=20&page=${pages[4]}&paramEmployee=${'newPage'}&searchName=${searchEmployee.getName()}&minSearchSalary=${searchEmployee.getMinSalary()}&maxSearchSalary=${searchEmployee.getMaxSalary()}">
        <b> ${pages[4]} </b><a/>

        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/sorting?size=20&page=${pages[5]}&paramEmployee=${'newPage'}&searchName=${searchEmployee.getName()}&minSearchSalary=${searchEmployee.getMinSalary()}&maxSearchSalary=${searchEmployee.getMaxSalary()}">
            <c:if test="${pages[5] != 0}">
                ${ pages[5] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/sorting?size=20&page=${pages[6]}&paramEmployee=${'newPage'}&searchName=${searchEmployee.getName()}&minSearchSalary=${searchEmployee.getMinSalary()}&maxSearchSalary=${searchEmployee.getMaxSalary()}">
            <c:if test="${pages[6] != 0}">
                ${ pages[6] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/sorting?size=20&page=${pages[7]}&paramEmployee=${'newPage'}&searchName=${searchEmployee.getName()}&minSearchSalary=${searchEmployee.getMinSalary()}&maxSearchSalary=${searchEmployee.getMaxSalary()}">
            <c:if test="${pages[7] != 0}">
                ${ pages[7] }
            </c:if>
        <a/>
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/sorting?size=20&page=${pages[8]}&paramEmployee=${'newPage'}&searchName=${searchEmployee.getName()}&minSearchSalary=${searchEmployee.getMinSalary()}&maxSearchSalary=${searchEmployee.getMaxSalary()}">
            <c:if test="${pages[8] != 0}">
                ${ pages[8] }
            </c:if>
        <a/>
        ${" > "}
        <a href="/Mk-JD2-82-21-employees-1.0-SNAPSHOT/sorting?size=20&page=${countOfPages}&searchName=${searchEmployee.getName()}&minSearchSalary=${searchEmployee.getMinSalary()}&maxSearchSalary=${searchEmployee.getMaxSalary()}">
        ${countOfPages} <a/>

        <br><br>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/';" value="Назад">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?paramEmployee=${'newPage'}';" value="Сбросить">

        </body>
</html>