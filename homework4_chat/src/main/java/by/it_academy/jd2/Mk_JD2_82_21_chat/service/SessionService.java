package by.it_academy.jd2.Mk_JD2_82_21_chat.service;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.api.IHandleSession;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionService implements IHandleSession {
    private static final SessionService instance = new SessionService();

    private static final String USER_ATTRIBUTE_NAME = "user";

    private SessionService() {
    };

    @Override
    public void save(HttpServletRequest req, User user) {
        HttpSession session = req.getSession();
        session.setAttribute(USER_ATTRIBUTE_NAME, user);
    }

    public static SessionService getInstance() {
        return instance;
    }
}
