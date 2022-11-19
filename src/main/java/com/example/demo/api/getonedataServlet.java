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

@WebServlet(value = "/getonedetail", name = "getonedetail")
public class getonedataServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", conf.CORS);
        String id = req.getParameter("id");
        System.out.println(id);

        if (Isok.isStr2Num(id)) {
            datadao dataDao = new datadao();
            data datas = dataDao.selectOne(Integer.parseInt(id));
            //转成 JSON 格式
            String respJson = objectMapper.writeValueAsString(datas);
            resp.setContentType("application/json;charset=utf8");
            resp.getWriter().write(respJson);
        } else {
            resp.setContentType("application/json;charset=utf8");
            resp.sendError(400);
        }


    }
}







