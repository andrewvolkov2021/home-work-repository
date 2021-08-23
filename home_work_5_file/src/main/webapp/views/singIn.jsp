<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Мессенджер: Вход</title>
    </head>
    <body>
        <form action="/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/singInCheck" method="GET">
            <h1>Вход в Мессенджер</h1>
            <p>Для входа необходимо указать логин и пароль</p>
            <p>Логин:</p><input name="login" type="text" placeholder="Введите логин" size="50">
            <p>Пароль:</p><input name="password" type="password" placeholder="Введите пароль" size="50">

            <br><br>

            <input type="submit" value="OK"/>
            <input type="button" onclick="location.href='/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/';" value="Назад">
        </form>
    </body>
</html>