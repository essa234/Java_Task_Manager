create table user
(
    user_id long not null auto_increment primary key,
    email varchar(255) not null,
    password varchar(255) not null,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    value varchar(255) not null,
    role varchar(255) not null
);