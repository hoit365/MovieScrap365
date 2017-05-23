package net.admin.db;

import java.util.ArrayList;

public class AdminBean {

	//데이터 전송 영역 변수 선언 시작
	private String NUM;
	private String MB_ID;
	private String MB_PW;
	private String MB_NAME;
	private String MB_BIRTH;
	private String MB_GENDER;
	private String MB_PH;
	private String MB_EMAIL;
	private String MB_REGDATE;
	private String MB_MANAGER;
	private String MB_STAT;
	//데이터 전송 영역 변수 선언 종료
	
	//페이지 영역 변수 선언 시작
	private int page;
	private int countList;
	private int countPage;
	private int totalCount;
	private int totalPage;
	private int startPage;
	private int endPage;
	//페이지 영역 변수 선언 종료
	
	//데이터 전송 영역 메소드 선언 시작
	public String getMB_ID() {
		return MB_ID;
	}

	public String getNUM() {
		return NUM;
	}

	public void setNUM(String nUM) {
		NUM = nUM;
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

	public String getMB_REGDATE() {
		return MB_REGDATE;
	}

	public void setMB_REGDATE(String mB_REGDATE) {
		MB_REGDATE = mB_REGDATE;
	}

	public String getMB_MANAGER() {
		return MB_MANAGER;
	}

	public void setMB_MANAGER(String mB_MANAGER) {
		MB_MANAGER = mB_MANAGER;
	}

	public String getMB_STAT() {
		return MB_STAT;
	}

	public void setMB_STAT(String mB_STAT) {
		MB_STAT = mB_STAT;
	}
	//데이터 전송 영역 메소드 선언 종료

	//페이지 영역 메소드 선언 시작
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCountList() {
		countList = 5;
		return countList;
	}

	public int getCountPage() {
		countPage=5;
		return countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	//페이지 영역 메소드 선언 종료

	@Override
	public String toString() {
		return "AdminBean [NUM=" + NUM + ", MB_ID=" + MB_ID + ", MB_PW=" + MB_PW + ", MB_NAME=" + MB_NAME
				+ ", MB_BIRTH=" + MB_BIRTH + ", MB_GENDER=" + MB_GENDER + ", MB_PH=" + MB_PH + ", MB_EMAIL=" + MB_EMAIL
				+ ", MB_REGDATE=" + MB_REGDATE + ", MB_MANAGER=" + MB_MANAGER + ", MB_STAT=" + MB_STAT + ", page="
				+ page + ", countList=" + countList + ", countPage=" + countPage + ", totalCount=" + totalCount
				+ ", totalPage=" + totalPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	
}