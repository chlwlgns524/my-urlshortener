package com.myshortener.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(urlPatterns = "/pages")
public class WelcomeServlet extends HttpServlet {

    public static final String COOKIE_NAME = "memberId";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        log.info(this.getClass().getCanonicalName());

        Optional<Cookie> loginMember = getMemberCookie(request);
        if (loginMember.isPresent())
            request.getRequestDispatcher("/WEB-INF/member-home.jsp")
                .forward(request, response);
        else
            request.getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(request, response);
    }

    public static Optional<Cookie> getMemberCookie(HttpServletRequest request) {
        if (Objects.isNull(request.getCookies()))
            return Optional.empty();
        return Arrays.stream(request.getCookies())
            .filter(cookie -> cookie.getName().equals(COOKIE_NAME))
            .findFirst();
    }

}
