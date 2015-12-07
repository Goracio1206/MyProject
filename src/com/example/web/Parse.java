package com.example.web;


import com.example.model.StringParser;
import com.example.model.WriteJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Admin on 12/7/2015.
 */
@WebServlet(name = "Parse")
public class Parse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        StringParser str = new StringParser();
        PrintWriter out = response.getWriter();
        out.println("Hi");
        out.println("Hi");
        String path = request.getParameter("fullPath");
        out.print(path);
        str.parse(path);
        WriteJSON write = new WriteJSON();
        write.addData(str.getPath(),str.getLimit(),str.getQ(),str.getLength(),str.isMetaData());
        out.print(write.result.toJSONString());
        out.print("bla");


//
//        RequestDispatcher view = request.getRequestDispatcher("result.jsp");
//        view.forward(request, response);
    }
}
