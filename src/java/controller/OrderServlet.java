/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Cart.ItemsCart;
import Items.ItemsDTO;
import Order.OrderDAO;
import OrderDetail.OrderDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {
    private final String ERROR_PAGE = "error.jsp";
    private final String ORDERSUCCESS_PAGE = "orderSuccess.jsp";
    
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

        String phoneNumber = request.getParameter("txtPhoneNumber");
        String tableId = request.getParameter("txtTableId");
        String total = request.getParameter("txtTotal");
        String url = ERROR_PAGE;
        int orderId = 0;

        // Kiểm tra tham số đầu vào
        if (phoneNumber == null || tableId == null || total == null) {
            request.setAttribute("errorMessage", "Thiếu thông tin trong giỏ hàng.");
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            return;
        }

        try {
            OrderDAO orderDao = new OrderDAO();
            orderId = orderDao.CheckOut(phoneNumber, Integer.parseInt(tableId), Integer.parseInt(total));

            if (orderId > 0) {
                // Xóa giỏ hàng sau khi thanh toán thành công
                  request.getSession().invalidate();

                request.setAttribute("orderId", orderId);
                url = ORDERSUCCESS_PAGE;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi xử lý đơn hàng. Vui lòng thử lại."); // Thông báo lỗi cho người dùng
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
