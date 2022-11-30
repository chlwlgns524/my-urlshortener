package com.myshortener.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(urlPatterns = "/pages/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.info("{}", getClass().getCanonicalName());

        Cookie memberCookie = WelcomeServlet.getMemberCookie(req).get();
        memberCookie.setMaxAge(0);
        resp.addCookie(memberCookie);
        resp.sendRedirect("/pages");
    }

}
