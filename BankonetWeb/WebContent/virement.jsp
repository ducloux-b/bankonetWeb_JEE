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
	<form action="<%=request.getContextPath() + "/traiterVirement"%>"
		method="post">
		<table border="1">
			<thead>
				<tr>
					<th>Compte Source</th>
					<th>Compte Destination</th>
					<th>Montant</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><select name="compteSource">
					 	<c:forEach items="${comptes}" var="compte">
							<option value="${compte.identifiant}">
								<c:out value="${compte.libelle}"/>
							</option>
						</c:forEach>
					</select></td>
					<td><select name="compteDestination">
							<c:forEach items="${comptes}" var="compte">
							<option value="${compte.identifiant}">
								<c:out value="${compte.libelle}"/>
							</option>
						</c:forEach>
					</select></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</form>
	<a href="<%=request.getContextPath() + "/pagePrincipale.jsp"%>">Page
		Principale</a>
</body>
</html>