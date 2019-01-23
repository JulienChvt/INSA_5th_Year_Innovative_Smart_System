package displaying_ressources;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginVerify
 */
@WebServlet(urlPatterns= {"/LoginVerify","/check.do","/index.html"})
public class LoginVerify extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginVerify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the login page to enter in the manage room service
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Load the input login and password
		String uname=request.getParameter("uname");
		String password=request.getParameter("password");
		//Verify if the password and login are correct
		if(uname.equals("admin") && password.equals("admin")) {
			
			//Initialize variables to load the success.jsp page
			String DropDownList = "<option value=213>213"; //list all the room created for the drop-down list
			String commandHistory="Login Success"; //store all the commands
			
			//Load attributes, and then the success.jsp page
			request.setAttribute("ListRoom", DropDownList);
			request.setAttribute("history", commandHistory);
			RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
			dispatcher.forward(request, response);
		}
		else
			//if the login/password are incorrect, the error page is displayed
			response.sendRedirect("error.jsp");
		
	}

}
