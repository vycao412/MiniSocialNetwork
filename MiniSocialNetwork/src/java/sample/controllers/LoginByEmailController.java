/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.UserDAO;
import sample.dtos.ErrorUserDTO;
import sample.dtos.SendingEmail;

/**
 *
 * @author VYCAO
 */
public class LoginByEmailController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(LoginByEmailController.class);

    private static final String ERROR = "loginByEmail.jsp";
    private static final String SUCCESS = "verify.jsp";

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

        LOG.info("LoginByEmailController");

        try {
            String mail = request.getParameter("txtMail");
            String name = request.getParameter("txtName");
            String password = request.getParameter("txtPassword");
            String rePassword = request.getParameter("txtRePassword");

            boolean check = true;
            ErrorUserDTO error = new ErrorUserDTO();

            if (mail.length() < 0) {
                check = false;
                error.setErrorMail("Mail is required!");
            }
            if (mail.length() > 50) {
                check = false;
                error.setErrorMail("The length of mail is from 1 to 50!");
            }
            if (!mail.matches(".+@gmail.com")) {
                check = false;
                error.setErrorMail("Enter gmail.com email!");
            }
            if (name.length() < 0) {
                check = false;
                error.setErrorName("Name is required!");
            }
            if (name.length() > 100) {
                check = false;
                error.setErrorName("The length of name is from 1 to 100!");
            }
            if (password.length() < 8) {
                check = false;
                error.setErrorPassword("The length of password is at least 8!");
            }
            if (!rePassword.equals(password)) {
                check = false;
                error.setErrorRePassword("This is not match!");
            }

            UserDAO dao = new UserDAO();
            boolean exist = dao.checkMail(mail);
            if (!exist) {
                check = false;
                error.setErrorMail("Mail is duplicated!");
            }

            if (check) {
                int leftLimit = 48; // numeral '0'
                int rightLimit = 122; // letter 'z'
                int targetStringLength = 10;
                Random random = new Random();
                String randomCode = random.ints(leftLimit, rightLimit + 1)
                        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                        .limit(targetStringLength)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();

                HttpSession session = request.getSession();
                session.setAttribute("MAIL", mail);
                session.setAttribute("PASS", password);
                session.setAttribute("NAME", name);
                session.setAttribute("VERIFY_CODE", randomCode);
                SendingEmail se = new SendingEmail(mail, randomCode);
                se.sendEmail();

                url = SUCCESS;
            } else {
                request.setAttribute("ERROR_EMAIL", error);
            }
        } catch (Exception e) {
            LOG.error("Error at LoginByEmailController: " + e.toString());
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
