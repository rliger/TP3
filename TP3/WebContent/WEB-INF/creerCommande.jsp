<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'une commande</title>
        <link type="text/css" rel="stylesheet" href="<c:url value = "inc/style.css"/>"/>
    </head>
    <body>
    <c:import url="../inc/menu.jsp"/>
        <div>
            <form method="post" action="creationCommande">
            	<c:set var="client" value="${ commande.client }" scope="request" />
                <c:import url="../inc/inc_client_form.jsp"/>
                <fieldset>
                    <legend>Informations commande</legend>
                    
                    <label for="dateCommande">Date <span class="requis">*</span></label>
                    <input type="text" id="dateCommande" name="dateCommande" value="${commande.date}" size="20" maxlength="20" disabled />
                    <span class="erreur">${formCommande.erreurs['dateCommande']}</span>
                    <br />
                    
                    <label for="montantCommande">Montant <span class="requis">*</span></label>
                    <input type="text" id="montantCommande" name="montantCommande" value="${commande.montant}" size="20" maxlength="20" />
                    <span class="erreur">${formCommande.erreurs['montantCommande']}</span>
                    <br />
                    
                    <label for="modePaiementCommande">Mode de paiement <span class="requis">*</span></label>
                    <input type="text" id="modePaiementCommande" name="modePaiementCommande" value="${commande.modePaiement}" size="20" maxlength="20" />
                    <span class="erreur">${formCommande.erreurs['modePaiementCommande']}</span>
                    <br />
                    
                    <label for="statutPaiementCommande">Statut du paiement</label>
                    <input type="text" id="statutPaiementCommande" name="statutPaiementCommande" value="${commande.statutPaiement}" size="20" maxlength="20" />
                    <span class="erreur">${formCommande.erreurs['statutPaiementCommande']}</span>
                    <br />
                    
                    <label for="modeLivraisonCommande">Mode de livraison <span class="requis">*</span></label>
                    <input type="text" id="modeLivraisonCommande" name="modeLivraisonCommande" value="${commande.modeLivraison}" size="20" maxlength="20" />
                    <span class="erreur">${formCommande.erreurs['modeLivraisonCommande']}</span>
                    <br />
                    
                    <label for="statutLivraisonCommande">Statut de la livraison</label>
                    <input type="text" id="statutLivraisonCommande" name="statutLivraisonCommande" value="${commande.statutLivraison}" size="20" maxlength="20" />
                    <span class="erreur">${formCommande.erreurs['statutLivraisonCommande']}</span>
                    <br />
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
                <p class="erreur">${formCommande.resultat}</p>
            </form>
        </div>
    </body>
</html>