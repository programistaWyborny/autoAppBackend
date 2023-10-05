CREATE TABLE IF NOT EXISTS account
(
    username varchar(32) primary key,
    password varchar(32)
);

CREATE TABLE IF NOT EXISTS car
(
    id    serial primary key,
    name  varchar(32),
    brand  varchar(32),
    model varchar(32),
    year  int,
    description varchar(32),
    username varchar(32),
    foreign key (username) REFERENCES account (username)
);
