package showEDU.com.web.member.model;

public class LoginMember {
	
	String userId; 
	String password;
	String invalidCredentials;
	boolean rememberMe;
	
	
	MemberBean memberBean;

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public LoginMember(String userId, String password, boolean rememberMe) {
		super();
		this.userId = userId;
		this.password = password;
		this.rememberMe = rememberMe;
	
	}

	public LoginMember() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInvalidCredentials() {
		return invalidCredentials;
	}

	public void setInvalidCredentials(String invalidCredentials) {
		this.invalidCredentials = invalidCredentials;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public String toString() {
		return "LoginMember [userId=" + userId + ", password=" + password + ", invalidCredentials=" + invalidCredentials
				+ ", rememberMe=" + rememberMe + ", memberBean=" + memberBean + "]";
	}
	
}