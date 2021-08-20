package by.it_academy.jd2.Mk_JD2_82_21_person.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class CookieService {

    public static String getValueFromCookie(HttpServletRequest req, String key) {
        String val = req.getParameter(key);

        if (val == null) {
            Cookie[] cookies = req.getCookies();

            if (cookies != null) {
                val = Arrays.stream(cookies)
                        .filter(x -> key.equalsIgnoreCase(x.getName()))
                        .map(Cookie::getValue)
                        .findFirst()
                        .orElse(null);
            }
        }

        if (val == null || val.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("Запрос не отработан! Не передан один из обязательных параметров");
        }
        return val;
    }

    public static void saveCookie(HttpServletResponse resp, String key, String val) {
        Cookie myCookie = new Cookie(key, URLEncoder.encode(val, StandardCharsets.UTF_8));
        resp.addCookie(myCookie);
    }
}
