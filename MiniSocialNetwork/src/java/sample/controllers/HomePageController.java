/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.ArticleDAO;
import sample.dtos.ArticleDTO;

/**
 *
 * @author User
 */
public class HomePageController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(HomePageController.class);

    public static final String SUCCESS = "homepage.jsp";
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

        LOG.info("HomePageController");
        String url = ERROR;

        try {
            String indexString = request.getParameter("index");

            if (indexString == null) {
                indexString = "1";
            }

            int index = Integer.parseInt(indexString);

            ArticleDAO dao = new ArticleDAO();
            //List<ArticleDTO> listArticle = dao.getListArticle();

            int count = dao.count();
            int pageSize = 20;
            int endPage = 0;
            endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }

            List<ArticleDTO> listArticle = dao.getListArticle(index, pageSize);

            HttpSession session = request.getSession();
            session.setAttribute("LIST_ARTICLES", listArticle);
            session.setAttribute("endPage", endPage);
            session.setAttribute("index", index);

            url = SUCCESS;

        } catch (Exception e) {
            LOG.error("Error at HomePageController" + e.toString());
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
