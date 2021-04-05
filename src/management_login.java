import java.io.IOException;    
import java.io.PrintWriter;    
import java.sql.*;    
import javax.servlet.ServletException;    
import javax.servlet.annotation.WebServlet;    
import javax.servlet.http.HttpServlet;    
import javax.servlet.http.HttpServletRequest;    
import javax.servlet.http.HttpServletResponse;
import connection.GetConnection;
@WebServlet("/management_login")
public class management_login extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Server at: ").append(request.getContextPath());
		
		String username = request.getParameter("uname");
		String password = request.getParameter("pass");
		
		System.out.println(username+"  "+password);
		
		try{
			PrintWriter out=response.getWriter();
			Connection con = GetConnection.getconnection();
			PreparedStatement ps=con.prepareStatement("select * from managementlogin where username='"+username+"'");
			ResultSet res=ps.executeQuery();
			while(res.next())
			{
				if(res.getString("username").equals(username) && res.getString("password").equals(password) )
				{
					System.out.println("success");
					response.sendRedirect("hospital_dashboard.html");	
				}
				else
				{
					response.sendRedirect("failed.html");
				}
			}
		}
		catch(Exception e){
			System.out.print(e);
		}

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}