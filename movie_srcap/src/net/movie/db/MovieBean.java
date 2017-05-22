package net.movie.db;

import java.util.ArrayList;
import java.util.Date;

public class MovieBean {
	private int ms_no;				//no
	private String mb_id;			//id session으로 받아올 id 값
	private String ms_title;		//title 한글명
	private String ms_subtitle;		//subtitle 영어명
	private String ms_director;		//director 감독
	private String ms_poster;		//poster
	private int ms_myRating;		//myRating
	private String ms_review;		//작성된 리뷰 
	private Date ms_regdate;		//스크랩 날짜
	
	private int ms_cnt; 
	
	private String ms_rating;		//등급
	private String ms_seq;
	private String ms_id;
	
	
	
	
	
	
	
	
	public int getMs_cnt() {
		return ms_cnt;
	}
	public void setMs_cnt(int ms_cnt) {
		this.ms_cnt = ms_cnt;
	}
	public String getMs_poster() {
		return ms_poster;
	}
	public void setMs_poster(String ms_poster) {
		this.ms_poster = ms_poster;
	}
	public String getMs_rating() {
		return ms_rating;
	}
	public void setMs_rating(String ms_rating) {
		this.ms_rating = ms_rating;
	}
	public String getMs_seq() {
		return ms_seq;
	}
	public void setMs_seq(String ms_seq) {
		this.ms_seq = ms_seq;
	}
	public String getMs_id() {
		return ms_id;
	}
	public void setMs_id(String ms_id) {
		this.ms_id = ms_id;
	}
	public int getMs_myRating() {
		return ms_myRating;
	}
	public void setMs_myRating(int ms_myRating) {
		this.ms_myRating = ms_myRating;
	}
	
	public int getMs_no() {
		return ms_no;
	}
	public void setMs_no(int ms_no) {
		this.ms_no = ms_no;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMs_title() {
		return ms_title;
	}
	public void setMs_title(String ms_title) {
		this.ms_title = ms_title;
	}
	public String getMs_subtitle() {
		return ms_subtitle;
	}
	public void setMs_subtitle(String ms_subtitle) {
		this.ms_subtitle = ms_subtitle;
	}
	public String getMs_director() {
		return ms_director;
	}
	public void setMs_director(String ms_director) {
		this.ms_director = ms_director;
	}



	public String getMs_review() {
		return ms_review;
	}
	public void setMs_review(String ms_review) {
		this.ms_review = ms_review;
	}
	public Date getMs_regdate() {
		return ms_regdate;
	}
	public void setMs_regdate(Date ms_regdate) {
		this.ms_regdate = ms_regdate;
	}
	@Override
	public String toString() {
		return "MovieBean [ms_no=" + ms_no + ", mb_id=" + mb_id + ", ms_title=" + ms_title + ", ms_subtitle="
				+ ms_subtitle + ", ms_director=" + ms_director + ", ms_poster=" + ms_poster + ", ms_myRating="
				+ ms_myRating + ", ms_review=" + ms_review + ", ms_regdate=" + ms_regdate + ", ms_rating=" + ms_rating
				+ ", ms_seq=" + ms_seq + ", ms_id=" + ms_id + "]";
	}



}
