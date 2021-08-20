package by.it_academy.jd2.Mk_JD2_82_21_vote.controller;

import by.it_academy.jd2.Mk_JD2_82_21_vote.service.VoteService;
import by.it_academy.jd2.Mk_JD2_82_21_vote.storage.VoteStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@WebServlet(name = "QuizServlet", urlPatterns = "/")
public class QuizServlet extends HttpServlet {

    private final VoteService service;

    public QuizServlet() {
        this.service = VoteService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/views/vote.jsp").forward(req, resp);

    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artist = req.getParameter("artist");
        String[] genres = req.getParameterValues("genre");
        String about = req.getParameter("about");

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        try {

            this.service.addVote(artist, genres, about);

            Map<String, Integer> artistResult = this.service.getArtistResult();
            Map<String, Integer> genreResult = this.service.getGenreResult();
            List<String> aboutResult = this.service.getAboutResult();


            writer.write("<h1>Текущие результаты голосования</h1>"
                    + "<h2>Номинация: лучший исполнитель</h2>");
            writer.write("<ol>");

            artistResult.entrySet()
                    .stream()
                    .sorted(new ArtistComparator())
                    .forEach(x -> {
                        switch (x.getKey()) {
                            case "1":
                                writer.write("<li>Ирина Аллегрова");
                                break;
                            case "2":
                                writer.write("<li>Каста");
                                break;
                            case "3":
                                writer.write("<li>Луна");
                                break;
                            case "4":
                                writer.write("<li>Иванушки");
                                break;
                        }
                        writer.write(" - " + x.getValue() + "</li>");
                    });
            writer.write("</ol>");

            writer.write("<hr>");
            writer.write("<h2>Номинация: лучший жанр</h2>");
            writer.write("<ol>");

            genreResult.entrySet()
                    .stream()
                    .sorted(new ArtistComparator())
                    .forEach(x -> {
                        switch (x.getKey()) {
                            case "1":
                                writer.write("<li>Рок");
                                break;
                            case "2":
                                writer.write("<li>Поп");
                                break;
                            case "3":
                                writer.write("<li>Фолк");
                                break;
                            case "4":
                                writer.write("<li>Альт");
                                break;
                            case "5":
                                writer.write("<li>Клкассика");
                                break;
                            case "6":
                                writer.write("<li>Джаз");
                                break;
                            case "7":
                                writer.write("<li>Блюз");
                                break;
                        }
                        writer.write(" - " + x.getValue() + "</li>");
                    });
            writer.write("</ol>");

            writer.write("<hr>");
            writer.write("<h2>Краткая информация о респондентах</h2>"
                    + "<ol>");
            aboutResult.stream()
                    .forEach(x -> writer.write("<li>" + x + "</li>"));
            writer.write("<ol>");

        } catch (GenresException ex) {
            writer.println(ex.getMessage());
        } catch (AboutException e){
            writer.println(e.getMessage());
        }
    }
}
