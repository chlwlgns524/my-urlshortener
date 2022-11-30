package com.myshortener.controller;

import static com.myshortener.controller.WelcomeServlet.COOKIE_NAME;

import com.myshortener.controller.dto.MemberDto;
import com.myshortener.service.MemberService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@WebServlet(urlPatterns = "/pages/login")
public class LoginServlet extends HttpServlet {

    private final MemberService memberService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.info("{}",getClass().getCanonicalName());

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            MemberDto memberDto = memberService.login(email, password);
            resp.addCookie(new Cookie(COOKIE_NAME, String.valueOf(memberDto.getId())));
            resp.sendRedirect("/pages");
        } catch (Exception e) {
            log.info("{}", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/login-form.jsp")
                .forward(req, resp);
        }
    }

}
