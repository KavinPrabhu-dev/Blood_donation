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
	
	<table id="ConsutaBPM" border="1" class="styled-table" style="margin-left: 35%;">
		<tr style="top: 20%;">
			<td>Phone number</td>
			<td>Name</td>
			<td>Date</td>
		</tr>
		<%
			String uid = request.getParameter("customername");
		    System.out.println(uid+"1");
			try {
				Connection con = GetConnection.getconnection();
				Statement statement = con.createStatement();
				String sql =  "select * from history where phno='"+ uid +"'";

				ResultSet resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
		%>
		<tr>
			<td><%=resultSet.getString("phno")%></td>
			<td><%=resultSet.getString("name")%></td>
			<td><%=resultSet.getString("date")%></td>
		</tr>
		<%}
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
		
	</table>
	<pre style="margin-left: 45%; text-transform: uppercase;">Export : <input type="button" id="btnExport" value="Printout" onclick="imprimir()"/></pre>
	<p>
		    
		    <script type="text/javascript">
		    function imprimir() {
		    	alert("Can start printing?");
		        var divToPrint=document.getElementById("ConsutaBPM");
		        newWin= window.open("");
		        newWin.document.write(divToPrint.outerHTML);
		        newWin.print();
		        newWin.close();
		    }
		    </script>
	  </p>
	<div id="createspace1">
		<pre> </pre>
		</div>
	<div id="footer">
		<p>CREATED BY TEAM-6</p>
		<p>EVERY BLOOD DONOR IS A LIFE SAVER!!</p>
	</div>
</body>
</html>