create table if not exists milage
(
    id serial primary key,
    milage float,
    car_id int,
    foreign key (car_id) references car (id)
)