create table if not exists note
(
    id serial primary key,
    content varchar(100),
    milage float,
    car_id int,
    foreign key (car_id) references car (id)
)