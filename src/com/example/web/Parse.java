package com.example.web;


import com.example.model.StringParser;
import com.example.model.WriteJSON;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "Parse")
public class Parse extends HttpServlet {
    private WriteJSON write = new WriteJSON();
    private StringParser str = new StringParser();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        String test = request.getParameter("ParseSelect");
        if (test.equals("fullPath1")) {
            String path = request.getParameter("fullPath");
            str.parse(path);
            write.addData(str.getPath(),str.getLimit(),str.getQ(),str.getLength(),str.isMetaData());
            out.write(write.result.toJSONString());
            out.flush();
            out.close();
        } if(test.equals("selectAttrib")) {
            String path = request.getParameter("path");
            int limit = Integer.parseInt(request.getParameter("limit"));
            String q = request.getParameter("q");
            int length = Integer.parseInt(request.getParameter("length"));
            Boolean metaData = request.getParameter("metaDate").contains("true");
            write.addData(path, limit, q, length, metaData);
            out.write(write.result.toJSONString());
            out.print(metaData);
            out.print("To Be Continued...");
            out.flush();
            out.close();
        } else if (test.equals("")) {
        out.print("You are Stupid!");
    }
      
        //JSONObject result = write.getResult();
        //String exit = result.toString();
       // out.print(result);





//
//        RequestDispatcher view = request.getRequestDispatcher("result.jsp");
//        view.forward(request, response);
    }
}
