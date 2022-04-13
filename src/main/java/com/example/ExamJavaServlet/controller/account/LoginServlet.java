package com.example.ExamJavaServlet.controller.account;

import com.example.ExamJavaServlet.entity.tbUser;
import com.example.ExamJavaServlet.model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel model = new UserModel();
        String username = req.getParameter("username");
        String password =req.getParameter("password");
        if (username==null){
            req.setAttribute("errorsLog","Invalid username");
            req.getRequestDispatcher("/user/login.jsp").forward(req,resp);
            return;
        }
        tbUser user= model.findAccountByUsername(username);
        if (user == null){
            req.setAttribute("errorsLog","Not found username");
            req.getRequestDispatcher("/user/login.jsp").forward(req,resp);
            return;

        }
        if (!user.getPassword().contains(password)){
            req.setAttribute("errorsLog","Invalid information");
            req.getRequestDispatcher("/user/login.jsp").forward(req,resp);
            return;
        }
        req.getSession().setAttribute("currentUser",user);
        resp.sendRedirect("/products");
    }
}
