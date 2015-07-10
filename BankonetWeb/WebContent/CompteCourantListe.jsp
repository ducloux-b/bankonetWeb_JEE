<%@page import="com.bankonet.model.CompteCourant"%>
<%@ page language="java" import="com.bankonet.model.Client" %>
<%@ page language="java" import="com.bankonet.model.Compte" %>
<%@ page language="java" import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<%
	Client client = (Client) session.getAttribute("client");
	%>
	<p>Client:<%= client.getPrenom()+" "+client.getNom() %></p>
	
	<%
	List<CompteCourant> comptesCourant = client.getCompteCourantList();
	%>
	<table border="1">
		<thead>
			<tr>
				<td>Intitulé</td>
				<td>Solde</td>
				<td>Découvert autorisé</td>
			</tr>
		</thead>
		<tbody>
		<%
		for(CompteCourant compteCourant:comptesCourant)
			{
			%>
			<tr>
				<td><%=compteCourant.getLibelle() %></td>
				<td><%=compteCourant.getSolde() %></td>
				<td><%=compteCourant.getDecouvertAutorise() %></td>
			</tr>
			<%
			}
		
		%>
		</tbody>
	</table>
	
	<a href="<%=request.getContextPath()+ "/pagePrincipale.jsp"%>">Page Principale</a>
</body>
</html>