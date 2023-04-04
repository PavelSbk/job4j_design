create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date)
values
('chimpanzee', 14600, '1758-01-01'),
('kangaroo', 2555, '1792-01-01'),
('seal', 9900, '1758-02-02'),
('dolphin', 8400, null),
('salmon_fish', 1450, null),
('clownfish', 2900, '1853-03-03'),
('whale', 29200, null);

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';
