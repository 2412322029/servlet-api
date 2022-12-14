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

@WebServlet(value = "/getalldetail",name = "getalldetail")
public class dataServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        datadao dataDao = new datadao();
        List<data> datas = dataDao.selectAll();
        //转成 JSON 格式
        String respJson = objectMapper.writeValueAsString(datas);

        resp.setHeader("Access-Control-Allow-Origin", conf.CORS);;

        resp.setContentType("application/json;charset=utf8");
        resp.getWriter().write(respJson);
    }
}







