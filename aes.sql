CREATE TABLE question(code varchar(5), owner varchar(100), body varchar(256), answer1 varchar(256), answer2 varchar(256),answer3 varchar(256),answer4 varchar(256), 
correct int, courseList varchar(256), instruction varchar(100),PRIMARY KEY (code));
insert into question value("11111","sharon","Where was the fortune cookie actually invented?","China","japan","korea","America",4,"",""); 
insert into question value("22222","gal","When a burning cigarette is inhaled, what is the temperature at its tip?","50","300","700","1000",3,"",""); 
insert into question value("33333","ziv","what is the ultimate answer of life the universe and everything?","i think therefore i am","42","god","life is a lie ",2,"",""); 
insert into question value("44444","gal","When a burning cigarette is inhaled, what is the temperature at its tip?","50","300","700","1000",3,"","");
select * from question; 

CREATE TABLE test(code varchar(6), Qs_id varchar(1024), Qs_grades varchar(1024), CommentsForTeacher varchar(256), CommentsForStudent varchar(256), TestOwner varchar(64), TestTime varchar(16), 
PRIMARY KEY (code));
insert into test value("119999", "222223333344444","100100020",null, null, "teacher1", "180");
insert into test value("118888", "111113333344444","9010020",null, null, "teacher1", "180");
insert into test value("117777", "111113333344444","9010020",null, null, "teacher2", "180");
insert into test value("116666", "111113333344444","9010020",null, null, "teacher2", "180");
insert into test value("115555", "111113333344444","9010020",null, null, "teacher3", "180");
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
insert into studentTest value("111111111119999","111111111","119999", "431", "92");
insert into studentTest value("111111112119999","111111112","119999", "431", "100");
insert into studentTest value("111111114119999","111111112","119999", "431", "80");
insert into studentTest value("111111111118888","111111112","118888", "431", "70");
insert into studentTest value("111111112118888","111111112","118888", "431", "60");
insert into studentTest value("111111114116666","111111114","116666", "431", "50");
insert into studentTest value("111111114117777","111111114","117777", "431", "99");
insert into studentTest value("111111113115555","111111113","115555", "431", "16");
select * from studentTest;

create table course(serial varchar(2), primary key(serial));
insert into course value ("11");
insert into course value ("22");
insert into course value ("33");
insert into course value ("44");

select test_code, avg(test_grade)	/* returns the average grade of all tests with the same code */
from studentTest
group by test_code;

select id, avg(test_grade)	/* returns the average grade of all tests done by the same student */
from studenTtest
group by id;

select testOwner, avg(test_grade) /* returns the average grade of all tests created by same teacher */
from test, studentTest
where test.code = studentTest.test_code
group by testOwner;

create table teacher (id varchar(16), foreign key(id) references user(id));
insert into teacher value("teacher1");
insert into teacher value("teacher2");
insert into teacher value("teacher3");
insert into teacher value("teacher4");


/*
drop table teacher;
drop table question;
drop table test;
drop table user;
drop table studentTest;
drop table student;
*/