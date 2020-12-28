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
import sample.daos.EmotionDAO;
import sample.daos.NotifyDAO;
import sample.daos.UserDAO;
import sample.dtos.ArticleDTO;
import sample.dtos.UserDTO;

/**
 *
 * @author User
 */
public class DeleteController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(DeleteController.class);

    public static final String SUCCESS = "HomePageController";
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

        LOG.info("DeleteController");
        String url = ERROR;

        try {
            String postIDString = request.getParameter("txtPostID");
            int postID = Integer.parseInt(postIDString);
            String userID_Str = request.getParameter("txtUserID");
            int userID = Integer.parseInt(userID_Str);
            String userIDPost_Str = request.getParameter("txtUserIDPost");
            int userIDPost = Integer.parseInt(userIDPost_Str);
            
            ArticleDAO dao = new ArticleDAO();
            ArticleDTO dto = dao.search(postID);
            
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.searchUserByUserID(userID);
            int role = user.getRole();
            
            CommentDAO commentDao = new CommentDAO();
            EmotionDAO emotionDao = new EmotionDAO();
            NotifyDAO notifyDao = new NotifyDAO();

            if (role == 1) {
                if (userID_Str.equals(userIDPost_Str)) {
                    if (dao.deleteByUser(postID)) {
                        commentDao.deleteByUserArticle(postID);
                        emotionDao.deleteByUserArticle(postID);
                        notifyDao.updateUserNotifyDelete(postID);
                        url = SUCCESS;
                    } else {
                        url = CANT;
                    }
                } else {
                    url = CANT;
                }
            }
            if (role == 2) {
                if (dao.deleteByAdmin(postID)) {
                    commentDao.deleteByAdminArticle(postID);
                    emotionDao.deleteByAdminArticle(postID);
                    notifyDao.deleteByAdminNotify(postID);
                    url = SUCCESS;
                }
            }

        } catch (Exception e) {
            LOG.error("Error at DeleteController: " + e.toString());
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
