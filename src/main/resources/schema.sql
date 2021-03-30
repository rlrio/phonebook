drop table if exists contacts;
drop table if exists users;

create table users(
id int auto_increment primary key,
first_name varchar(255) not null,
last_name varchar(255)
);

create table contact(
id int auto_increment primary key,
first_name varchar(255) not null,
last_name varchar(255),
phone varchar(16) not null,
users_id int references users (id)
);