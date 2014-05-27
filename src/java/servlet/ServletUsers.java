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
import utilisateurs.gestionnaires.GestionnaireUtilisateurs;
import utilisateurs.modeles.Utilisateur;
import musiques.gestionnaires.GestionnairesMusiques;
import musiques.modeles.Morceau;

/**
 *
 * @author Justin
 */
@WebServlet(name = "ServletUsers", urlPatterns = {"/ServletUsers"})
public class ServletUsers extends HttpServlet {

    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;
    @EJB
    private GestionnairesMusiques gestionnaireMusiques;

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
       
        if (action != null) {
            // LISTER LES UTILISATEURS
            if (action.equals("listerLesMusiques")) {
                gestionnaireMusiques.CreationCatalogue();
                Collection<Morceau> liste = gestionnaireMusiques.listeDesMorceaux();
                request.setAttribute("listeDesMusiques", liste);
                forwardTo = "main.jsp?action=listerLesMusiques";
                message = "Liste des musiques";
                getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
                //UTILISATEUR DE TEST
            }  
        }

        RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "&message=" + message);
        dp.forward(request, response);
        //Après un forward, plus rien ne peut être exécuté après !
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
//        HttpSession session = request.getSession();
//        String userName = (String) session.getAttribute("USER");
//
//        if (userName != null) {
//            response.setContentType("text/html");
//            ServletOutputStream out = response.getOutputStream();
//            out.println("<html><body><h1>");
//            out.println("Hello, " + userName + "! ");
//            out.println("</h1></body></html>");
//        } else {
//            response.sendRedirect("login.jsp");
//        }
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
