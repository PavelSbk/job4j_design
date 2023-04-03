create table animal (
	id serial primary key,
	name varchar(255)
);

insert into animal
values
(1, 'Boar'),
(2, 'Lion'),
(3, 'Alligator'),
(4, 'Ostrich'),
(5, 'Grizzly');

create table animal_class (
	id serial primary key,
	class_name varchar(255)
);

insert into animal_class
values
(1, 'Mammalia'),
(2, 'Reptilia'),
(3, 'Aves');

create table living_area (
	id	serial primary key,
	area varchar(255)
);

insert into living_area
values
(1,	'wood'),
(2,	'mountains'),
(3,	'savannas'),
(4,	'rivers'),
(5,	'lakes');

create table cage_number (
	id serial primary key,
	number int
);

insert into cage_number
values
(1, 101),
(2, 212),
(3, 323),
(4, 456),
(5, 589);

create table animals (
	id serial primary key,
	name varchar(255),
	class int references animal_class(id),
	cage int references cage_number(id) unique
);

insert into animals
values
(1, 'Boar', 1, 1),
(2, 'Lion', 2, 2),
(3, 'Alligator', 3, 3),
(4, 'Ostrich', 3, 4),
(5, 'Grizzly,', 1, 5);

create table animal_areas (
	id serial primary key,
	animal int references animal(id),
	area int references living_area(id)
);

insert into animal_areas(animal, area)
values
(1, 1),
(1, 3),
(2, 2),
(3, 4),
(3, 5),
(4, 3),
(5, 1),
(5, 2),
(5, 4)
