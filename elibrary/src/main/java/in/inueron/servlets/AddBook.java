package in.inueron.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.inueron.Beans.BookBean;
import in.inueron.Dao.BookDao;


@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter p=response.getWriter();
		 
		String callno=request.getParameter("callno");
		String name=request.getParameter("name");
		String author=request.getParameter("author");
		String publisher=request.getParameter("publisher");
		String quantity=request.getParameter("quantity");
		    int quantity1=Integer.parseInt(quantity);
		//.request.=request.getParameter("callno");

			p.print("<!DOCTYPE html>");
			p.print("<html>");
			p.println("<head>");
			p.println("<title>Add Book Form</title>");
			p.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
			p.println("</head>");
			p.println("<body>");
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
		
		
		
		BookBean b=new BookBean(callno,name,author,publisher,quantity1);
		  BookDao b1=new BookDao();
		   int result= b1.save(b);
		   System.out.println(result);
		 if(result==1)
		 {
		 p.println("<h1>Book Successfully inserted </h1>");
		 request.getRequestDispatcher("addbookform.html").include(request, response);
		 }
		 else
		 {
			 p.println("<h1>Book not inserted </h1>"); 
		 }
		 
		  
		
		
		
	}

}
