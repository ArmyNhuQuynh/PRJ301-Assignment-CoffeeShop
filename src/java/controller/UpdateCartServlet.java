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
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/UpdateCartServlet"})
public class UpdateCartServlet extends HttpServlet {

    private final String VIEW_YOUR_CART = "checkOut.jsp";

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
        String itemId = request.getParameter("itemid");
        String quantityParam = request.getParameter("itemvalue");
        String url = VIEW_YOUR_CART;

        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                request.setAttribute("errorMessage", "Session has expired. Please log in again.");
                url = "login.jsp"; // Redirect to login or home page
            } else {
                ItemsCart cart = (ItemsCart) session.getAttribute("CART");

                // Create a new cart if it doesn't exist
                if (cart == null) {
                    cart = new ItemsCart();
                    session.setAttribute("CART", cart);
                }

                if (itemId != null && !itemId.trim().isEmpty() && quantityParam != null) {
                    try {
                        int quantity = Integer.parseInt(quantityParam);

                        // Fetch the item from the database
                        ItemsDAO dao = new ItemsDAO();
                        ItemsDTO item = dao.GetItems(itemId);

                        if (item != null) {
                            if (quantity >= 0) {
                                cart.updateCart(item, quantity);
                                session.setAttribute("CART", cart);
                            } else {
                                request.setAttribute("errorMessage", "Quantity cannot be negative.");
                            }
                        } else {
                            request.setAttribute("errorMessage", "Item not found.");
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("errorMessage", "Invalid quantity format.");
                    } catch (ClassNotFoundException | SQLException e) {
                        log("UpdateCartServlet - Exception: " + e.getMessage(), e);
                        request.setAttribute("errorMessage", "An error occurred while updating the cart.");
                    }
                }
            }
        } finally {
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
