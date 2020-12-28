/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sample.daos.ArticleDAO;
import sample.daos.CommentDAO;
import sample.daos.UserDAO;
import sample.dtos.ArticleDTO;
import sample.dtos.CommentDTO;
import sample.dtos.UserDTO;

/**
 *
 * @author User
 */
public class DetailController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(DetailController.class);

    public static final String SUCCESS = "detail.jsp";
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

        LOG.info("DetailController");

        String url = ERROR;

        try {
            String search = request.getParameter("txtPostId");
            int postID = Integer.parseInt(search);
            ArticleDAO dao = new ArticleDAO();
            ArticleDTO dto = dao.search(postID);

            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.searchUserByUserID(dto.getUserID());
            String name = user.getName();

            CommentDAO commentDao = new CommentDAO();
            List<CommentDTO> listComment = commentDao.getListComment(postID);

            HashMap<Integer, String> listUserComment = new HashMap<Integer, String>();
            if (listComment != null) {
                for (CommentDTO comment : listComment) {
                    UserDTO userComment = userDao.searchUserByUserID(comment.getUserID());

                    listUserComment.put(comment.getUserID(), userComment.getName());
                    request.setAttribute("LIST_USER_COMMENT", listUserComment);
                }
            }
            
            request.setAttribute("ARTICLE", dto);
            request.setAttribute("USER_POST", name);
            request.setAttribute("LIST_COMMENT", listComment);
            url = SUCCESS;
        } catch (Exception e) {
            LOG.error("Error at DetailController" + e.toString());
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
