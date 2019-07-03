<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Affichage d'un client</title>
<link type="text/css" rel="stylesheet" href="<c:url value = "inc/style.css"/>"/>
</head>
<body>
	<c:import url="inc/menu.jsp" />
	<%-- Affichage de la chaîne "message" transmise par la servlet --%>
	<p class="info">
		<c:if test="${message == true}">
			<p class="info">
				Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br />
				<a href="<c:url value = "creerClient.jsp"/>">Cliquez ici</a> pour
				accéder au formulaire de création d'un client.
			</p>
		</c:if>
		<c:if test="${message ==false}">
			<p class="info">Client créé avec succès !</p>


			<%-- Puis affichage des données enregistrées dans le bean "client" transmis par la servlet --%>
			<p>
				Nom :
				<c:out value="${ client.nom }" />
			</p>
			<p>
				Prénom :
				<c:out value="${ client.prenom }" />
			</p>
			<p>
				Adresse :
				<c:out value="${ client.adresse }" />
			</p>
			<p>
				Numéro de téléphone :
				<c:out value="${ client.telephone }" />
			</p>
			<p>
				Email :
				<c:out value="${ client.email }" />
			</p>
		</c:if>
</body>
</html>