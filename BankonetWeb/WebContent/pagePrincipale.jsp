<%@ page language="java" import="com.bankonet.model.Client" %>
<%@ page language="java" import="com.bankonet.model.Compte" %>
<%@ page language="java" import="java.util.List" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<title>Principale</title>
	
</head>
<body>
	<%
	Client client = (Client) session.getAttribute("client");
	%>
	<p>Client:<%= client.getPrenom()+" "+client.getNom() %></p>
	
	<%
	List<Compte> comptesEpargnes = client.getCompteEpargneList();
	if(comptesEpargnes.size()>0)
		{
		%>
		<%="<a href= \"/BankonetWeb/CompteEpargneListe\">Compte Epargne</a>"%>
		<%
		}
	%>
	
	<a href= "/BankonetWeb/CompteCourantListe">Compte Courant</a>
	<a href= "/BankonetWeb/initVirement">Faire Virement</a>
	<a href= "fin.jsp">Déconnexion</a>

</body>
</html>