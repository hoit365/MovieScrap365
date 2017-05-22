create table MovieScrap(
	ms_no number,
	mb_id varchar2(15),
	ms_title varchar2(100),
	ms_director varchar2(30),
	ms_poster varchar2(500),
	ms_regdate date,
	ms_rating varchar(50),3
	ms_seq varchar2(15),
	ms_id varchar2(15),	
	primary key (ms_no)
);

alter table MovieScrap modify(ms_title varchar2(100));

drop table MovieScrap purge;

delete from MovieScrap;

select * from member_board;

select * from MovieScrap;

select * from MovieScrap where mb_id='namhy';
select count(*) from MovieScrap where mb_id='hoit159';
select * from (select rownum rnum, ms_no, mb_id, ms_title, ms_director,ms_poster, ms_regdate, ms_rating, ms_seq, ms_id from (select * from Moviescrap where mb_id='hoit159')) where rnum>=1 and rnum<=100;
select * from (select ms_no, mb_id, ms_title, ms_director,ms_poster, ms_regdate, ms_rating, ms_seq, ms_id from (select * from Moviescrap)) where mb_id='namhy';


select ms_title, count(*) as cnt from MovieScrap group by ms_title order by count(*) desc;





select * from seq;

select * from MovieReview;
select count(*) from MovieReview where mb_id='namhy' and ms_seq=35443 and ms_id='F';
drop table MovieReview purge;
delete from MovieReview;
delete from MovieReview where mb_id='namhy';

create table MovieReview(
	ms_no number,
	ms_title varchar2(50),
	mb_id varchar2(15),
	ms_myRating number,
	ms_review varchar2(1000),
	ms_seq varchar2(15),
	ms_id varchar2(15),
	ms_regdate date,
	primary key (ms_no)
);

drop table MovieReview purge;



create table MovieRank(
	ms_title varchar2(100),
	ms_poster varchar2(500),
	ms_seq varchar2(15),
	ms_id varchar2(15),
	ms_cnt number,
	primary key (ms_title)
);


select * from MovieRank;
delete from MovieRank;

insert into MovieRank (ms_title, ms_poster, ms_seq, ms_id, ms_cnt) values ('a','b','c','d',1);
