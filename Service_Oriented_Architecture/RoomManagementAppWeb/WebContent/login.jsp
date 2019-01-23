<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOGIN PAGE</title>
</head>

<!-- For the room management -->
<body>
	<form method="post" action="check.do">
		<!--  Servlet LoginVerify que vous allez faire! -->	
		<table>
			<tr>
				<td>Pseudo</td>
				<td><input type="text" name="uname"></td>
			</tr>
			<tr>
				<td>Mot de Passe</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td> </td>
				<td><input type="submit" value="login"></td>
			</tr>
		</table>
	</form>
</body>
</html>