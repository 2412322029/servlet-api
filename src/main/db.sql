
create database if not exists servletdb;

use servletdb;

drop table if exists datas;
create table datas (
                      Id int primary key auto_increment,
                      title varchar(1024),
                      content mediumtext,
                      userId int,
                      postTime datetime
);


insert into datas values(null,'标题一','今天开始学Java了!',1,now());
insert into datas values(null,'标题二','昨天开始学Java!',1,now());
insert into datas values(null,'标题三','明天开始学Java',1,now());
insert into datas values(null,'标题一','今天开始学C++',2,now());
insert into datas values(null,'标题二','明天开始学C++',2,now());

-- 创建用户表
drop table if exists user;
create table user (
                      userId int primary key auto_increment,
                      username varchar(128) unique,
                      password varchar(128)
);


insert into user values(null,'admin','123456');
insert into user values(null,'user','123456');
