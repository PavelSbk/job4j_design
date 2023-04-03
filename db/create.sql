create table rules (
	id serial primary key,
	rule_name text
);

create table roles (
	id serial primary key,
	role_name text
);

create table categories (
	id serial primary key,
	category_name text
);

create table states (
	id serial primary key,
	state_name text
);
/* users - role = many-to-one */
create table users (
	id serial primary key,
	user_name varchar(255),
	role_id int references roles(id)
);
/* item - users = many-to-one */
/* item - category = many-to-one */
/* item - state = many-to-one */
create table items (
	id serial primary key,
	item_name varchar(255),
	user_id int references users(id),
	category_id int references categories(id),
	state_id int references states(id)
);
/* item - comments = one-to-many */
create table comments (
	id serial primary key,
	comment_name varchar(255),
	item_id int references items(id)
);
/* item - attachs = one-to-many */
create table attachs (
	id serial primary key,
	attach_name varchar(255),
	item_id int references items(id)
);
/* role - rules = many-to-many */
create table roles_rules (
	id serial primary key,
	role_id int references roles(id),
	rule_id int references rules(id)
);
