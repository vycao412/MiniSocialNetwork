/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.ArticleDAO;
import sample.daos.EmotionDAO;
import sample.daos.NotifyDAO;
import sample.daos.UserDAO;
import sample.dtos.ArticleDTO;
import sample.dtos.EmotionDTO;
import sample.dtos.NotifyDTO;
import sample.dtos.UserDTO;

/**
 *
 * @author User
 */
public class EmotionController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EmotionController.class);

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

        LOG.info("EmotionController");

        String url = ERROR;
        try {
            // lay du lieu tu detail.jsp
            HttpSession session = request.getSession();

            String emotion = request.getParameter("emo");
            String postID_Str = request.getParameter("txtPostId");
            int postID = Integer.parseInt(postID_Str);
            String userID_Str = request.getParameter("userID");
            int userID = Integer.parseInt(userID_Str);

            long millis = System.currentTimeMillis();
            Date date = new Date(millis);

            boolean isLike = false;
            boolean isDislike = false;

            EmotionDAO dao = new EmotionDAO();
            EmotionDTO dto = dao.findEmoByUser(postID, userID);

            ArticleDAO articleDao = new ArticleDAO();
            ArticleDTO article = null;

            UserDAO userDao = new UserDAO();
            UserDTO user = null;
            
            NotifyDAO notifyDao = new NotifyDAO();
            NotifyDTO notify = null;

            if (emotion.equals("Like")) {
                isLike = true;
                isDislike = false;
            } else if (emotion.equals("Dislike")) {
                isLike = false;
                isDislike = true;
            }

            boolean check = false;
            
            if (dto != null) {
                if (isLike) {
                    isLike = true;
                    isDislike = false;
                    notify = new NotifyDTO(postID, userID, date, "Liked", 1);
                }
                if (isDislike) {
                    isLike = false;
                    isDislike = true;
                    notify = new NotifyDTO(postID, userID, date, "Disliked", 1);
                }
                if (dto.isIsLike() && isLike) {
                    isLike = false;
                    isDislike = false;
                    notify = new NotifyDTO(postID, userID, date, "deleted like", 1);
                } if (dto.isIsDislike() && isDislike) {
                    isDislike = false;
                    isLike = false;
                    notify = new NotifyDTO(postID, userID, date, "deleted dislike", 1);
                } if (dto.isIsLike() && isDislike) {
                    isDislike = true;
                    isLike = false;
                    notify = new NotifyDTO(postID, userID, date, "Disliked", 1);
                    
                } if (dto.isIsDislike() && isLike) {
                    isLike = true;
                    isDislike = false;
                    notify = new NotifyDTO(postID, userID, date, "Liked", 1);
                    
                }
                dto = new EmotionDTO(postID, userID, isLike, isDislike, date);
                if (dao.update(dto)) {
                    check = true;
                }

            } else {
                if (isLike) {
                    isLike = true;
                    isDislike = false;
                    notify = new NotifyDTO(postID, userID, date, "Liked", 1);
                }
                if (isDislike) {
                    isLike = false;
                    isDislike = true;
                    notify = new NotifyDTO(postID, userID, date, "Disliked", 1);
                }
                dto = new EmotionDTO(postID, userID, isLike, isDislike, date);
                if (dao.create(dto)) {
                    check = true;
                }               
            }
            
            int numLike = dao.countLike(postID);
            int numDislike = dao.countDislike(postID);
            article = new ArticleDTO(postID, numLike, numDislike);
            if (articleDao.update(article)) {
                check = true;
            }
            else {
                check = false;
            }
            
            if (notifyDao.create(notify)) {
                check = true;
            }
            else {
                check = false;
            }
            
            if (check) {
                url = SUCCESS;
            }
            

        } catch (NumberFormatException | SQLException e) {
            LOG.error("Error at EmotionController" + e.toString());
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
