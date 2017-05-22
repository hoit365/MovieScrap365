package net.member.db;

public class MemberBean {
	private String MB_ID;
	private String MB_PW;
	private String MB_NAME;
	private String MB_BIRTH;
	private String MB_GENDER;
	private String MB_PH;
	private String MB_EMAIL;
	public String getMB_ID() {
		return MB_ID;
	}
	public void setMB_ID(String mB_ID) {
		MB_ID = mB_ID;
	}
	public String getMB_PW() {
		return MB_PW;
	}
	public void setMB_PW(String mB_PW) {
		MB_PW = mB_PW;
	}
	public String getMB_NAME() {
		return MB_NAME;
	}
	public void setMB_NAME(String mB_NAME) {
		MB_NAME = mB_NAME;
	}
	public String getMB_BIRTH() {
		return MB_BIRTH;
	}
	public void setMB_BIRTH(String mB_BIRTH) {
		MB_BIRTH = mB_BIRTH;
	}
	public String getMB_GENDER() {
		return MB_GENDER;
	}
	public void setMB_GENDER(String mB_GENDER) {
		MB_GENDER = mB_GENDER;
	}
	public String getMB_PH() {
		return MB_PH;
	}
	public void setMB_PH(String mB_PH) {
		MB_PH = mB_PH;
	}
	public String getMB_EMAIL() {
		return MB_EMAIL;
	}
	public void setMB_EMAIL(String mB_EMAIL) {
		MB_EMAIL = mB_EMAIL;
	}
	@Override
	public String toString() {
		return "MemberBean [MB_ID=" + MB_ID + ", MB_PW=" + MB_PW + ", MB_NAME=" + MB_NAME + ", MB_BIRTH=" + MB_BIRTH
				+ ", MB_GENDER=" + MB_GENDER + ", MB_PH=" + MB_PH + ", MB_EMAIL=" + MB_EMAIL + "]";
	}
	
	
	
}