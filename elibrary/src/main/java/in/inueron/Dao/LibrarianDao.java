package in.inueron.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.inueron.Beans.LibrarianBean;
import in.inueron.Util.Dbconnection;

public class LibrarianDao {
	Connection connection = null;
	int status;

	public int save(LibrarianBean bean) {
		try {
			connection = Dbconnection.jdbcconnection();
			if (connection != null) {
				PreparedStatement ps = connection
						.prepareStatement("insert into e_librarian(name,email,password,mobile) values(?,?,?,?)");
				ps.setString(1, bean.getName());
				ps.setString(2, bean.getEmail());
				ps.setString(3, bean.getPassword());
				ps.setString(4, bean.getMobile());
				status = ps.executeUpdate();
				return 1;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public List<LibrarianBean> view() {
		List<LibrarianBean> list1 = new ArrayList();
		try {
			connection = Dbconnection.jdbcconnection();
			if (connection != null) {
				String sql = "select * from e_librarian";
				PreparedStatement pre = connection.prepareStatement(sql);
				ResultSet re = pre.executeQuery();
				while (re.next()) {
					LibrarianBean lbean = new LibrarianBean();
					lbean.setName(re.getString("name"));
					lbean.setId(re.getInt("id"));
					lbean.setEmail(re.getString("email"));
					lbean.setMobile(re.getString("mobile"));
					lbean.setPassword(re.getString("password"));
					list1.add(lbean);
				}

			}
			return list1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public int delete(int id) {
		try {
			String sql = "delete from e_librarian where id=?";
			connection = Dbconnection.jdbcconnection();
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			int stat = pre.executeUpdate();
			return stat;

		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

	public LibrarianBean viewById(int id) {
		try {
			LibrarianBean lbean = new LibrarianBean();

			String sql = "select * from e_librarian where id=?";
			connection = Dbconnection.jdbcconnection();
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			ResultSet sre = pre.executeQuery();
			if (sre.next()) {
				lbean.setName(sre.getString("name"));
				lbean.setEmail(sre.getString("email"));
				lbean.setMobile(sre.getString("mobile"));
				lbean.setPassword(sre.getString("password"));
				lbean.setId(sre.getInt("id"));
				return lbean;
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}


	
		public  int update(LibrarianBean bean){
	      int status = 0;
			try{
				connection = Dbconnection.jdbcconnection();
				PreparedStatement ps=connection.prepareStatement("update e_librarian set name=?,email=?,password=?,mobile=? where id=?");
				ps.setString(1,bean.getName());
				ps.setString(2,bean.getEmail());
				ps.setString(3,bean.getPassword());
				ps.setString(4,bean.getMobile());
				ps.setInt(5,bean.getId());
				System.out.println(bean.getId());
				status=ps.executeUpdate();
				
				connection.close();
				return status;
				
			}catch(Exception e)
			{
				System.out.println(e);
			}
			return 3;
			
			
		}
	
		public boolean authenciate(String email,String password)
		{
			try
			{
			connection = Dbconnection.jdbcconnection();
			String sql="Select * from e_librarian where email=? && password=?";
			PreparedStatement pre=connection.prepareStatement(sql);
			pre.setString(1, email);
			pre.setString(2, password);
			ResultSet status=pre.executeQuery();
			if(status.next())
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
			}catch(Exception e)
			{
				e.getMessage();
			}
			return false;
			
		}
	}

