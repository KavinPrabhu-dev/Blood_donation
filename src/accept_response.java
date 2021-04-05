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

@WebServlet("/accept_response")
public class accept_response extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Server at: ").append(request.getContextPath()); 
		String uid=request.getParameter("uid");
		String phno="",name="",address="";
		System.out.println(uid);
		PrintWriter pw =response.getWriter();
		
		
		try    
        {    
			Connection con=GetConnection.getconnection();  
			Statement statement = con.createStatement();
			String sql = "select * from bloodrequest where phno='"+uid+"'";
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				phno=resultSet.getString("dphno");
			}
			String sql1 = "select * from userregister where phno='"+phno+"'";
			ResultSet resultSet1 = statement.executeQuery(sql1);
			while(resultSet1.next())
			{
				name=resultSet1.getString("firstname")+" "+resultSet1.getString("lastname");
				address=resultSet1.getString("address");
			}
			String ACCOUNT_SID ="AC1f9229a469c8e4fa94f1118bb26d9a28";
            String AUTH_TOKEN ="539fc5ef5dbd58b3b446ce98dab6d81c";

            String userid = "The donor has accepted your request name : "+name+" Phone number : "+phno+" Address : "+address;
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message1 = Message
                    .creator(new PhoneNumber("+91"+uid), // to
                            new PhoneNumber("+13026130392"), // from
                            userid)
                    .create();
            	System.out.println(message1);
			PreparedStatement p = con.prepareStatement("update bloodrequest set status='" +"Accepted" + "' where phno='"+uid+"'");
			int r = p.executeUpdate();
			if (r > 0) {
				System.out.println("Updated");
				response.sendRedirect("user_dashboard.html");
			} else {
				System.out.println("Failed");
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