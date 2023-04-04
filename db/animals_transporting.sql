drop table if exists animals;
drop table if exists transport_price;

create table transport_price (
  id serial primary key,
  price int,
  package_weight int
);

create table animals (
  id serial primary key,
  name varchar(30),
  weight numeric,
  category int references transport_price(id)
);

insert into transport_price(price, package_weight) values
(1, 10),
(2, 20),
(3, 30);

insert into animals(name, weight, category) values
('Boar', 200.3, 2),
('Bear', 400.1, 3),
('Seal', 60.5, 1);

/* transporting price depending of animal weight + package_weight sum_transport_weight */
select a.name, a.weight, a.category, tp.package_weight + a.weight as sum_transport_weight
from animals as a join transport_price as tp on a.category = tp.id
order by sum_transport_weight asc;

/* transporting price for 1 kM distance transport_price */
select a.name, a.weight, a.category, tp.price * (a.weight + tp.package_weight) as transport_price
from animals as a join transport_price as tp on a.category = tp.id
order by transport_price desc;

/* transporting price for 100 kM distance t_p_d */
select a.name, a.weight, tp.price * (a.weight + tp.package_weight) * 100 as t_p_d
from animals as a join transport_price as tp on a.category = tp.id
order by a.weight asc;