/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.fwrp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fwrp.model.factory.*;
import com.fwrp.model.*;
import com.fwrp.dao.*;

/**
 *
 * @author mrinm
 */
public class FWRPServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String messageType = request.getMethod();
        String uri = request.getRequestURI();
        System.out.println(uri);
        if (uri.contains("retailerlogin")) {
            if (messageType.equals("GET")) {
                request.getRequestDispatcher("/WEB-INF/retailerlogin.jsp").forward(request, response);
            } else {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                Retailer retail = RetailerFactory.create(username, password, name);
                
                if (!RetailerDaoImpl.getInstance().check(username)) {
                    RetailerDaoImpl.getInstance().enList(retail);
                    response.sendRedirect("/WEB-INF/retailerlogin.jsp");
                }  else {
                    response.sendRedirect("/WEB-INF/retailerlogin.jsp?error=True");
                }
            }
        } else if (uri.contains("charitylogin")) {
            request.getRequestDispatcher("/WEB-INF/charitylogin.jsp").forward(request, response);
        } else if (uri.contains("consumerlogin")) {
            request.getRequestDispatcher("/WEB-INF/consumerlogin.jsp").forward(request, response);
        }
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
