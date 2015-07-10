<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
		<thead>
			<tr>
				<th>Intitulé</th>
				<th>Solde</th>
				<th>Taux d'intérêt</th>
				<th>Plafond</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${client.compteEpargneList}" var="compte">
				<tr>
					<td><c:out value="${compte.libelle}" /></td>
					<td><c:out value="${compte.solde}" /></td>
					<td><c:out value="${compte.tauxInteret}" /></td>
					<td><c:out value="${compte.plafond}" /></td>
					<td><a href="<c:url value="/DetailCompteEpargne"><c:param name="id" value="${compte.identifiant}"/></c:url>">Détail<a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="<%=request.getContextPath()+ "/pagePrincipale.jsp"%>">Page Principale</a>
</body>
</html>