package com.example.demo.api;

import com.example.demo.conf;
import com.example.demo.db.User;
import com.example.demo.db.data;
import com.example.demo.db.datadao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/delete",name = "delete")
public class delervlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        datadao dataDao = new datadao();
        resp.setHeader("Access-Control-Allow-Origin", conf.CORS);;
        String id = req.getParameter("id");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        if (Isok.isStr2Num(id)){
            User user=new User();
            user.setPassword(password);
            user.setUsername(username);

            datadao datadao=new datadao();
            datadao.delete(Integer.parseInt(id),user);
            resp.setContentType("application/json;charset=utf8");
            resp.getWriter().write("删除成功");
        }else {
            resp.sendError(400);
        }

    }
}







