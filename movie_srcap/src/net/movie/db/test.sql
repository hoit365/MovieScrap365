create table MovieScrap(
	ms_no number,
	mb_id varchar2(15),
	ms_title varchar2(50),
	ms_subtitle varchar2(50),
	ms_director varchar2(30),
	ms_poster varchar2(30),
	ms_userRating number,
	ms_myRating number,
	ms_review varchar2(2000),
	ms_regdate date,
	ms_rating varchar(15),
	ms_seq varchar2(15),
	ms_id varchar2(15),	
	primary key (mb_id)
);

drop table MovieScrap purge;
select * from MovieScrap;



CREATE SEQUENCE SEQ_member_num INCREMENT BY 1 START WITH 1;

CREATE TABLE member
(
	mb_num number(7,0) NOT NULL,
	mb_id varchar2(30) NOT NULL UNIQUE,
	mb_pw varchar2(100) DEFAULT '' NOT NULL,
	mb_name varchar2(10) NOT NULL,
	mb_birth date NOT NULL,
	mb_gender varchar2(10) NOT NULL,
	mb_ph varchar2(10) NOT NULL,
	mb_email varchar2(100) NOT NULL,
	mb_regdate date NOT NULL,
	PRIMARY KEY (mb_num)
);



insert into MEMBER VALUES 
(SEQ_member_num.nextval, 'jsm8778','134679','정수민','1990-12-01', '남자', 
'01033063382', 'jsm8778@gmail.com',sysdate);

insert into MEMBER VALUES 
(SEQ_member_num.nextval, 'soldesk','134679','솔데스','1990-12-01', '남자', 
'01012341234', 'jsm8778@gmail.com',sysdate);

insert into MEMBER VALUES 
(SEQ_member_num.nextval, 'nor','134679','솔데','1990-12-01', '남자', 
'01012341234', 'jsm8778@gmail.com',sysdate);

select * from MEMBER;

select MB_NAME, MB_ID, MB_EMAIL, MB_BIRTH, MB_GENDER, MB_PH FROM MEMBER where MB_ID = 'jsm8778';

update MEMBER set MB_PH = '01033063382' where MB_ID='jsm8778';

select MB_ID, MB_PH, MB_name from MEMBER WHERE MB_NAME='정수민' and MB_PH='01033063382';

select MB_NAME, MB_PH, MB_ID from MEMBER where MB_NAME = '정수민' and MB_PH = '01033063382';

select MB_ID from MEMBER where MB_ID='12345678';

delete member where MB_NAME = '김남형';