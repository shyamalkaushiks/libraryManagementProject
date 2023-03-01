package in.inueron.Beans;

public class LibrarianBean {
	private int id;
	private String name,email,password;
	private String mobile;

	public LibrarianBean()
	{
		
	}
	public LibrarianBean(int id2, String name2, String email2, String password2, String smobile)
	{
		super();
		this.id=id2;
		this.name = name2;
		this.email = email2;
		this.password = password2;
		this.mobile = smobile;
	}


	public LibrarianBean(String name, String email, String password, String smobile) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = smobile;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	@Override
	public String toString() {
		return "LibrarianBean [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", mobile=" + mobile + "]";
	}
	
}
