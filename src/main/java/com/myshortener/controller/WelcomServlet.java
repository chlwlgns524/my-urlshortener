package com.myshortener.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pages")
public class WelcomServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        System.out.println(this.getClass().getCanonicalName());

        request.getRequestDispatcher("/WEB-INF/index.jsp")
            .forward(request, response);
    }
}
