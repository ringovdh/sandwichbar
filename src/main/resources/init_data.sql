insert into ingredient(id, category, name, stock) values (1, 'Vegetables', 'Tomato', 3);
insert into ingredient(id, category, name, stock) values (2, 'Vegetables', 'Salad', 3);
insert into ingredient(id, category, name, stock) values (3, 'Cheese', 'Cheddar', 2);
insert into ingredient(id, category, name, stock) values (4, 'Eggs', 'Egg', 3);
insert into ingredient(id, category, name, stock) values (5, 'Meat', 'Ham', 3);

insert into product(id, name, price, stock, product_type) values (1, 'Coca Cola', 2.0, 3, 'DRINK');
insert into product(id, name, price, stock, product_type) values (2, 'Pepsi Cola', 2.0, 0, 'DRINK');
insert into product(id, name, price, stock, product_type) values (3, 'Ice Tea', 2.5, 3, 'DRINK');
insert into product(id, name, price, stock, product_type) values (4, 'Vittel', 1.8, 3, 'DRINK');
insert into product(id, name, price, product_type) values (5, 'Cheese sandwich', 4.5, 'SANDWICH');
insert into product(id, name, price, product_type) values (6, 'Ham sandwich', 4.0, 'SANDWICH');
insert into product(id, name, price, product_type) values (7, 'Egg sandwich', 3.8, 'SANDWICH');

insert into product_ingredient(id, product_id, ingredient_id) values (1, 5, 1);
insert into product_ingredient(id, product_id, ingredient_id) values (2, 5, 2);
insert into product_ingredient(id, product_id, ingredient_id) values (3, 5, 3);
insert into product_ingredient(id, product_id, ingredient_id) values (4, 6, 5);
insert into product_ingredient(id, product_id, ingredient_id) values (5, 6, 1);
insert into product_ingredient(id, product_id, ingredient_id) values (6, 6, 2);
insert into product_ingredient(id, product_id, ingredient_id) values (7, 7, 3);
insert into product_ingredient(id, product_id, ingredient_id) values (8, 7, 2);