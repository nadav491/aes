CREATE TABLE question(code varchar(5), owner varchar(100), body varchar(256), answer1 varchar(256), answer2 varchar(256),answer3 varchar(256),answer4 varchar(256), 
correct int, courseList varchar(256), instruction varchar(100),PRIMARY KEY (code));
insert into question value("02001","roie","Where was the fortune cookie actually invented?","China","japan","korea","America",4,"",""); 
insert into question value("02002","gal","When a burning cigarette is inhaled, what is the temperature at its tip?","50","300","700","1000",3,"",""); 
insert into question value("02101","roie","what is the ultimate answer of life the universe and everything?","i think therefore i am","42","god","life is a lie ",2,"",""); 
insert into question value("04007","roie","When a burning cigarette is inhaled, what is the temperature at its tip?","50","300","700","1000",3,"","");
select * from question; 

CREATE TABLE test(code varchar(6), Qs_id varchar(1024), Qs_grades varchar(1024), CommentsForTeacher varchar(256), CommentsForStudent varchar(256), TestOwner varchar(64), TestTime varchar(16), 
PRIMARY KEY (code));
insert into test value("021202", "0200102101", "050050", "1", "1", "roie", "2");
select * from test;

CREATE TABLE user(id varchar(10),password varchar(20), type varchar(10),login boolean, Uname varchar(256), PRIMARY KEY (id));
insert into user value("111111111","123","Student",false,"gal");
insert into user value("111111112","1234","Student",false,"ronny");
insert into user value("111111113","1233","Student",false,"gal");
insert into user value("111111114","1235","Student",false,"yossi");
insert into user value("123","123","Student",false,"yossi");
insert into user value("1","1","Teacher",false,"roie");
insert into user value("333333333","1235","Manager",false,"shaked");
select * from user;


create table student(id varchar(10), tests_id varchar(1024), tests_grades varchar(1024), PRIMARY KEY (id));
insert into student value("yossi", null, null);
select * from student;

create table studentTest(id_code varchar(20), id varchar(10), test_code varchar(6), test_answers varchar(1024), test_grade varchar(3), cheat boolean,  checked boolean, CommentsForStudent varchar(256), key(id_code));
select * from studentTest;


drop table question;
drop table test;
drop table user;
drop table studentTest;
drop table student;


