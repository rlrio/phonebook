drop table if exists contacts;
drop table if exists users;

create table users(
id serial primary key,
first_name varchar(255) not null,
last_name varchar(255)
);

create table contacts(
id serial primary key,
first_name varchar(255) not null,
last_name varchar(255),
phone varchar(16) not null,
user_id int references users(id)
);