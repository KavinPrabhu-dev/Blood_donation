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

@WebServlet("/hblood_search")
public class hblood_search extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Server at: ").append(request.getContextPath());  
		String donoruid = request.getParameter("uid");
		String location=request.getParameter("location");
		
		PrintWriter pw =response.getWriter();
		
		
		try    
        {    
            	String ACCOUNT_SID ="AC1f9229a469c8e4fa94f1118bb26d9a28";
                String AUTH_TOKEN ="539fc5ef5dbd58b3b446ce98dab6d81c";

                String userid = "You have request from "+location;
                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                Message message1 = Message
                        .creator(new PhoneNumber("+91"+donoruid), // to
                                new PhoneNumber("+13026130392"), // from
                                userid)
                        .create();
                	System.out.println(message1);
            	response.sendRedirect("hospital_dashboard.html");
        }    
        catch(Exception e)    
        {    
        	    response.sendRedirect("failed.html");
                e.printStackTrace();    
        }    
            
            
        pw.close();    
    }    

	}