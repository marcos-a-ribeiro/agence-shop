INSERT INTO tb_category(name) VALUES ('Eletronics');
INSERT INTO tb_category(name) VALUES ('Furniture');
INSERT INTO tb_category(name) VALUES ('Food');

INSERT INTO tb_product(name, description, available, price, category_id) VALUES ('Carrots',  'Organic & Fresh', 'TRUE', 2, 3);
INSERT INTO tb_product(name, description, available, price, category_id) VALUES ('Notebook',  'Intel i7', 1, 500, 1);
INSERT INTO tb_product(name, description, available, price, category_id) VALUES ('Sofa',  'Leather', 1, 80, 2);

INSERT INTO tb_user (name, email, password) VALUES ('Marcos', 'maribeiro9600@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('João', 'joao@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);