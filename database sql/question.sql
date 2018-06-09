CREATE TABLE question(code varchar(5), owner varchar(100), body varchar(256), answer1 varchar(256), answer2 varchar(256),answer3 varchar(256),answer4 varchar(256), correct int, courseList varchar(256), instruction varchar(100),PRIMARY KEY (code));
insert into question value("11111","sharon","Where was the fortune cookie actually invented?","China","japan","korea","America",4,"","");
insert into question value("22222","gal","When a burning cigarette is inhaled, what is the temperature at its tip?","50","300","700","1000",3,"","");
insert into question value("33333","ziv","what is the ultimate answer of life the universe and everything?","i think therefore i am","42","god","life is a lie ",2,"","");
insert into question value("22223","gal","When a burning cigarette is inhaled, what is the temperature at its tip?","50","300","700","1000",3,"","");
select * from question;
SELECT * FROM question WHERE owner="gal";
UPDATE question Set code='22222', owner='gal', body='When a burning cigarette is inhaled, what is the temperature at its tip?',answer1='50' ,answer2='300' ,answer3='700' ,answer4='1000' ,correct=2, courseList='', instruction='' WHERE code='22222';