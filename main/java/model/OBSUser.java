package model;

public class OBSUser {
	private String user_id, user_pwd, user_type;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public OBSUser() {
	}

	public OBSUser(String user_id) {
		this.user_id = user_id;
	}

	public OBSUser(String user_id, String user_pwd, String user_type) {
		super();
		this.user_id = user_id;
		this.user_pwd = user_pwd;
		this.user_type = user_type;
	}

}
