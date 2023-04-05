DROP TABLE if EXISTS devices_people;
DROP TABLE if EXISTS people;
DROP TABLE if EXISTS devices;

CREATE TABLE devices(
    id serial PRIMARY KEY,
    name varchar(255),
    price float
);

CREATE TABLE people(
    id serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE devices_people(
    id serial PRIMARY KEY,
    device_id int REFERENCES devices(id),
    people_id int REFERENCES people(id)
);

INSERT INTO devices(name, price) VALUES
('D1', 1234.3),
('D2', 2345.4),
('D3', 34414.5),
('D4', 41434.6),
('D5', 56.7);

INSERT INTO people(name) VALUES
('Ca Cd'),
('Dc Kl'),
('Fd Rc'),
('Cy Cg'),
('Ac Dc');

INSERT INTO devices_people(device_id, people_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5)

/* Используя агрегатные функции вывести среднюю цену устройств. */
SELECT avg(price) as avg_price FROM devices

/* Используя группировку вывести для каждого человека среднюю цену его устройств */
SELECT people.name, AVG(devices.price)
FROM people
JOIN devices_people dp ON dp.people_id = people.id
JOIN devices ON devices.id = dp.device_id
GROUP BY people.name

/* И c условием, что средняя стоимость устройств должна быть больше 5000 */
SELECT people.name, AVG(devices.price)
FROM people
JOIN devices_people dp ON dp.people_id = people.id
JOIN devices ON devices.id = dp.device_id
GROUP BY people.name
HAVING AVG(devices.price) > 5000;
