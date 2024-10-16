/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Cart.ItemsCart;
import Items.ItemsDAO;
import Items.ItemsDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "RemoveFromCartServlet", urlPatterns = {"/RemoveFromCartServlet"})
public class RemoveFromCartServlet extends HttpServlet {

    private final String HOME_PAGE = "home.jsp";

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
         String itemId = request.getParameter("itemId"); // Assuming item ID is passed as a parameter
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                ItemsCart cart = (ItemsCart) session.getAttribute("CART");

                if (cart != null && itemId != null && !itemId.trim().isEmpty()) {
                    // Create a temporary ItemsDTO to use for comparison
                    ItemsDTO itemToRemove = new ItemsDTO();
                    itemToRemove.setItemId(Integer.parseInt(itemId.trim())); // Set the ID of the item to remove

                    // Remove the item from the cart
                    cart.removeItemFromCart(itemToRemove); // Use the method from ItemsCart
                    session.setAttribute("CART", cart); // Update the session with the modified cart
                }
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(RemoveFromCartServlet.class.getName()).log(Level.SEVERE, "Invalid item ID format: " + itemId, e);
        } catch (Exception ex) {
            Logger.getLogger(RemoveFromCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            String urlRewriting = "DispatchServlet?btAction=View your cart";
            response.sendRedirect(urlRewriting);
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
