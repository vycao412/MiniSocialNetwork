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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.ArticleDAO;
import sample.daos.NotifyDAO;
import sample.daos.UserDAO;
import sample.dtos.ArticleDTO;
import sample.dtos.NotifyDTO;
import sample.dtos.UserDTO;

/**
 *
 * @author VYCAO
 */
public class NotifyController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(NotifyController.class);

    public static final String SUCCESS = "notify.jsp";
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

        String url = ERROR;

        LOG.info("NotifyController");

        try {
            String userID_Str = request.getParameter("userID");
            int userID = Integer.parseInt(userID_Str);
            HttpSession session = request.getSession();
            UserDTO userLogin = (UserDTO) session.getAttribute("USER");

            UserDAO userDao = new UserDAO();
            UserDTO user = null;

            ArticleDAO articleDao = new ArticleDAO();
            ArticleDTO article = null;

            NotifyDAO dao = new NotifyDAO();

            List<NotifyDTO> listNotify = dao.getAllNotify(userID);

            if (listNotify != null) {
                request.setAttribute("LIST_NOTIFY", listNotify);

                HashMap<Integer, String> mapName = new HashMap<>();
                HashMap<Integer, String> mapPost = new HashMap<>();
                for (NotifyDTO notify : listNotify) {
                    mapName.put(notify.getUserID(), userDao.searchUserByUserID(notify.getUserID()).getName());
                    ArticleDTO dto = articleDao.search(notify.getPostID());
                    if (dto != null) {
                        mapPost.put(notify.getPostID(), articleDao.search(notify.getPostID()).getTitle());   
                    }                                     
                }
                request.setAttribute("CMT_NAME", mapName);
                request.setAttribute("POST", mapPost);
            }
            if (userLogin.getUserID() == userID) {
                url = SUCCESS;
            }

        } catch (Exception e) {
            LOG.error("Error at NotifyController" + e.toString());
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
