package com.myshortener.controller;

import com.myshortener.controller.dto.MemberDto;
import com.myshortener.service.MemberService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@WebServlet(urlPatterns = "/pages/signup")
public class SignupServlet extends HttpServlet {

    private final MemberService memberService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.info("{}", getClass().getCanonicalName());

        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        // validation logic
        try {
            memberService.createNewMember(
                MemberDto.builder()
                    .email(email)
                    .name(name)
                    .password(password)
                    .build());

            resp.sendRedirect("/pages");
        } catch (Exception e) {
            log.info("{}", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/signup-form.jsp")
                .forward(req, resp);
        }
    }

}
