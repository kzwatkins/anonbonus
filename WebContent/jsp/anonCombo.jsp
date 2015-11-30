<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Anonymous Bonus Points Code</title>
<!-- script src='https://www.google.com/recaptcha/api.js'></script> -->
<link rel="stylesheet" type="text/css" href="../css/combos.css">
<link rel="icon" href="../pics/code.png" type="image/png">

</head>
<body>
	<table align="left">
		<tr class="leftpane">
			<td rowspan="10" colspan="2" class="code"></td>
		</tr>

		<tr>
			<td class="col">
				<table align="center">
					<tr>
						<td rowspan="2" align="right"><img src='../pics/wprlogo.png'
							height='50' alt='Watkins Proactive Research LLC' border='0' /></td>
					</tr>
					<tr>
						<td align="left"><font size='+1'>K. Z. Watkins, Ph.D.</font><br>
							<font size='-1'>Anonymous User Bonus Point Generator</font></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div>Congrats! You Qualify for Bonus Points for Completing the
					Survey!!</div>

			</td>
		</tr>
		<tr>
			<td>
				<hr>
			</td>
		</tr>
		<tr>
			<td>
				<div class="results">${url}</div>

			</td>
		</tr>
		<tr>
			<td>
				<div class="results">${anonCode}</div>

			</td>
		</tr>
	</table>

</body>
</html>