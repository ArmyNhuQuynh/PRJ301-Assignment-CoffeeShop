/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
     private final String LOGIN_PAGE = "Login.jsp";
     private final String HOME_PAGE = "home.jsp";
     private final String VIEW_YOUR_CART = "checkOut.jsp";
     private final String LOGIN_CONTROLLER = "LoginServlet";
     private final String SHOWALLITEMS_CONTROLLER = "ShowAllItemsServlet";
     private final String SEARCH_CONTROLLER = "SearchServlet";
     private final String ADDTOCART_CONTROLLER = "AddToCartServlet";
     private final String REMOVEFROMCART_CONTROLLER = "RemoveFromCartServlet";
     private final String UPDATECART_CONTROLLER = "UpdateCartServlet";
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
        PrintWriter out = response.getWriter();
        String button = request.getParameter("btAction");
        String url = HOME_PAGE;
        try {
           if(button==null){
              url = SHOWALLITEMS_CONTROLLER; 
           }else if(button.equals("Login")){
               url = LOGIN_CONTROLLER;
           }else if(button.equals("Search")){
               url = SEARCH_CONTROLLER;
           }else if(button.equals("Add")){
               url = ADDTOCART_CONTROLLER;
           }else if(button.equals("Remove item")){
               url = REMOVEFROMCART_CONTROLLER;
           }else if(button.equals("View your cart")){
               url = VIEW_YOUR_CART;
           }else if(button.equals("Update quantity")){
               url = UPDATECART_CONTROLLER;
           }
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
