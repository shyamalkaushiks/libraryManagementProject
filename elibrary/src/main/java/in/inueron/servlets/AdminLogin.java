package in.inueron.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Admin Section</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
	
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
	if(email.equalsIgnoreCase("shyamalkaushik13@gmail.com")&&(password.equalsIgnoreCase("Shyambaba13")))
	{
	  HttpSession loginsession=request.getSession();
	  loginsession.setAttribute("admin", true);
	  RequestDispatcher req=request.getRequestDispatcher("navadmin.html");
		req.include(request, response);
	 // request.getRequestDispatcher().include(request, response);
		HttpSession h1=request.getSession();
		h1.setAttribute("admin", "hi");
		request.getRequestDispatcher("admincarousel.html").include(request, response);
		
		
	  //PrintWriter p=response.getWriter();
	  //p.println("login ho gaya");
	  
	  
	}
	}

}
