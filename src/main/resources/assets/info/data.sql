-- Usuarios
INSERT INTO "user" (username, email, password,role_name) VALUES
('Alice', 'alice@gmail.com', '$2a$10$2eHF8siZ/4HN9rIYOquGje9jwwQjBNkBjOHKRQC1sJhr5XOKDA/ES', 'ADMIN'),
('Bob', 'bob@gmail.com', '$2a$10$Tq1/xXIIgo/HqAwuA.LdmOOnGTJ8l.5WNmTWflzu3JgYycF1Nvzp2', 'USER'), --1234abcd
('Charlie', 'charlie@gmail.com', '$2a$10$0qu1z/OKDz64Eh87aAK2CO91efg.ZVBVAVb8Myube0ikD60Q58bMy', 'USER');

--Categorías
INSERT INTO category (name, description) VALUES
('Food', 'Expenses on food and groceries'),
('Transport', 'Travel and transportation costs'),
('Health', 'Medical and health-related expenses'),
('Education', 'Spending on education'),
('Entertainment', 'Leisure and fun'),
('Utilities', 'Electricity, water, internet, etc.'),
('Clothing', 'Apparel and accessories'),
('Savings', 'Amount set aside for savings'),
('Gifts', 'Gifts and donations'),
('Misc', 'Miscellaneous expenses');

-- Transacciones por fechas

-- Enero 2024
INSERT INTO transaction (amount, created_date, type_transaction, user_id, category_id) VALUES
(50.00, '2024-01-05', 'EXPENSE', 3, 1),
(120.00, '2024-01-10', 'INCOME', 3, 2),
(30.00, '2024-01-15', 'EXPENSE', 2, 3),
(200.00, '2024-01-20', 'INCOME', 2, 4),
(90.00, '2024-01-25', 'EXPENSE', 1, 5);

-- Marzo 2024
INSERT INTO transaction (amount, created_date, type_transaction, user_id, category_id) VALUES
(15.50, '2024-03-02', 'EXPENSE', 1, 6),
(100.00, '2024-03-08', 'INCOME', 1, 7),
(75.00, '2024-03-14', 'EXPENSE', 1, 8),
(300.00, '2024-03-22', 'INCOME', 1, 9),
(65.00, '2024-03-30', 'EXPENSE', 1, 10);

-- Junio 2024
INSERT INTO transaction (amount, created_date, type_transaction, user_id, category_id) VALUES
(60.00, '2024-06-01', 'EXPENSE', 2, 2),
(250.00, '2024-06-07', 'INCOME', 2, 3),
(40.00, '2024-06-13', 'EXPENSE', 2, 4),
(90.00, '2024-06-19', 'INCOME', 2, 5),
(55.00, '2024-06-27', 'EXPENSE', 2, 6);

-- Agosto 2024
INSERT INTO transaction (amount, created_date, type_transaction, user_id, category_id) VALUES
(25.00, '2024-08-03', 'EXPENSE', 3, 7),
(500.00, '2024-08-09', 'INCOME', 3, 8),
(70.00, '2024-08-15', 'EXPENSE', 3, 9),
(150.00, '2024-08-21', 'INCOME', 3, 10),
(85.00, '2024-08-29', 'EXPENSE', 3, 1);

-- Octubre 2024
INSERT INTO transaction (amount, created_date, type_transaction, user_id, category_id) VALUES
(45.00, '2024-10-06', 'EXPENSE', 1, 3),
(320.00, '2024-10-11', 'INCOME', 1, 4),
(35.00, '2024-10-17', 'EXPENSE', 2, 5),
(400.00, '2024-10-23', 'INCOME', 2, 6),
(95.00, '2024-10-30', 'EXPENSE', 2, 7);
