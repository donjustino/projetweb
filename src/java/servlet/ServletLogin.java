/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilisateurs.gestionnaires.GestionnaireUtilisateurs;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author Justin
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;

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

        String forwardTo = "";
        String message = "";
        String action = request.getParameter("action");
        HttpSession session = request.getSession(true);
        if (action.equals("checkConnexion")) {
            System.out.println(request.getParameter("log") + request.getParameter("pass"));
            if (gestionnaireUtilisateurs.verifUtilisateur(request.getParameter("log"), request.getParameter("pass")) == true) {
                //request.getParameter("log").equals("admin") && request.getParameter("pass").equals("admin")) {

                session.setAttribute("login", request.getParameter("log"));
                session.setAttribute("mdp", request.getParameter("pass"));
                session.setAttribute("connecte", true);
                RequestDispatcher view = request.getRequestDispatcher("main.jsp?action=listerLesMusiques");
                view.forward(request, response);
            } else {
                session.setAttribute("login", request.getParameter("log"));
                session.setAttribute("mdp", request.getParameter("pass"));
                session.setAttribute("connecte", false);
                RequestDispatcher view = request.getRequestDispatcher("login.jsp?action=erreur");
                view.forward(request, response);
            }
        } else if (action.equals("deconnexion")) {
            session.setAttribute("connecte", false);
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        } 
        else if (action.equals("creerUnUtilisateur")) {

            if (request.getParameter("login") != null) {
                gestionnaireUtilisateurs.creerUtilisateur(request.getParameter("login"), request.getParameter("password"), request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("abonnement"));
                session.setAttribute("connecte", false);
                forwardTo = "login.jsp?";
                message = "Création d'un utilisateur";
                RequestDispatcher view = request.getRequestDispatcher("login.jsp?");
                view.forward(request, response);

            } else {

                session.setAttribute("connecte", false);
                forwardTo = "creationutilisateur.jsp?";
                message = "Création d'un utilisateur";
                RequestDispatcher view = request.getRequestDispatcher("creationutilisateur.jsp?");
                view.forward(request, response);
            }
            // CHERCHER UTILISATEUR
        }

        //getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
// Redirection vers la page d’accueil RequestDispatcher dp = request.getRequestDispatcher("index.jsp"); dp.forward(request, response);
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
