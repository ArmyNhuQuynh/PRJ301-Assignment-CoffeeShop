/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Items.ItemsDAO;
import Items.ItemsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ViewWishListServlet", urlPatterns = {"/ViewWishListServlet"})
public class ViewWishListServlet extends HttpServlet {
        private final String HOME_PAGE = "home.jsp";
        private final String VIEW_WISH_LIST = "viewWishList.jsp";


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
    String url = HOME_PAGE;
    
    try  {
        Cookie[] arr = request.getCookies();
        ArrayList<ItemsDTO> result = new ArrayList<>();
        
        if (arr != null) {
            ItemsDAO dao = new ItemsDAO(); // Create DAO instance outside the loop

            for (Cookie c : arr) {
                if ("wishlist".equals(c.getName())) {
                    String ids = c.getValue();
                    String[] listid = ids.split(",");
                    
                    if (listid != null && listid.length > 0) {
                        for (String id : listid) {
                            // Check if the id is valid before processing
                            if (id != null && !id.trim().isEmpty()) {
                                ItemsDTO item = dao.GetItems(id);
                                if (item != null) {
                                    result.add(item);
                                }
                            }
                        }
                        request.setAttribute("listItemLike", result);
                    }
                }
            }
        }
        
        url = VIEW_WISH_LIST;
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(ViewWishListServlet.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        // Use forward instead of sendRedirect to keep request attributes
        request.getRequestDispatcher(url).forward(request, response);
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
