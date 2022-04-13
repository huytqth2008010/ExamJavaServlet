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
    private UserModel accountModel = new UserModel();

    private static final int MAX_COUNT = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        tbUser user = accountModel.findAccountByUsername(username);
        if (user == null){
//            resp.getWriter().println("Invalid Information");
            resp.sendRedirect("/login");
            return;
        }
        boolean result = checkPassword(password, user.getPassword());
        if (result){
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", user);
            resp.sendRedirect("/products");
        }else {
            resp.getWriter().println("Account is not valid!");
            resp.sendRedirect("/user/login");
        }
    }
    public static boolean checkPassword(String password, String passwordDatabase) {
        return password.equals(passwordDatabase);
    }
}
