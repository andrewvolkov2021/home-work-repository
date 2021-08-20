package by.it_academy.jd2.Mk_JD2_82_21_person.controller;

import by.it_academy.jd2.Mk_JD2_82_21_person.service.CookieService;
import by.it_academy.jd2.Mk_JD2_82_21_person.service.SessionService;
import by.it_academy.jd2.Mk_JD2_82_21_person.storage.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PersonServlet", urlPatterns = "/person")
public class PersonServlet extends HttpServlet {

    private static final String FIRST_NAME_PARAM_NAME = "firstName";
    private static final String LAST_NAME_PARAM_NAME = "lastName";
    private static final String AGE_PARAM_NAME = "age";
    private static final String NAME_HEADER_PARAM_NAME = "typeOfStorage";
    private static final String NAME_STORAGE_WITH_SESSIONS_PARAM_NAME = "session";
    private static final String NAME_STORAGE_WITH_COOKIES_PARAM_NAME = "cookie";

    private Person person;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        try {
            String typeOfStorageVal = req.getHeader(NAME_HEADER_PARAM_NAME);

            if (typeOfStorageVal.equalsIgnoreCase(NAME_STORAGE_WITH_SESSIONS_PARAM_NAME)) {
                String firstName = SessionService.getValueFromSession(req, FIRST_NAME_PARAM_NAME);
                SessionService.saveSession(req, FIRST_NAME_PARAM_NAME, firstName);

                String lastName = SessionService.getValueFromSession(req, LAST_NAME_PARAM_NAME);
                SessionService.saveSession(req, LAST_NAME_PARAM_NAME, lastName);

                String age = SessionService.getValueFromSession(req, AGE_PARAM_NAME);
                SessionService.saveSession(req, AGE_PARAM_NAME, age);

                person = new Person(firstName, lastName, age);
            }

            if (typeOfStorageVal.equalsIgnoreCase(NAME_STORAGE_WITH_COOKIES_PARAM_NAME)) {
                String firstName = CookieService.getValueFromCookie(req, FIRST_NAME_PARAM_NAME);
                CookieService.saveCookie(resp, FIRST_NAME_PARAM_NAME, firstName);

                String lastName = CookieService.getValueFromCookie(req, LAST_NAME_PARAM_NAME);
                CookieService.saveCookie(resp, LAST_NAME_PARAM_NAME, lastName);

                String age = CookieService.getValueFromCookie(req, AGE_PARAM_NAME);
                CookieService.saveCookie(resp, AGE_PARAM_NAME, age);

                person = new Person(firstName, lastName, age);
            }

            if (typeOfStorageVal.equalsIgnoreCase("")) {
                throw new IllegalArgumentException("Запрос не отработан! Не передан заголовок typeOfStorage");
            }

            writer.write("<p>Person: " + person.getLastName() + " " + person.getFirstName() + ", age = "
                    + person.getAge() + "</p>");

        } catch (IllegalArgumentException exception) {
            writer.write("<p>" + exception.getMessage() + "</p>");
        } catch (NullPointerException ex) {
            writer.write("<p>Запрос не отработан! Не передан заголовок typeOfStorage</p>");
        }
    }
}
