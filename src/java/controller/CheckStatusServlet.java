/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Order.OrderDAO;
import Order.OrderDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CheckStatusServlet", urlPatterns = {"/CheckStatusServlet"})
public class CheckStatusServlet extends HttpServlet {
    private final String ORDERSTATUS_PAGE = "orderStatus.jsp";

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
        response.setContentType("text/html;charset=UTF-8");
        String orderId = request.getParameter("txtOrderId");
        String url = ORDERSTATUS_PAGE;

        if (orderId == null || orderId.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Please enter an order ID.");
        } else {
            try {
                OrderDAO dao = new OrderDAO();
                OrderDTO result = dao.CheckStatus(orderId);

                if (result != null) {
                    request.setAttribute("ORDER", result);
                } else {
                    request.setAttribute("errorMessage", "Order not found.");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CheckStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("errorMessage", "Error connecting to the database.");
            } catch (SQLException ex) {
                Logger.getLogger(CheckStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("errorMessage", "SQL error occurred.");
            }
        }

        // Retain the order ID input value
        request.setAttribute("txtOrderId", orderId);

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
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
