package by.it_academy.jd2.Mk_JD2_82_21_person.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionService {
    public static String getValueFromSession(HttpServletRequest req, String key) {
        String val = req.getParameter(key);

        if (val == null) {
            HttpSession session = req.getSession(false);

            if (!session.isNew()) {
                val = (String) session.getAttribute(key);
            }
        }

        if (val == null || val.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("Запрос не отработан! Не передан один из обязательных параметров");
        }
        return val;
    }

    public static void saveSession(HttpServletRequest req, String key, String val) {
        HttpSession session = req.getSession();
        session.setAttribute(key, val);
    }
}
