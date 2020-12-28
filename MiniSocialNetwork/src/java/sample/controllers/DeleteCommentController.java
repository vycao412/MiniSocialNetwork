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
import org.apache.log4j.Logger;
import sample.daos.ArticleDAO;
import sample.daos.CommentDAO;
import sample.daos.NotifyDAO;
import sample.daos.UserDAO;
import sample.dtos.UserDTO;

/**
 *
 * @author VYCAO
 */
public class DeleteCommentController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(DeleteCommentController.class);

    public static final String SUCCESS = "DetailController";
    public static final String ERROR = "error.jsp";
    public static final String CANT = "errorDelete.jsp";

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

        String url = ERROR;

        LOG.info("DeleteCommentController");

        try {
            // lay du lieu tu detail.jsp
            String commentID_Str = request.getParameter("commentID");
            int commentID = Integer.parseInt(commentID_Str);
            String userID_Str = request.getParameter("userID");
            int userID = Integer.parseInt(userID_Str);
            String userComment_Str = request.getParameter("userComment");
            int userComment = Integer.parseInt(userComment_Str);
            String postID_Str = request.getParameter("txtPostId");
            int postID = Integer.parseInt(postID_Str);

            CommentDAO dao = new CommentDAO();
            ArticleDAO articleDao = new ArticleDAO();
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.searchUserByUserID(userID);
            int role = user.getRole();

            NotifyDAO notifyDao = new NotifyDAO();

            if (role == 1) {
                if (userID == userComment) {
                    if (dao.deleteByUser(commentID)) {
                        url = SUCCESS;
                    }
                } else {
                    url = CANT;
                }
            } else if (role == 2) {
                if (dao.deleteByAdmin(commentID)) {
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at DeleteCommentController: " + e.toString());
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
