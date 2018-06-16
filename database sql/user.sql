CREATE TABLE user(id varchar(10),password varchar(20), type varchar(10),login boolean, PRIMARY KEY (id));
insert into user value("111111111","123","student",false);
insert into user value("111111112","1234","student",false);
insert into user value("111111113","1233","student",false);
insert into user value("111111114","1235","student",false);

insert into user value("222222222","1234","teacher",false);
insert into user value("333333333","1235","manager",false);
select * from user;