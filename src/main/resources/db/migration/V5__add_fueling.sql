create table if not exists fueling
(
    id serial primary key,
    milage float,
    car_id int,
    volume float,
    price float,
    foreign key (car_id) references car (id)
)