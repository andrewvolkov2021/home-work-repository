package by.it_academy.jd2.Mk_JD2_82_21_chat.service.api;

import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.User;

import javax.servlet.http.HttpServletRequest;

public interface IHandleSession {
    void save(HttpServletRequest req, User user);
}
