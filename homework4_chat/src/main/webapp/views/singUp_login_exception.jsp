<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Мессенджер: Регистрация</title>
    </head>
    <body>
        <form action="/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/singUpCheck" method="POST">
            <h1>Регистрация</h1>
            <p>Для регистрации необходимо заполнить следующие поля</p>
            <p>Логин:</p><input name="login" type="text" placeholder="Введите логин" size="50">
            <p>Пароль:</p><input name="password" type="password" placeholder="Введите пароль" size="50">

            <br><br>

            <p>Имя:</p><input name="firstName" type="text" placeholder="Введите имя" size="50">
            <p>Фамилия:</p><input name="lastName" type="text" placeholder="Введите фамилию" size="50">
            <p>Дата рождения:</p><input name="date" type="date">

            <br><br>
            <p><b>ВНИМАНИЕ!!! Уже существует пользователь с таким логином!</b></p>
            <br>

            <input type="submit" value="OK" />
            <input type="button" onclick="location.href='/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/';" value="Назад">
        </form>
    </body>
</html>