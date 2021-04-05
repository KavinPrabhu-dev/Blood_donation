<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="connection.GetConnection"%>
<%@page import="java.text.DateFormat" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="bg_search.css">
</head>
<body onload="startup();">
	<div class="header">
		<a href="index.html" class="logo">BLOOD DONATION</a>
		<div class="header-right">
			<a class="active" href="index.html">Home</a>
		</div>
	</div>
	<div id="createspace">
		<pre> </pre>
	</div>
	
	<table border="1" class="styled-table" style="margin-left: 5%;">
		<tr style="top: 20%;">
			<td>Name</td>
			<td>Phone number</td>
			<td>Location</td>
		</tr>
		<%
		
		        String phno=request.getParameter("customername");
				System.out.println(phno);
			    try {
				Connection con = GetConnection.getconnection();
				Statement statement = con.createStatement();
				String sql = "select * from bloodrequest where dphno='"+phno+"' AND status='"+"pending"+"'";

				ResultSet resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
                    
					
		%>
		<tr>
			<td><%=resultSet.getString("name")%></td>
			<td><%=resultSet.getString("phno")%></td>
			<td><%=resultSet.getString("location") %>
		</tr>
		<%
			}
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
		
	</table>
	<div id="createspace1">
		<pre> </pre>
		</div>
	<div class="loginbox" style="top: 30%;">
		<form action="accept_response" method="post">
		<label for="uid">Phone number:</label>
		<input type="text" name="uid" required="required"><br>
		<input type="submit" value="ACCEPT">
		
	</div>
	
	</form>
	</div>
	<div id="footer">
		<p>CREATED BY TEAM-6</p>
		<p>EVERY BLOOD DONOR IS A LIFE SAVER!!</p>
	</div>
</body>
</html>