import java.io.IOException;    
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.*;    
import javax.servlet.ServletException;    
import javax.servlet.annotation.WebServlet;    
import javax.servlet.http.HttpServlet;    
import javax.servlet.http.HttpServletRequest;    
import javax.servlet.http.HttpServletResponse;
import connection.GetConnection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet("/user_registration")
public class user_registration extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Server at: ").append(request.getContextPath());
	    DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal=Calendar.getInstance();
		String current_date = dateFormat.format(cal.getTime());  
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phonenumber = request.getParameter("phno");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String bloodgroup=request.getParameter("bloodgroup");
		String age = request.getParameter("age");
		String address = request.getParameter("address");
		String district = request.getParameter("district");
		String pincode = request.getParameter("pincode");
		
		PrintWriter pw =response.getWriter();
		SecureRandom rid = new SecureRandom();
		int uniqueid = rid.nextInt(999);
		String uniid = Integer.toString(uniqueid);
		
		
		try    
        {    
			Connection con=GetConnection.getconnection();    
            String query="insert into userregister(firstname,lastname,email,phno,password,gender,bloodgroup,age,address,district,pincode,uid,date) values (?,?,?,?,?,?,?,?,?,?,?,?,?);";    
            PreparedStatement pstmt=con.prepareStatement(query);    
            pstmt.setString(1, firstname);
            pstmt.setString(2, lastname);
            pstmt.setString(3, email);    
            pstmt.setString(4,phonenumber);    
            pstmt.setString(5, password);    
            pstmt.setString(6, gender);   
            pstmt.setString(7, bloodgroup);
            pstmt.setString(8,age);
            pstmt.setString(9,address);
            pstmt.setString(10,district);
            pstmt.setString(11,pincode);
            pstmt.setString(12, uniid);
            pstmt.setString(13,current_date);
            int x=pstmt.executeUpdate();      
            if(x==1)
            {
            	response.sendRedirect("user_loginform.html");
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