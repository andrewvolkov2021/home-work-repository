package by.it_academy.jd2.Mk_JD2_82_21_vote.controller;

public class AboutException extends Exception {
    public AboutException() {
        super("<h1> Внимание!!!</h1>" +
                "<p>Результаты голосования не учтены. Не была предоставлена информация о себе, что противоречит"
                + " правилам голосования. Пройдите голосование заново</p>");
    }
}
