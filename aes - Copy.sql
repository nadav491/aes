CREATE TABLE question(code varchar(5), owner varchar(100), body varchar(256), answer1 varchar(256), answer2 varchar(256),answer3 varchar(256),answer4 varchar(256), 
correct int, courseList varchar(256), instruction varchar(100),PRIMARY KEY (code));
insert into question value("11111","sharon","Where was the fortune cookie actually invented?","China","japan","korea","America",4,"",""); 
insert into question value("22222","gal","When a burning cigarette is inhaled, what is the temperature at its tip?","50","300","700","1000",3,"",""); 
insert into question value("33333","ziv","what is the ultimate answer of life the universe and everything?","i think therefore i am","42","god","life is a lie ",2,"",""); 
insert into question value("44444","gal","When a burning cigarette is inhaled, what is the temperature at its tip?","50","300","700","1000",3,"","");
select * from question; 

CREATE TABLE test(code varchar(6), Qs_id varchar(1024), Qs_grades varchar(1024), CommentsForTeacher varchar(256), CommentsForStudent varchar(256), TestOwner varchar(64), TestTime varchar(16), 
PRIMARY KEY (code));
insert into test value("119999", "222223333344444","100100020",null, null, "Nativ", "180");
insert into test value("118888", "111113333344444","9010020",null, null, "Nativ", "180");
select * from test;

CREATE TABLE User(id varchar(10),password varchar(20), type varchar(10),login boolean, PRIMARY KEY (id)); 
insert into user value("111111111","123","student",false); 
insert into user value("111111112","1234","student",false); 
insert into user value("111111113","1233","student",false); 
insert into user value("111111114","1235","student",false); 
insert into user value("222222222","1234","teacher",false); 
insert into user value("333333333","1235","manager",false); 
select * from user;

create table student(id varchar(10), tests_id varchar(1024), tests_grades varchar(1024), foreign key(id) references user(id));
insert into student value("111111111", null, null);
insert into student value("111111112", null, null);
insert into student value("111111113", null, null); 
insert into student value("111111114", null, null);
insert into student value("222222222", null, null); 
insert into student value("333333333", null, null);
select * from student;

create table studentTest(id_code varchar(20), id varchar(10), test_code varchar(6), test_answers varchar(1024), test_grade varchar(3), foreign key(id) references user(id), foreign key(test_code) references test(code), primary key(id_code));
select * from studentTest;

create table course(serial varchar(2), primary key(serial));
insert into course value ("11");
insert into course value ("22");
insert into course value ("33");
insert into course value ("44");

/*
drop table question;
drop table test;
drop table user;
drop table studentTest;
drop table student;
*/

