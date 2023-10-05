create table if not exists user_car
(
    id serial primary key,
    allowed_username varchar(32) not null,
    shared_car_id int not null,
    foreign key (allowed_username) references users (username),
    foreign key (shared_car_id) references car (id)
)