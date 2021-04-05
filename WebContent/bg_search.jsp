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
<script>
    function startup() {
           document.getElementById('username').value = localStorage.getItem("username");
       }
</script>
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
			<td>U ID</td>
			<td>Name</td>
		</tr>
		<%
		    DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		    Calendar cal=Calendar.getInstance();
		    String current_date = dateFormat.format(cal.getTime()); 
		    
			String uid = request.getParameter("uid");
			String pincode = request.getParameter("pincode");
			System.out.println(uid+" "+pincode);
			try {
				Connection con = GetConnection.getconnection();
				Statement statement = con.createStatement();
				String sql = "";

				if (uid.length() != 0 && pincode.length() != 0) {
					sql = "select * from userregister where bloodgroup='" + uid + "'AND pincode='"
							+ pincode + "'";
				} else if (uid.length() != 0  && pincode.length() == 0) {
					sql = "select * from userregister where bloodgroup='" + uid + "'";
				} else if (uid.length() ==0 && pincode.length() != 0) {
					sql = "select * from userregister where pincode='" + pincode + "'";
				} 

				ResultSet resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
                    
					String date=resultSet.getString("date");
                    Date date_1=dateFormat.parse(current_date);
                    Date date_2=dateFormat.parse(date);
                    if(date_1.compareTo(date_2)>=0){
		%>
		<tr>
			<td><%=resultSet.getString("uid")%></td>
			<td><%=resultSet.getString("firstname")%></td>
		</tr>
		<%}
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
		<form action="blood_request" method="post">
		<label for="uid">User ID:</label>
		<input id="username" type="text" name="username" readonly="readonly"></input>
		<br>
		<label for="uid">U id:</label>
		<input type="text" name="uid" required="required"><br>
		<label for="location">Location:</label>
		<input type="text" name="location" required="required"><br>
		<input type="submit" value="REQUEST">
		
	</div>
	
	</form>
	</div>
	<div id="footer">
		<p>CREATED BY TEAM-6</p>
		<p>EVERY BLOOD DONOR IS A LIFE SAVER!!</p>
	</div>
</body>
</html>