create database thread;
use thread;
create table userTbl (
                         id int primary key auto_increment,
                         name varchar(30),
                         emaill_address varchar(50)
)
select * from userTbl;
ALTER TABLE userTbl CHANGE emaill_address email_address varchar(30);

