package in.inueron.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.inueron.Beans.LibrarianBean;
import in.inueron.Dao.LibrarianDao;


@WebServlet("/AddLibrarian")
public class AddLibrarian extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add Librarian</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String smobile=request.getParameter("mobile");
		//String mobile=Integer.parseInt(smobile);
		LibrarianBean libbean=new LibrarianBean(name,email,password,smobile);
		LibrarianDao dao=new LibrarianDao();
		int response1=dao.save(libbean);
		
		if(response1==1)
		{
			System.out.println("value is inserted");
			out.print("<h4>Librarian added successfully</h4>");
			request.getRequestDispatcher("LibrarianForm.html").include(request, response);
			
			
			out.println("</div>");
			request.getRequestDispatcher("footer.html").include(request, response);
		}
		else {
			System.out.println("value is notinserted");
			PrintWriter p=response.getWriter();
			p.println("error");
		}System.out.println("value is inserted");
		
	}

}
