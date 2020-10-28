package vasavi;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "thirdpage", urlPatterns = { "/thirdpage" })
public class thirdpage extends HttpServlet {
	
    public thirdpage() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		AuthenticationServlet obj = new AuthenticationServlet();
		String username =  obj.getUser();
		String password = obj.getPassword();
		PrintWriter out=response.getWriter();
		MySQLDB obj2 = new MySQLDB();
		Boolean result= obj2.authenticate(username, password);		
		if(result) {
			RequestDispatcher rd =request.getRequestDispatcher("/thirdpage.html");
			rd.forward(request, response);
		}
		else {
			out.print("Login to view thid page");
			RequestDispatcher rd =request.getRequestDispatcher("/login.html");
			rd.include(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	}

}