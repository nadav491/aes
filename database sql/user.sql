CREATE TABLE user(id varchar(10),password varchar(20), type varchar(10),login boolean, Uname varchar(256), PRIMARY KEY (id));
insert into user value("111111111","123","Student",false,"gal");
insert into user value("111111112","1234","Student",false,"ronny");
insert into user value("111111113","1233","Student",false,"gal");
insert into user value("111111114","1235","Student",false,"yossi");
insert into user value("123","123","Student",false,"yossi");

insert into user value("1","1","Teacher",false,"roie");
insert into user value("222","1234","Teacher",false,"roie");
insert into user value("333333333","1235","Manager",false,"shaked");

UPDATE `aes`.`user` SET `login` = '0' WHERE (`id` = '123');
select * from user;question