/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.user.UserDAO;


@WebServlet(name = "LoginServlet")

public class LoginServlet extends HttpServlet {
    private static final String ERROR="error.html";
    private static final String SUCCESS="search.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR;
        try {
            String userID=request.getParameter("userID");
            String password=request.getParameter("password");
            UserDAO dao= new UserDAO();
            boolean checkLogin= dao.checkLogin(userID,password);
            if(checkLogin){
                url=SUCCESS;
            }
        }catch(Exception e){
            log("Error at LoginServlet"+e.toString());
        }finally{
            response.sendRedirect(url);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
