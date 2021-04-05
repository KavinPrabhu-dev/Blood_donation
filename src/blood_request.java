import java.io.IOException;    
import java.io.PrintWriter;
import java.sql.*;    
import javax.servlet.ServletException;    
import javax.servlet.annotation.WebServlet;    
import javax.servlet.http.HttpServlet;    
import javax.servlet.http.HttpServletRequest;    
import javax.servlet.http.HttpServletResponse;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import connection.GetConnection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet("/blood_request")
public class blood_request extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Server at: ").append(request.getContextPath());
	    DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal=Calendar.getInstance();
		String current_date = dateFormat.format(cal.getTime());  
		String phno = request.getParameter("username");
		String donoruid = request.getParameter("uid");
		String status="pending";
		String location=request.getParameter("location");
		String name="",dphno="";
		
		PrintWriter pw =response.getWriter();
		
		
		try    
        {    
			Connection con=GetConnection.getconnection();  
			Statement statement = con.createStatement();
			
			String query="select * from userregister where phno='"+phno+"'";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
			name=resultSet.getString("firstname")+" "+resultSet.getString("lastname");
			}
			String query1="select * from userregister where uid='"+donoruid+"'";
			ResultSet resultSet1 = statement.executeQuery(query1);
			while(resultSet1.next()){
			dphno=resultSet1.getString("phno");
			}
            String query2="insert into bloodrequest(name,phno,location,status,dphno) values (?,?,?,?,?);";    
            PreparedStatement pstmt=con.prepareStatement(query2);    
            pstmt.setString(1,name);
            pstmt.setString(2,phno);
            pstmt.setString(3, location);    
            pstmt.setString(4,status);    
            pstmt.setString(5,dphno);
            int x=pstmt.executeUpdate();      
            if(x==1)
            {
            	String ACCOUNT_SID ="AC1f9229a469c8e4fa94f1118bb26d9a28";
                String AUTH_TOKEN ="539fc5ef5dbd58b3b446ce98dab6d81c";

                String userid = "You have request from "+name+" and Phone number "+phno+" please login and check it";
                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                Message message1 = Message
                        .creator(new PhoneNumber("+91"+dphno), // to
                                new PhoneNumber("+13026130392"), // from
                                userid)
                        .create();
                	System.out.println(message1);
            	response.sendRedirect("user_dashboard.html");
            }
            else{
            	response.sendRedirect("failed.html");
            }
        }    
        catch(Exception e)    
        {    
        	    response.sendRedirect("failed.html");
                e.printStackTrace();    
        }    
            
            
        pw.close();    
    }    

	}