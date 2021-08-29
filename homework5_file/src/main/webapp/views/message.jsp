<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Мессенджер: Отправка сообщений</title>
    </head>
    <body>
        <form action="/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/messageCheck" method="POST">
            <h1>Отправка сообщений</h1>

            <input type="button" onclick='location.href="/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/user"' value="Все пользователи">
            <input type="button" onclick='location.href="/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/about"' value="Об приложении">

            <p>Укажите логин получателя сообщения</p>
            <p>Получатель:</p><input name="loginRecipient" type="text" placeholder="Введите логин" size="50">
            <br><br>

            <p>Напишите сообщение:</p>
             <p><textarea rows="10" cols="45" name="text"></textarea></p>

            <input type="submit" onclick='location.href="/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/views/message.jsp"' value="Отправить сообщение" /><br><br>
            <input type="button" onclick='location.href="/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/chat"' value="Получить сообщения">
            <input type="button" onclick="location.href='/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/';" value="Назад">


        </form>
    </body>
</html>