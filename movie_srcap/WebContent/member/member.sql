CREATE TABLE member
(
	mb_id varchar2(30) NOT NULL ,
	mb_pw varchar2(100) NOT NULL,
	mb_name varchar2(10) NOT NULL,
	mb_birth date NOT NULL,
	mb_gender varchar2(10) NOT NULL,
	mb_ph varchar2(15) NOT NULL,
	mb_email varchar2(100) NOT NULL,
	mb_regdate date NOT NULL,
    MB_MANAGER varchar2(20) NOT NULL,
    MB_STAT varchar2(20) NOT NULL,
	PRIMARY KEY (MB_ID)
);

show parameter processes;

alter system set processes=150 scope=spfile;
shutdown immediate;
startup

select * from MEMBER;


insert into member values('admin','admin','관리자','2017-05-18','male','01033063382','mmm@mmm.com','2017-05-18','yes','active');

select MB_ID, MB_PW, MB_NAME, MB_BIRTH, MB_GENDER, MB_PH, MB_EMAIL FROM MEMBER WHERE MB_ID = 'jsm8778' and MB_PW = 'wjdtnals77';

select MB_NAME, MB_ID, MB_EMAIL, MB_BIRTH, MB_GENDER, MB_PH FROM MEMBER where MB_ID = 'jsm8778';

update MEMBER set MB_PH = '01033063382' where MB_ID='jsm8778';

select MB_ID, MB_PH, MB_name from MEMBER WHERE MB_NAME='정수민' and MB_PH='01033063382';

select MB_NAME, MB_PH, MB_ID from MEMBER where MB_NAME = '정수민' and MB_PH = '01033063382';

select MB_ID from MEMBER where MB_ID='12345678';

delete member where MB_NAME = '김남형';