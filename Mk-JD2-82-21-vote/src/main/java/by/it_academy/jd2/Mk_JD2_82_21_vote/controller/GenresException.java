package by.it_academy.jd2.Mk_JD2_82_21_vote.controller;

public class GenresException extends Exception {
    public GenresException(){
        super("<h1> Внимание!!!</h1>" +
                "<p>Результаты голосования не учтены. Было выбранно менее 3-х или более 5-и жанров, что противоречит"
                + " правилам голосования. Пройдите голосование заново</p>");
    }
}
