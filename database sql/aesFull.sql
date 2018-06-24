CREATE TABLE question(code varchar(5), owner varchar(100), body varchar(256), answer1 varchar(256), answer2 varchar(256),answer3 varchar(256),answer4 varchar(256), correct int, courseList varchar(256),Sinstructions varchar(100) , Tinstructions varchar(100),PRIMARY KEY (code));
insert into question value("02001","roie","Where was the fortune cookie actually invented?","China","japan","korea","America",4,"","","");
insert into question value("02002","roie","When a burning cigarette is inhaled, what is the temperature at its tip?","50","300","700","1000",3,"","","");
insert into question value("04001","roie","what is the ultimate answer of life the universe and everything?","i think therefore i am","42","god","life is a lie ",2,"","","");
insert into question value("04002","roie","When a burning cigarette is inhaled, what is the temperature at its tip?","50","300","700","1000",3,"","","");

CREATE TABLE test(code varchar(6), Qs_id varchar(1024), Qs_grades varchar(1024), CommentsForTeacher varchar(256), CommentsForStudent varchar(256), TestOwner varchar(64), TestTime varchar(16),PRIMARY KEY (code));
insert into test value("021202", "0200102002", "050050", "1", "1", "roie", "2");
insert into test value("041401", "0400104002", "044056", "2", "2", "roie", "11");

CREATE TABLE user(id varchar(10),password varchar(20), type varchar(10),login boolean, Uname varchar(256), PRIMARY KEY (id));
insert into user value("123","123","Student",false,"yossia");
insert into user value("1","1","Teacher",false,"roie");
insert into user value("12","12","Teacher",false,"moshe");
insert into user value("2","2","Manager",false,"shaked");
insert into user value("3","3","Student",false,"yossid");

create table student(id varchar(10), tests_id varchar(1024), tests_grades varchar(1024), PRIMARY KEY (id));
insert into student value("yossia","","");
insert into student value("yossid","","");

create table studentTest(id_code varchar(20), id varchar(10), test_code varchar(6), test_answers varchar(1024), test_grade varchar(3), cheat boolean,  checked boolean, CommentsForStudent varchar(256),PRIMARY key(id_code));

create table course(code varchar(20),name varchar(256), PRIMARY key(code));
insert into course value("02", "Physics");
insert into course value("03", "Algebra1");
insert into course value("04", "History");
insert into course value("05", "English");

create table subject(code varchar(20),name varchar(256), PRIMARY key(code));
insert into subject value("02", "Physics");
insert into subject value("03", "Algebra1");
insert into subject value("04", "History");
insert into subject value("05", "English");




