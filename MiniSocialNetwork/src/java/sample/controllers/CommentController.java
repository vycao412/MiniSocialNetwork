/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sample.daos.CommentDAO;
import sample.daos.NotifyDAO;
import sample.dtos.CommentDTO;
import sample.dtos.NotifyDTO;

/**
 *
 * @author User
 */
public class CommentController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CommentController.class);

    public static final String SUCCESS = "DetailController";
    public static final String ERROR = "error.jsp";

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

        LOG.info("CommentController");

        String url = ERROR;

        try {
            // lay du lieu tu form comment
            String postID_Str = request.getParameter("txtPostId");
            int postID = Integer.parseInt(postID_Str);
            String userID_Str = request.getParameter("userID");
            int userID = Integer.parseInt(userID_Str);
            String comment = request.getParameter("txtComment");

            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            
            CommentDAO dao = new CommentDAO();
            CommentDTO dto = new CommentDTO(postID, userID, comment, date, 1);
            
            NotifyDAO notifyDao = new NotifyDAO();
            NotifyDTO notify = null;

            if (dao.addComment(dto)) {
                notify = new NotifyDTO(postID, userID, date, "Commented", 1);
                if (notifyDao.create(notify)) {
                    url = SUCCESS;
                }               
            }
        } catch (Exception e) {
            LOG.error("Error at CommentController: " + e.toString());
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
