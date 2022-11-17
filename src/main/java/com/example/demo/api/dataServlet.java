package com.example.demo.api;

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

@WebServlet("/data")
public class dataServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        datadao blogDao = new datadao();
        List<data> blogs = blogDao.selectAll();
        //转成 JSON 格式
        String respJson = objectMapper.writeValueAsString(blogs);

        resp.setHeader("Access-Control-Allow-Origin", "*");;

        resp.setContentType("application/json;charset=utf8");
        resp.getWriter().write(respJson);
    }
}





