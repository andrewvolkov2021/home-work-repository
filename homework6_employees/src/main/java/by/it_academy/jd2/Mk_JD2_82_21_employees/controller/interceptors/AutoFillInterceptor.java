package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.interceptors;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.AutoFiller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutoFillInterceptor implements HandlerInterceptor {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AutoFiller autoFiller = mapper.readValue(request.getInputStream(), AutoFiller.class);
        int count = autoFiller.getCount();
        if (count == 0) {
            response.sendRedirect("autoFill_exception");
        }
        request.setAttribute("autoFiller", autoFiller);
        return true;
    }
}
