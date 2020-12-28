/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.UserDAO;
import sample.dtos.UserDTO;

/**
 *
 * @author User
 */
public class VerifyController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(VerifyController.class);

    private static final String SUCCESS = "login.html";
    private static final String ERROR = "verify.jsp";

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

        LOG.info("VerifyController");
        String url = ERROR;

        try {
            String code = request.getParameter("txtCode");
            HttpSession session = request.getSession();
            String randomCode = (String) session.getAttribute("VERIFY_CODE");
            String mail = (String) session.getAttribute("MAIL");
            String name = (String) session.getAttribute("NAME");
            String password = (String) session.getAttribute("PASS");

            if (code.equals(randomCode)) {
                UserDTO dto = new UserDTO(mail, name, password, 1, 1, randomCode);
                UserDAO dao = new UserDAO();
                dao.loginByEmail(dto);
                url = SUCCESS;
            } else {
                request.setAttribute("errorCode", "Error verify code!");
            }
        } catch (Exception e) {
            LOG.error("Error at VerifyController: " + e.toString());
        } finally {
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
