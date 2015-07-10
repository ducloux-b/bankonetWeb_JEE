<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="com.bankonet.model.Client" %>
<%@ page language="java" import="com.bankonet.model.Compte" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach items="${client.compteEpargneList}" var="compteEpargne">
		<c:if test="${compteEpargne.identifiant == param.id}">
			<table border="1">
				<thead>
					<tr>
						<th>Identifiant</th>
						<th>Libellé</th>
						<th>Solde</th>
						<th>Taux d'intérêt</th>
						<th>Plafond des échéances</th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<td><c:out value="${compteEpargne.identifiant}" /></td>
							<td><c:out value="${compteEpargne.libelle}" /></td>
							<td><c:out value="${compteEpargne.solde}" /></td>
							<td><c:out value="${compteEpargne.tauxInteret}" /></td>
							<td><c:out value="${compteEpargne.plafond}" /></td>
							
						</tr>
				</tbody>
			</table>
		</c:if>
</c:forEach>

<a href="<%=request.getContextPath()+ "/pagePrincipale.jsp"%>">Page Principale</a>
</body>
</html>