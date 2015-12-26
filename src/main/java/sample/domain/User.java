package sample.domain;

public class User {

	private String fullName;
	private String lastName;
	private String uid;
	private String password;
	
	public User(){}
	
	public User(String uid) {
		this.uid = uid;
	}
	
	public User(String fullName, String lastName, String uid, String pwd) {
		this.fullName = fullName;
		this.lastName = lastName;
		this.uid = uid;
		this.password = pwd;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
