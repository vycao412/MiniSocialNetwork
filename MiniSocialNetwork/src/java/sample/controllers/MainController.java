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

/**
 *
 * @author User
 */
public class MainController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(MainController.class);

    private static final String ERROR = "error.jsp";
    private static final String FIRST = "login.html";
    private static final String POST_LINK = "post.jsp";
    private static final String RESISTER_LINK = "register.jsp";
    private static final String HOMEPAGE_LINK = "homepage.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String REGISTER = "RegisterController";
    private static final String POST = "PostController";
    private static final String VERIFY = "VerifyController";
    private static final String SEARCH = "SearchController";
    private static final String DETAIL = "DetailController";
    private static final String DELETE = "DeleteController";
    private static final String EMOTION = "EmotionController";
    private static final String COMMENT = "CommentController";
    private static final String LOGINBYEMAIL = "LoginByEmailController";
    private static final String DELETECOMMENT = "DeleteCommentController";
    private static final String SHOWNOTIFY = "NotifyController";
    private static final String SHOWCORRECTNOTIFY = "RespondNotifyController";

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

        LOG.info("MainController");
        String url = ERROR;
        try {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("btnAction");

            if (action == null) {
                url = FIRST;
            }

            if (action.equals("Login")) {
                url = LOGIN;
            }
            if (action.equals("Logout")) {
                url = LOGOUT;
            }
            if (action.equals("SignUp")) {
                url = REGISTER;
            }
            if (action.equals("Post")) {
                url = POST;
            }
            if (action.equals("RegisterLink")) {
                url = RESISTER_LINK;
            }
            if (action.equals("HomePageLink")) {
                url = HOMEPAGE_LINK;
            }
            if (action.equals("Create")) {
                url = POST_LINK;
            }
            if (action.equals("Enter")) {
                url = VERIFY;
            }
            if (action.equals("Search")) {
                url = SEARCH;
            }
            if (action.equals("Detail")) {
                url = DETAIL;
            }
            if (action.equals("Delete")) {
                url = DELETE;
            }
            if (action.equals("Emotion")) {
                url = EMOTION;
            }
            if (action.equals("Comment")) {
                url = COMMENT;
            }
            if (action.equals("LoginByEmail")) {
                url = LOGINBYEMAIL;
            }
            if (action.equals("DeleteComment")) {
                url = DELETECOMMENT;
            }
            if (action.equals("ShowNotify")) {
                url = SHOWNOTIFY;
            }
            if (action.equals("ShowCorrectNotify")) {
                url = SHOWCORRECTNOTIFY;
            }

        } catch (Exception e) {
            LOG.error("Error at MainController: " + e.toString());
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
