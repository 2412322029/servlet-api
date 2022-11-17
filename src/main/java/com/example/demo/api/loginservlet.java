package com.example.demo.api;


import com.example.demo.db.User;
import com.example.demo.db.UserDao;
import com.fasterxml.jackson.databind.ObjectMapper;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/login")
public class loginservlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");
        //获取到请求中的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username="+username+",password="+password);
        if(username == null || "".equals(username) || password == null || "".equals(password)){

            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("当前的用户名或密码为空!!");
            return;
        }

        UserDao userDao = new UserDao();
        User user = userDao.selectByName(username);
        if(user == null || !user.getPassword().equals(password)){

            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("当前的用户名或密码错误!!");
            return;
        }

        HttpSession session = req.getSession(true);
        //存储到会话中
        session.setAttribute("user",user);

        //重定向
        resp.sendRedirect("blog_list.html");
    }
}

