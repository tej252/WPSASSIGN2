package vasavi;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet(
		description = "Performs authentication based on user inputs", 
		urlPatterns = { "/auth" })
		
public class ServletAuthentication extends HttpServlet {
	static String username,password;
	
	
    public AuthenticationServlet() {
        super();
        
    }


	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				username=request.getParameter("username");
				password=request.getParameter("password");
				
				PrintWriter out=response.getWriter();
		
				MySQLDB obj = new MySQLDB();
				Boolean result = obj.authenticate(username, password);
				out.print(" Welcome"+username);
				
				
				if(result) {
					RequestDispatcher rd =request.getRequestDispatcher("/secondpage.html");
					rd.forward(request, response);
				}
				else {
					out.print("Invalid Credentials: "+username);
					RequestDispatcher rd =request.getRequestDispatcher("/index.html");
					rd.include(request, response);
				}
	}
	public String getUser() {
		return username;
	}
	public String getPassword() {
		return password;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}