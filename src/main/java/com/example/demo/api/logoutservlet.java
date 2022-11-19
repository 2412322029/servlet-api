package com.example.demo.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class logoutservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先找到当前会话
        resp.setContentType("application/json;charset=utf8");
        HttpSession session = req.getSession(false);
        if(session == null){
            resp.getWriter().write("未登录,无法注销");
            resp.sendError(401);
            return;
        }

        session.removeAttribute("user");
        System.out.println(session);
        resp.getWriter().write("已注销");
        return;
    }
}

