insert into rules
values
(1, 'rule1'),
(2, 'rule2'),
(3, 'rule3');

insert into roles
values
(1, 'role1'),
(2, 'role2'),
(3, 'role3');

insert into categories
values
(1, 'cat1'),
(2, 'cat2'),
(3, 'cat3'),
(4, 'cat4');

insert into states(state_name)
values
('state1'),
('state2');

insert into users(user_name, role_id)
values
('A', 1),
('B', 1),
('C', 3),
('D', 2);

insert into items(item_name, user_id, category_id, state_id)
values
('I1',1, 1, 1),
('I2', 2, 2, 2),
('I3', 3, 2, 1),
('I4', 2, 1, 1);

insert into attachs(attach_name, item_id)
values
('Atch1', 1),
('Atch2', 1),
('Atch3', 3);

insert into roles_rules(role_id, rule_id)
values
(1, 2),
(2, 1),
(2, 3),
(3, 1);