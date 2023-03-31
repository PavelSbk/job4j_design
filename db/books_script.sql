create TABLE books(
	book_id serial PRIMARY KEY, 
    title text,
    pages integer,
    author varchar(255),
    ISBN integer
);

insert into books
values
(1,	'Head First Design Patterns', '694', 'bof',	1492078002),
(2,	'Effective Java', 412, 'Joshua Bloch', 0134685997),
(3,	'Grokking Algorithms', 300, 'Aditya Bhargava', 1617292230),
(4,	'Head First Java',	688, 'bof',	1491910771),
(5,	'Java Concurrency in Practice',	424, 'Brian Goetz',	0321349601);

select * from books;




