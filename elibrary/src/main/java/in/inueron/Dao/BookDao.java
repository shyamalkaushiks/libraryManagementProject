package in.inueron.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.inueron.Beans.BookBean;
import in.inueron.Beans.IssueBookBean;
import in.inueron.Util.Dbconnection;

public class BookDao {

	Connection connection = null;

	public BookDao() {

	}

	public int save(BookBean b) {
		int response = 0;
		try {
			// BookBean b = new BookBean();

			connection = Dbconnection.jdbcconnection();
			String sql = "insert into e_book values(?,?,?,?,?,?)";
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setString(1, b.getCallno());
			pre.setString(2, b.getName());
			pre.setString(3, b.getAuthor());
			pre.setString(4, b.getPublisher());
			pre.setInt(5, b.getQuantity());
			pre.setInt(6, 0);
			response = pre.executeUpdate();
			System.out.println("res" + response);

		} catch (Exception e) {
			e.getMessage();
		}
		return response;
	}

	public List<BookBean> view() {
		// TODO Auto-generated method stub
		try {
			// BookBean b = new BookBean();
			
			connection = Dbconnection.jdbcconnection();
			String sql = "select * from e_book";
			PreparedStatement pre = connection.prepareStatement(sql);
			ResultSet iterator = pre.executeQuery();
			List<BookBean> mylist = new ArrayList<BookBean>();
			while (iterator.next()) {
				BookBean b1 = new BookBean();
				b1.setCallno(iterator.getString("callno"));
				b1.setName(iterator.getString("name"));
				b1.setPublisher(iterator.getString("publisher"));
				b1.setQuantity(iterator.getInt("quantity"));
				b1.setCallno(iterator.getString("callno"));
				b1.setAuthor(iterator.getString("author"));
				b1.setIssued(iterator.getInt("issued"));

				mylist.add(b1);
			}
		

			return mylist;
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
	
	public int deletebycall(String callno)
	{
		try {
		connection = Dbconnection.jdbcconnection();
		String sql="delete from e_book where callno=?";
		PreparedStatement pre2=connection.prepareStatement(sql);
		pre2.setString(1, callno);
		int re=pre2.executeUpdate();
		return re;
		}catch(Exception e)
		{
			e.getMessage();
		}
		return 0;
		}

	public static boolean checkissue(String callno)
	{
		boolean status=false;
		try {
		Connection connection1 = Dbconnection.jdbcconnection();
		String sql="select * from e_book where callno=? and quantity>=issued";
		PreparedStatement pre2=connection1.prepareStatement(sql);
		pre2.setString(1, callno);
		  ResultSet re=pre2.executeQuery();
		  if(re.next())
		  {
			 status=true;
		  }
		  
		
		}catch(Exception e)
		{
			e.getMessage();
		}
		return status;
		
	}
	public static  List<IssueBookBean> viewissued()
	{
		List<IssueBookBean> booklist=new ArrayList<IssueBookBean>();
		try
		{
			Connection con=Dbconnection.jdbcconnection();
			PreparedStatement ps=con.prepareStatement("select * from e_issuebook order by issuedate desc");
			ResultSet rs6=ps.executeQuery();

	
		while(rs6.next())
		{
			
			IssueBookBean bean=new IssueBookBean();
			bean.setCallno(rs6.getString("callno"));
			bean.setStudentid(rs6.getString("studentid"));
			bean.setStudentname(rs6.getString("studentname"));
			bean.setStudentmobile(rs6.getLong("studentmobile"));
			bean.setIssueddate(rs6.getDate("issuedate"));
			bean.setReturnstatus(rs6.getString("returnstatus"));
			booklist.add(bean);
		     System.out.println(booklist.size());
		}
		con.close();       
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		
		return booklist;
	}	
	public static int returned(String callno,String studentid)
	{
		int updatereturned=0;
		try
		{
		String sqlup="update e_issuebook set returnstatus='true' where callno=? and studentid=?";
		Connection connection=Dbconnection.jdbcconnection();
		PreparedStatement p1=connection.prepareStatement(sqlup);
		p1.setString(1, callno);
		p1.setString(2,studentid);
		int updatechecker=p1.executeUpdate();
		if(updatechecker>0)
		{
			String sqlup1="update e_book set issued=? where callno=?";
			PreparedStatement p2=connection.prepareStatement(sqlup1);
			int valuereturn=getIssued(callno);
			p2.setInt(1, valuereturn-1);
			p2.setString(2, callno);
			
			   
			   updatereturned=p2.executeUpdate();
			                }
		
		            return updatereturned;
		  
		
		}catch(Exception e)
		{
			
			System.out.println(e.getMessage());
		}
		return updatereturned;
	}
	
	public static int getIssued(String callno){
		int issued=0;
		try{
			Connection con= Dbconnection.jdbcconnection();
			PreparedStatement ps=con.prepareStatement("select * from e_book where callno=?");
			ps.setString(1,callno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				issued=rs.getInt("issued");
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return issued;
	}
	
	
	
	public static int issueBook(IssueBookBean bean) {
		// TODO Auto-generated method stub
		try
		{
			Connection con= Dbconnection.jdbcconnection();
		  String callno =bean.getCallno();
		      boolean result=checkissue(callno);
		      if(result)
		      {
		    	  int status=0;
					try{
						
						PreparedStatement ps=con.prepareStatement("insert into e_issuebook values(?,?,?,?,?,?)");
						ps.setString(1,bean.getCallno());
						ps.setString(2,bean.getStudentid());
						ps.setString(3,bean.getStudentname());
						ps.setLong(4,bean.getStudentmobile());
						java.sql.Date currentDate=new java.sql.Date(System.currentTimeMillis());
						ps.setDate(5,currentDate);
						ps.setString(6,"no");
						
						status=ps.executeUpdate();
						if(status>0){
							PreparedStatement ps2=con.prepareStatement("update e_book set issued=? where callno=?");
							ps2.setInt(1,getIssued(callno)+1);
							ps2.setString(2,callno);
							status=ps2.executeUpdate();
						}
						con.close();
						
					}catch(Exception e){System.out.println(e);}
					
					return status; 
		      }
		      else{
					return 0;
				}
		      
		  
		}	
		catch(Exception e)
		{
			e.getMessage();
		}
		
		return 0;
	}
}
