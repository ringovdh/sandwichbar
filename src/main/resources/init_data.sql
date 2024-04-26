insert into ingredient(id, category, name, stock) values (1, 'Vegetables', 'Tomato', 3);
insert into ingredient(id, category, name, stock) values (2, 'Vegetables', 'Salad', 3);
insert into ingredient(id, category, name, stock) values (3, 'Cheese', 'Cheddar', 2);
insert into ingredient(id, category, name, stock) values (4, 'Eggs', 'Egg', 3);
insert into ingredient(id, category, name, stock) values (5, 'Meat', 'Ham', 3);

insert into drink(id, name, price, stock, product_id) values (1, 'Coca Cola', 2.0, 3, '6b65434b-af6e-48db-969f-a71558999aaf');
insert into drink(id, name, price, stock, product_id) values (2, 'Pepsi Cola', 2.0, 0, 'b22c7e67-c010-4571-84c7-f8f12dc0fe04');
insert into drink(id, name, price, stock, product_id) values (3, 'Ice Tea', 2.5, 3, '1eedc780-caf2-4c5a-8335-af1b64cff6cf');
insert into drink(id, name, price, stock, product_id) values (4, 'Vittel', 1.8, 3, '82e238ed-8883-4486-90ad-66d66a39c181');

insert into sandwich(id, name, price, product_id) values (1, 'Cheese sandwich', 4.5, '98ad1617-5e74-4a2e-962c-d238f7c34f1d');
insert into sandwich(id, name, price, product_id) values (2, 'Ham sandwich', 4.0, 'c608bdea-f8d2-444a-87d2-9e01573982d6');
insert into sandwich(id, name, price, product_id) values (3, 'Egg sandwich', 3.8, '3c6da0e9-71fe-48d8-bba8-715777dcba11');

insert into sandwich_ingredient(id, sandwich_id, ingredient_id) values (1, 1, 1);
insert into sandwich_ingredient(id, sandwich_id, ingredient_id) values (2, 1, 2);
insert into sandwich_ingredient(id, sandwich_id, ingredient_id) values (3, 1, 3);
insert into sandwich_ingredient(id, sandwich_id, ingredient_id) values (4, 2, 5);
insert into sandwich_ingredient(id, sandwich_id, ingredient_id) values (5, 2, 1);
insert into sandwich_ingredient(id, sandwich_id, ingredient_id) values (6, 2, 2);
insert into sandwich_ingredient(id, sandwich_id, ingredient_id) values (7, 3, 3);
insert into sandwich_ingredient(id, sandwich_id, ingredient_id) values (8, 3, 2);