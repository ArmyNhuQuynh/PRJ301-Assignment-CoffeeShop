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
public class AddToCartServlet extends HttpServlet {

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
        String lastSearchValue = request.getParameter("txtlastSearchValue");
        String url = HOME_PAGE;
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                session = request.getSession(true);
            }

            ItemsCart cart = (ItemsCart) session.getAttribute("CART");
            if (cart == null) {
                cart = new ItemsCart(); // Create a new cart if none exists
            }

            String itemid = request.getParameter("txtItemId");
            if (itemid != null && !itemid.trim().isEmpty()) {
                ItemsDAO dao = new ItemsDAO();
                ItemsDTO item = dao.GetItems(itemid);

                if (item != null) {
                    cart.addItemToCart(item);
                    session.setAttribute("CART", cart);
                    url = "DispatchServlet?txtSearchValue=" + lastSearchValue + "&btAction=Search";
                } else {
                    log("AddToCartServlet _ No item found with ID: " + itemid);
                }
            } else {
                log("AddToCartServlet _ Invalid itemid: " + itemid);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("AddToCartServlet _ Exception: ", e);
            url = "error.jsp";
        } finally {
            response.sendRedirect(url);
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
