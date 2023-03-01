package in.inueron.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddLibrarianForm
 */
@WebServlet("/AddLibrarianForm")
public class AddLibrarianForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Admin Section</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		 RequestDispatcher req=request.getRequestDispatcher("navadmin.html");
			req.include(request, response);
			 RequestDispatcher req1=request.getRequestDispatcher("LibrarianForm.html");
				req1.include(request, response);
				System.out.println("Helllo sok");
			
			//request.getRequestDispatcher("AddLibrarianForm.html").include(request, response);
			  //PrintWriter p=response.getWriter();
	
	}

}
