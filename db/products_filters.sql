DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_type;

CREATE TABLE product_type (
	id serial PRIMARY KEY,
	name varchar(30)
);

CREATE TABLE product (
	id serial PRIMARY KEY,
	name varchar(30),
	expired_date date,
	price numeric,
	products_in_stock int,
	type_id int REFERENCES product_type(id)
);

INSERT INTO product_type
VALUES
(1, 'СЫР'),
(2, 'МОЛОЧНЫЕ ПРОДУКТЫ'),
(3, 'ХЛЕБОБУЛОЧНЫЕ ИЗДЕЛИЯ'),
(4, 'МЯСОБ РЫБА'),
(5, 'ОВОЩИ');

INSERT INTO product (name, expired_date, price, products_in_stock, type_id)
VALUES
('СЫР РОКФОР', '2023-06-23', 1563.3, 2, 1),
('СЫР ПЛАВЛЕННЫЙ', '2023-11-15', 150.3, 12, 1),
('СЫР ПОШЕХОНСКИЙ', '2023-04-02', 568.8, 6, 1),
('МОРОЖЕНОЕ ВАНИЛЬНОЕ', '2023-07-01', 245.5, 6, 2),
('СМЕТАНА', '2023-04-11', 100.1, 5, 2),
('МОЛОКО 2,5', '2023-04-04', 89.0, 12, 2),
('МОРОЖЕНОЕ С ШОКОЛАДНОЙ КРОШКОЙ', '2023-03-29', 245.5, 2, 2),
('БАТОН СТОЛИЧНЫЙ', '2023-04-10', 35.3, 10, 3),
('ХЛЕБ СТОЛОВЫЙ', '2023-04-03', 40.4, 5, 3),
('БУЛКА С МАКОМ', '2023-04-09', 55.7, 6, 3),
('ГОВЯДИНА', '2023-04-21', 654.3, 5, 4),
('СЁМГА', '2023-04-11', 1570.8, 3, 4),
('БАРАНИНА', '2023-04-15', 498.8, 5, 4),
('СВИНИНА', '2023-04-02', 297.7, 10, 4),
('ОГУРЦЫ', '2023-04-13', 152.2, 22, 5),
('КАПУСТА', '2023-04-12', 56.8, 32, 5),
('ПОМИДОРЫ', '2023-04-02', 325.7, 20, 5);

SELECT * FROM product

/* 1. Написать запрос получение всех продуктов с типом "СЫР" */
SELECT product.id, product.name, product_type.name as type, expired_date, price, products_in_stock
FROM product
JOIN product_type ON product.type_id = product_type.id
WHERE product_type.name ILIKE '%СЫР%';

/* 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое" */
SELECT product.id, product.name, product_type.name as type, expired_date, price, products_in_stock
FROM product
JOIN product_type ON product.type_id = product_type.id
WHERE product.name ILIKE '%мороженое%';

/* 3. Написать запрос, который выводит все продукты, срок годности которых уже истек */
SELECT product.id, product.name, expired_date, products_in_stock
FROM product
WHERE expired_date < now()
ORDER BY expired_date;

/* 4. Написать запрос, который выводит самый дорогой продукт. Запрос должен быть универсальный и находить все продукты с максимальной ценой */
SELECT product_type.name, MAX(price)
FROM product
JOIN product_type ON product.type_id = product_type.id
GROUP BY product_type.name
ORDER BY MAX(price) DESC

/* 5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество */
SELECT product_type.name as имя_типа, SUM(products_in_stock) as количество
FROM product_type
JOIN product ON product_type.id = product.type_id
GROUP BY product_type.name
ORDER BY SUM(products_in_stock) DESC

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT product.id, product.name, product_type.name as type, expired_date, price, products_in_stock
FROM product
JOIN product_type ON product.type_id = product_type.id
WHERE product_type.name ILIKE '%СЫР%' OR product_type.name ILIKE '%МОЛОЧНЫЕ ПРОДУКТЫ%';

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. Под количеством подразумевается количество продуктов определенного типа.
-- Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла", которые ему принадлежат, то количество продуктов типа "СЫР" будет 2.
SELECT product_type.name as type, products_in_stock
FROM product
JOIN product_type ON product.type_id = product_type.id
WHERE products_in_stock < 10
ORDER BY products_in_stock

-- 8. Вывести все продукты и их тип.
SELECT product.id, product.name, product_type.name as type, expired_date, price, products_in_stock
FROM product
JOIN product_type ON product.type_id = product_type.id;