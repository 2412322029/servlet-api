package com.example.demo.api;



import com.example.demo.conf;
import com.example.demo.db.data;
import com.example.demo.db.datadao;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(value = "/getlast", name = "getlast")
public class lastservlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", conf.CORS);

            datadao dataDao = new datadao();
            String r=dataDao.getlastdate();
            resp.setContentType("application/json;charset=utf8");
            resp.getWriter().write("{\"time\":\""+r+"\"}");



    }
}







