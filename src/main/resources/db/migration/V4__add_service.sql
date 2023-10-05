create table if not exists service
(
    id serial primary key,
    content varchar(100),
    price float,
    milage float,
    car_id int,
    foreign key (car_id) references car (id)
)