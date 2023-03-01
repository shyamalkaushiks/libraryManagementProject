package in.inueron.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.inueron.Dao.BookDao;


@WebServlet("/ReturnBook")
public class ReturnBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Return Book</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navlibrarian.html").include(request, response);
		
		out.println("<div class='container'>");
		String callno=request.getParameter("callno");
		String studentid=request.getParameter("studentid");
		BookDao b1=new BookDao();
		Date d =(Date) request.getSession().getAttribute("dateissued");
		int v=b1.returned(callno, studentid);
		
		java.sql.Date currentDate=new java.sql.Date(System.currentTimeMillis());
		long diffInMillies = Math.abs(currentDate.getTime() - d.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        System.out.println(diffInDays);
        
        int finedays=(int) (diffInDays-15);
        int fineamount=0;
        if(finedays>0)
        {
		  fineamount=5*finedays;
        }
        else
        {
        	
        }
		if(v==1)
		{
			out.println("book returned hope you enjoy your fine amount is"+fineamount);
		}
		else {
			out.println("book  not returned hope you enjoy ");	
		}
		
		
		
		
		
	}

}
