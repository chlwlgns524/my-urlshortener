package com.myshortener.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(urlPatterns = "/pages/signup-form")
public class ToSignupFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.info("{}", getClass().getCanonicalName());

        req.getRequestDispatcher("/WEB-INF/views/signup-form.jsp")
            .forward(req, resp);
    }

}
