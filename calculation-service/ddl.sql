-- create database
create database user_db;
-- use database
use user_db;
-- create table user
create table user (
user_id bigint NOT NULL auto_increment primary key,
first_name varchar(20) NOT NULL,
last_name varchar(20) NOT NULL,
username varchar(20) NOT NULL UNIQUE,
password varchar(64) NOT NULL,
email varchar(254) NOT NULL,
created_time bigint NOT NULL
);
-- create table user
create table user_audit (
user_id int  NOT NULL,
active_at int NOT NULL,
token varchar(128) NOT NULL
);
