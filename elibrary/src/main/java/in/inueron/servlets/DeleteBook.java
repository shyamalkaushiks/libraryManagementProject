package in.inueron.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.inueron.Dao.BookDao;


@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String callno=request.getParameter("callno");	
	   BookDao b1=new BookDao();
	   b1.deletebycall(callno);
	   request.getRequestDispatcher("ViewBook").include(request, response);
	


}
}
