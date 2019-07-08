package fr.tp.beans;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class FormCommande {
    public static final String  CHAMP_NOM             = "nomClient";
    public static final String  CHAMP_PRENOM          = "prenomClient";
    public static final String  CHAMP_ADRESSE         = "adresseClient";
    public static final String  CHAMP_TELEPHONE       = "telephoneClient";
    public static final String  CHAMP_EMAIL           = "emailClient";
    public static final String  CHAMP_DATE            = "dateCommande";
    public static final String  CHAMP_MONTANT         = "montantCommande";
    public static final String  CHAMP_MODEPAIEMENT    = "modePaiementCommande";
    public static final String  CHAMP_STATUTPAIEMENT  = "statutPaiementCommande";
    public static final String  CHAMP_MODELIVRAISON   = "modeLivraisonCommande";
    public static final String  CHAMP_STATUTLIVRAISON = "statutLivraisonCommande";
    public static final String  FORMAT_DATE           = "dd/MM/yyyy HH:mm:ss";

    private String              resultat;
    private Map<String, String> erreurs               = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Commande creationCommande( HttpServletRequest request ) {

        FormClient clientForm = new FormClient();
        Client client = clientForm.creationClient( request );

        erreurs = clientForm.getErreurs();

        DateTime dt = new DateTime();
        DateTimeFormatter formatter = DateTimeFormat.forPattern( FORMAT_DATE );
        String date = dt.toString( formatter );
        String montant = getValeurChamp( request, CHAMP_MONTANT );
        String modePaiement = getValeurChamp( request, CHAMP_MODEPAIEMENT );
        String statutPaiement = getValeurChamp( request, CHAMP_STATUTPAIEMENT );
        String modeLivraison = getValeurChamp( request, CHAMP_MODELIVRAISON );
        String statutLivraison = getValeurChamp( request, CHAMP_STATUTLIVRAISON );

        Commande commande = new Commande();

        commande.setClient( client );

        try {
            validationDate( date );
        } catch ( Exception e ) {
            setErreur( CHAMP_DATE, e.getMessage() );
        }
        commande.setDate( date );

        try {
            validationMontant( montant );
        } catch ( Exception e ) {
            setErreur( CHAMP_MONTANT, e.getMessage() );
        }
        if ( estUnDouble( montant ) ) {
            commande.setMontant( Double.parseDouble( montant ) );
        }

        try {
            validationModePaiement( modePaiement );
        } catch ( Exception e ) {
            setErreur( CHAMP_MODEPAIEMENT, e.getMessage() );
        }
        commande.setModePaiement( modePaiement );

        try {
            validationStatutPaiement( statutPaiement );
        } catch ( Exception e ) {
            setErreur( CHAMP_STATUTPAIEMENT, e.getMessage() );
        }
        commande.setStatutPaiement( statutPaiement );

        try {
            validationModeLivraison( modeLivraison );
        } catch ( Exception e ) {
            setErreur( CHAMP_MODELIVRAISON, e.getMessage() );
        }
        commande.setModeLivraison( modeLivraison );

        try {
            validationStatutLivraison( statutLivraison );
        } catch ( Exception e ) {
            setErreur( CHAMP_STATUTLIVRAISON, e.getMessage() );
        }
        commande.setStatutLivraison( statutLivraison );

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de Création de la commande.";
        } else {
            resultat = "Le formulaire de commande comporte des erreurs.";
        }

        return commande;
    }

    private void validationNom( String nom ) throws Exception {
        if ( nom == null || nom.length() < 2 ) {
            throw new Exception( "Le nom du client doit contenir au moins 2 caractères." );
        }
    }

    private void validationPrenom( String prenom ) throws Exception {
        if ( prenom == null || prenom.length() < 2 ) {
            throw new Exception( "Le prénom du client doit contenir au moins 2 caractères." );
        }
    }

    private void validationAdresse( String adresse ) throws Exception {
        if ( adresse == null || adresse.length() < 10 ) {
            throw new Exception( "L'adresse du client doit contenir au moins 10 caractères." );
        }
    }

    private void validationEmail( String email ) throws Exception {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new Exception( "Merci de saisir une adresse mail valide." );
            }
        } else {
            throw new Exception( "Merci de saisir une adresse mail." );
        }
    }

    private boolean estUnEntier( String telephone ) {
        try {
            Integer.parseInt( telephone );
        } catch ( NumberFormatException e ) {
            return false;
        }

        return true;
    }

    private void validationTelephone( String telephone ) throws Exception {
        if ( telephone != null && telephone.length() < 4 || !estUnEntier( telephone ) ) {
            throw new Exception( "Le numéro de téléphone client doit contenir des numéros et au minimum 4." );
        }
    }

    private void validationDate( String date ) throws Exception {
        if ( date == null || date.length() < 10 ) {
            throw new Exception( "La date n'est pas correcte." );
        }
    }

    private boolean estUnDouble( String montant ) {
        if ( montant == null ) {
            return false;
        }
        try {
            Double.parseDouble( montant );
        } catch ( NumberFormatException e ) {
            return false;
        }

        return true;
    }

    private void validationMontant( String montant ) throws Exception {
        if ( montant == null ) {
            throw new Exception( "Aucun montant saisi." );
        }
        if ( montant != null && !estUnDouble( montant ) ) {
            throw new Exception( "Le montant saisi n'est pas correct." );
        }
    }

    private void validationModePaiement( String modePaiement ) throws Exception {
        if ( modePaiement == null || modePaiement.length() < 2 ) {
            throw new Exception( "Le mode de paiement doit contenir au moins 2 caractères." );
        }
    }

    private void validationStatutPaiement( String statutPaiement ) throws Exception {
        if ( statutPaiement != null && statutPaiement.length() < 2 ) {
            throw new Exception( "Le statut du paiement doit contenir au moins 2 caractères." );
        }
    }

    private void validationModeLivraison( String modeLivraison ) throws Exception {
        if ( modeLivraison == null || modeLivraison.length() < 2 ) {
            throw new Exception( "Le mode de livraison doit contenir au moins 2 caractères." );
        }
    }

    private void validationStatutLivraison( String statutLivraison ) throws Exception {
        if ( statutLivraison != null && statutLivraison.length() < 2 ) {
            throw new Exception( "Le statut de la livraison doit contenir au moins 2 caractères." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }

}
