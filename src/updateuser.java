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

@WebServlet("/updateuser")
public class updateuser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Server at: ").append(request.getContextPath());  
		String phno = request.getParameter("donorphno");
		String name="";
		
		PrintWriter pw =response.getWriter();
		DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal=Calendar.getInstance();
		String current_date = dateFormat.format(cal.getTime());
		String result_date="";
		
		try    
        {    
			Connection con=GetConnection.getconnection();  
			Statement statement = con.createStatement();
			
			String query="select * from userregister where phno='"+phno+"'";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
			name=resultSet.getString("firstname")+" "+resultSet.getString("lastname");
			}    
            String query1="insert into history(phno,name,date) values (?,?,?);";    
            PreparedStatement pstmt=con.prepareStatement(query1);    
            pstmt.setString(1, phno);
            pstmt.setString(2, name);
            pstmt.setString(3, current_date);    
            int x=pstmt.executeUpdate(); 
            cal.setTime(dateFormat.parse(current_date));
            cal.add(Calendar.DAY_OF_MONTH, 60);
            result_date=dateFormat.format(cal.getTime());
            PreparedStatement p = con.prepareStatement("update userregister set date='" +result_date+ "' where phno='"+phno+"'");
			int r = p.executeUpdate();
			if(r>0)
			{
				response.sendRedirect("hospital_dashboard.html");
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