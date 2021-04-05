import java.io.IOException;    
import java.io.PrintWriter;    
import java.sql.*;    
import javax.servlet.ServletException;    
import javax.servlet.annotation.WebServlet;    
import javax.servlet.http.HttpServlet;    
import javax.servlet.http.HttpServletRequest;    
import javax.servlet.http.HttpServletResponse;
import connection.GetConnection;
@WebServlet("/user_login")
public class user_login extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Server at: ").append(request.getContextPath());
		
		String username = request.getParameter("uname");
		String password = request.getParameter("pass");
		
		System.out.println(username+"  "+password);
		
		try{
			PrintWriter out=response.getWriter();
			Connection con = GetConnection.getconnection();
			PreparedStatement ps=con.prepareStatement("select * from userregister where phno='"+username+"'");
			ResultSet res=ps.executeQuery();
			while(res.next())
			{
				if(res.getString("phno").equals(username) && res.getString("password").equals(password) )
				{
					System.out.println("Success");
					response.sendRedirect("user_dashboard.html");	
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
