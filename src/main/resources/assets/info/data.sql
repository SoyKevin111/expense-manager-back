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


-- Transacciones
-- Usuario 1
INSERT INTO transaction (amount, created_date_time, type_transaction, user_id, category_id) VALUES
(100.00, '2024-02-02 10:00:00', 'SAVINGS_IN', 1, 1),
(40.00,  '2024-02-10 11:00:00', 'SAVINGS_OUT', 1, 2),
(200.00, '2024-04-05 12:30:00', 'INCOME', 1, 3),
(80.00,  '2024-04-15 09:45:00', 'EXPENSE', 1, 4),
(60.00,  '2024-04-20 08:00:00', 'SAVINGS_IN', 1, 5),
(30.00,  '2024-05-01 17:15:00', 'SAVINGS_OUT', 1, 6),
(500.00, '2024-07-12 10:30:00', 'INCOME', 1, 7),
(90.00,  '2024-07-22 14:00:00', 'EXPENSE', 1, 8),
(150.00, '2024-09-02 15:30:00', 'SAVINGS_IN', 1, 9),
(45.00,  '2024-09-18 13:00:00', 'SAVINGS_OUT', 1, 10);

-- Usuario 2
INSERT INTO transaction (amount, created_date_time, type_transaction, user_id, category_id) VALUES
(300.00, '2024-01-06 09:00:00', 'INCOME', 2, 1),
(70.00,  '2024-01-18 10:00:00', 'EXPENSE', 2, 2),
(120.00, '2024-02-15 13:00:00', 'SAVINGS_IN', 2, 3),
(50.00,  '2024-03-05 08:45:00', 'SAVINGS_OUT', 2, 4),
(180.00, '2024-04-10 12:15:00', 'INCOME', 2, 5),
(65.00,  '2024-04-28 11:30:00', 'EXPENSE', 2, 6),
(90.00,  '2024-06-03 16:45:00', 'SAVINGS_IN', 2, 7),
(30.00,  '2024-06-21 09:20:00', 'SAVINGS_OUT', 2, 8),
(600.00, '2024-08-06 14:00:00', 'INCOME', 2, 9),
(100.00, '2024-09-12 13:40:00', 'EXPENSE', 2, 10);

-- Usuario 3
INSERT INTO transaction (amount, created_date_time, type_transaction, user_id, category_id) VALUES
(220.00, '2024-02-01 08:30:00', 'INCOME', 3, 1),
(55.00,  '2024-02-20 10:15:00', 'EXPENSE', 3, 2),
(140.00, '2024-03-12 11:45:00', 'SAVINGS_IN', 3, 3),
(35.00,  '2024-03-25 09:50:00', 'SAVINGS_OUT', 3, 4),
(175.00, '2024-05-08 10:10:00', 'INCOME', 3, 5),
(85.00,  '2024-05-14 13:30:00', 'EXPENSE', 3, 6),
(110.00, '2024-06-05 08:00:00', 'SAVINGS_IN', 3, 7),
(25.00,  '2024-06-25 10:00:00', 'SAVINGS_OUT', 3, 8),
(380.00, '2024-07-03 12:00:00', 'INCOME', 3, 9),
(95.00,  '2024-07-29 15:15:00', 'EXPENSE', 3, 10);

