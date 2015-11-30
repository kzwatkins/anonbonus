<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/combos.css">
<link rel="icon" href="pics/code.png" type="image/png">
<title>Anon User Bonus Pt Gen</title>
</head>
<body>
	<form action="auth" method="post" onreset="reset()">
		<table>
			<tr class="leftpane">
				<td rowspan="10" class="code"></td>
			</tr>
			<tr>
				<td class="col" colspan="4">
					<table align="center">
						<tr>
							<td rowspan="2" align="right"><img src='pics/wprlogo.png'
								height='50' alt='Watkins Proactive Research LLC' border='0' />
							</td>
						</tr>
						<tr>
							<td align="left"><font size='+1'>K. Z. Watkins, Ph.D.</font><br>
								<font size='-1'>Anonymous User Bonus Point Generator</font></td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td colspan="4">
					<h1>Welcome to the Anonymous User Bonus Point Generator!</h1> If
					you received login credentials, please enter them below to generate
					an anonymous code for bonus points.
				</td>
			</tr>
			<tr>
				<td align="right" colspan="2">Username:</td>
				<td align="left" colspan="2"><input type="text" name="user"
					class="longbutton" colspan="2"></td>
			</tr>
			<tr>
				<td align="right" colspan="2">Password:</td>
				<td align="left" colspan="2"><input type="password"
					name="password" class="longbutton"></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit"
					value="Login" class="longbutton"></td>
				<td align="center" colspan="2"><input type="reset"
					value="Reset" class="longbutton"></td>
			</tr>

			<tr>
				<td colspan="4" align="center">
					<div class="error" id="invalidLoginMessage">${invalidLoginMessage}</div>
				</td>
			</tr>

		</table>
	</form>

	<script type="text/javascript" src="js/reset.js"></script>
</body>
</html>