package fr.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.tp.beans.Commande;
import fr.tp.beans.FormCommande;

public class CreationCommande extends HttpServlet {

    public static final String ATT_COMMANDE     = "commande";
    public static final String ATT_FORMCOMMANDE = "formCommande";

    public static final String VUE_COMMANDE     = "/WEB-INF/afficherCommande.jsp";
    public static final String VUE_FORMCOMMANDE = "/WEB-INF/creerCommande.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE_FORMCOMMANDE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        FormCommande formCommande = new FormCommande();

        /*
         * Appel au traitement et à la validation de la requête, et récupération
         * du bean en résultant
         */
        Commande commande = formCommande.creationCommande( request );

        /* Ajout du bean et du message à l'objet requête */
        request.setAttribute( ATT_COMMANDE, commande );
        request.setAttribute( ATT_FORMCOMMANDE, formCommande );

        /* Transmission à la page JSP en charge de l'affichage des données */
        if ( formCommande.getErreurs().isEmpty() ) {
            this.getServletContext().getRequestDispatcher( VUE_COMMANDE ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( VUE_FORMCOMMANDE ).forward( request, response );
        }
    }
}