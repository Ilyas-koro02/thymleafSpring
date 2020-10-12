create sequence hibernate_sequence start with 1 increment by 1;

create table users
(
    id         serial not null,
    first_name varchar(255),
    last_name  varchar(255),
    number     bigint
);

create table admin
(
    id          serial not null,
    name        varchar(255),
    login       varchar(255),
    password_admin    varchar(255)
);

INSERT INTO admin(name, login, password_admin) VALUES ('user123', 'koro', '2002');