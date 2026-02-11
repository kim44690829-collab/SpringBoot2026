-- DB springBootDB
create database springBootDB;
use springBootDB;

-- user_member
create table user_member(
  no int auto_increment primary key,
  id varchar(20) not null,
  pw varchar(100) not null,
  mail varchar(50) not null,
  phone varchar(50) not null,
  reg_date datetime default now(),
  mod_date datetime default now()
);

-- board
create table board(
num int auto_increment primary key,
writer varchar(20),
subject varchar(30),
writerPw varchar(20),
reg_date datetime default now(),
readcount int default 0,
content varchar(1000)
);