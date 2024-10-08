/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Cart.ItemsCart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class RemoveFromCartServlet extends HttpServlet {
    private final String HOME_PAGE="home.jsp";

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
        try { //1. cust goes to his carts place
            HttpSession session = request.getSession(false);
            if(session != null){
            //2. cust take his cart
                 ItemsCart cart = (ItemsCart)session.getAttribute("CART");
                 if(cart != null){
            //3. cust gets items
                     Map<String, Integer> items = cart.getItems();
                     if(items != null){
                   // 4. cust choose all remove items 
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if(selectedItems != null){
                             //5. cust remove all items 
                             for(String item : selectedItems){
                                 cart.removeItemFromCart(item);
                             }//each item is processed
                        }//user choose at lest one 
                       session.setAttribute("CART", cart);
                    }// items has existed
                }//cart has existed
            }//carts place has existed
        }finally {
            //6.RErefesh by calling the previous function again (view cart) using URL Rewritting
            String urlRewritting ="DispatchServlet"
                    + "?btAction=View your card";
            response.sendRedirect(urlRewritting);
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
