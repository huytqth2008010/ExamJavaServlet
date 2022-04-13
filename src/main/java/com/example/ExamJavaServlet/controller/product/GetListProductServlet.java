package com.example.ExamJavaServlet.controller.product;

import com.example.ExamJavaServlet.entity.tbProducts;
import com.example.ExamJavaServlet.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetListProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductModel model = new ProductModel();
        List<tbProducts> listObj = model.findAll();
        req.setAttribute("listObj",listObj);
        req.getRequestDispatcher("/products/ListProduct.jsp").forward(req,resp);
    }
}
