package fr.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import fr.tp.beans.Client;
import fr.tp.beans.Commande;

public class CreationCommande extends HttpServlet {

    public static final String CHAMP_NOM             = "nomClient";
    public static final String CHAMP_PRENOM          = "prenomClient";
    public static final String CHAMP_ADRESSE         = "adresseClient";
    public static final String CHAMP_TELEPHONE       = "telephoneClient";
    public static final String CHAMP_EMAIL           = "emailClient";
    public static final String CHAMP_DATE            = "dateCommande";
    public static final String CHAMP_MONTANT         = "montantCommande";
    public static final String CHAMP_MODEPAIEMENT    = "modePaiementCommande";
    public static final String CHAMP_STATUTPAIEMENT  = "statutPaiementCommande";
    public static final String CHAMP_MODELIVRAISON   = "modeLivraisonCommande";
    public static final String CHAMP_STATUTLIVRAISON = "statutLivraisonCommande";

    public static final String ATT_CLIENT            = "client";
    public static final String ATT_MESSAGE           = "message";
    public static final String ATT_COMMANDE          = "commande";

    public static final String FORMAT_DATE           = "dd/MM/yyyy HH:mm:ss";

    public static final String VUE                   = "/afficherCommande.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /*
         * Récupération des données saisies, envoyées en tant que paramètres de
         * la requête GET générée à la validation du formulaire
         */
        String nom = request.getParameter( CHAMP_NOM );
        String prenom = request.getParameter( CHAMP_PRENOM );
        String adresse = request.getParameter( CHAMP_ADRESSE );
        String telephone = request.getParameter( CHAMP_TELEPHONE );
        String email = request.getParameter( CHAMP_EMAIL );

        /* Récupération de la date courante */
        DateTime dt = new DateTime();
        /* Conversion de la date en String selon le format défini */
        DateTimeFormatter formatter = DateTimeFormat.forPattern( FORMAT_DATE );
        String date = dt.toString( formatter );
        double montant;
        try {
            /* Récupération du montant */
            montant = Double.parseDouble( request.getParameter( CHAMP_MONTANT ) );
        } catch ( NumberFormatException e ) {
            /* Initialisation à -1 si le montant n'est pas un nombre correct */
            montant = -1;
        }
        String modePaiement = request.getParameter( CHAMP_MODEPAIEMENT );
        String statutPaiement = request.getParameter( CHAMP_STATUTPAIEMENT );
        String modeLivraison = request.getParameter( CHAMP_MODELIVRAISON );
        String statutLivraison = request.getParameter( CHAMP_STATUTLIVRAISON );

        Boolean message;
        /*
         * Initialisation du message à afficher : si un des champs obligatoires
         * du formulaire n'est pas renseigné, alors on affiche un message
         * d'erreur, sinon on affiche un message de succès
         */
        if ( nom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty() ) {
            message = true;
        } else {
            message = false;
        }
        /*
         * Création des beans Client et Commande et initialisation avec les
         * données récupérées
         */
        Client client = new Client();
        client.setNom( nom );
        client.setPrenom( prenom );
        client.setAdresse( adresse );
        client.setTelephone( telephone );
        client.setEmail( email );

        Commande commande = new Commande();
        commande.setClient( client );
        commande.setDate( date );
        commande.setMontant( montant );
        commande.setModePaiement( modePaiement );
        commande.setStatutPaiement( statutPaiement );
        commande.setModeLivraison( modeLivraison );
        commande.setStatutLivraison( statutLivraison );

        /* Ajout du bean et du message à l'objet requête */
        request.setAttribute( ATT_COMMANDE, commande );
        request.setAttribute( ATT_MESSAGE, message );

        /* Transmission à la page JSP en charge de l'affichage des données */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}