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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/insertdata", name = "insertdata")
public class insertdataservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", conf.CORS);
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        if(Isok.isover(content,1) && Isok.isbetween(title,1,100)){
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);


            data data=new data();
            data.setTitle(title);
            data.setContent(content);
            data.setUserId(user.getUserId());


            datadao datadao=new datadao();
            String i=datadao.insert(data,user);
            resp.setContentType("application/json;charset=utf8");
            resp.getWriter().write(i);
        }else {
            resp.setContentType("application/json;charset=utf8");
            resp.sendError(400);
        }

    }
}
