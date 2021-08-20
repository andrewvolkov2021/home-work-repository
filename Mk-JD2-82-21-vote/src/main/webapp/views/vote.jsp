<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Квиз</title>
    </head>
    <body>
        <form action="/Mk-JD2-82-21-vote-0.0.0-SNAPSHOT/" method="POST">
            <label for="artist">Группа</label>
            <select name="artist">
            <option value="1">Ирина Аллегрова</option>
            <option value="2">Каста</option>
            <option value="3">Луна</option>
            <option value="4">Иванушки</option>
            </select><br/>

            <label for="genre">Жанр</label><br/>
            <input type="checkbox" name="genre" value="1"/> Рок <br/>
            <input type="checkbox" name="genre" value="2"/> Поп <br/>
            <input type="checkbox" name="genre" value="3"/> Фолк <br/>
            <input type="checkbox" name="genre" value="4"/> Альт <br/>
            <input type="checkbox" name="genre" value="5"/> Клкассика <br/>
            <input type="checkbox" name="genre" value="6"/> Джаз <br/>
            <input type="checkbox" name="genre" value="7"/> Блюз <br/>

            <label for="about">О себе</label><br/>
            <textarea name="about"></textarea>
            <input type="submit" name="Отправить">
        </form>
    </body>
</html>