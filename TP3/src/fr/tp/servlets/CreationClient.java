package fr.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.tp.beans.Client;
import fr.tp.beans.FormClient;

public class CreationClient extends HttpServlet {

    public static final String ATT_CLIENT     = "client";
    public static final String ATT_FORMCLIENT = "formClient";

    public static final String VUE_CLIENT     = "/WEB-INF/afficherClient.jsp";
    public static final String VUE_FORMCLIENT = "/WEB-INF/creerClient.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE_FORMCLIENT ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        FormClient formClient = new FormClient();

        /*
         * Appel au traitement et à la validation de la requête, et récupération
         * du bean en résultant
         */
        Client client = formClient.creationClient( request );

        /* Ajout du bean et du message à l'objet requête */
        request.setAttribute( ATT_CLIENT, client );
        request.setAttribute( ATT_FORMCLIENT, formClient );

        /* Transmission à la page JSP en charge de l'affichage des données */
        if ( formClient.getErreurs().isEmpty() ) {
            this.getServletContext().getRequestDispatcher( VUE_CLIENT ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( VUE_FORMCLIENT ).forward( request, response );
        }
    }
}