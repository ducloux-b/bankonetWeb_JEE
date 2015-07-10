<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title></title>
</head>
<body>


<h1>Bienvenue sur Bankonet</h1><br>

<form action="<%=request.getContextPath()%>/traiterLogin" method="POST">

<table border="0">
	<tr>
		<td align="center" width="200">
			Identifiant : 
		</td>
		<td>
			<input type="text" name="login" maxlength="20" size="20" value="">
		</td>
	</tr>
	<tr>
		<td align="center">
			Mot de Passe : 
		</td>
		<td>
			<input type="password" name="motDePasse" maxlength="20" size="20" value="">
		</td>
	</tr>
	<tr>
		<td align="right">
		<input type="reset" value="Reinitialiser">
		</td>
		<td align="right">
		<input type="submit" value="Se connecter">
		</td>
	</tr>
</table>


</form>


</body>
</html>
