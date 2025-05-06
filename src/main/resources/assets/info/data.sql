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
-- Transacciones para user_id = 1
INSERT INTO transaction (amount, created_date_time, type_transaction, user_id, category_id) VALUES
(1200.00, '2025-01-03 09:00:00', 'INCOME', 1, 1),
(300.00,  '2025-01-05 10:30:00', 'EXPENSE', 1, 2),
(500.00,  '2025-01-10 11:45:00', 'SAVINGS_IN', 1, 3),
(200.00,  '2025-01-15 14:00:00', 'SAVINGS_OUT', 1, 4),

(1500.00, '2025-02-02 08:15:00', 'INCOME', 1, 5),
(350.00,  '2025-02-07 12:00:00', 'EXPENSE', 1, 6),
(600.00,  '2025-02-12 13:30:00', 'SAVINGS_IN', 1, 7),
(250.00,  '2025-02-18 15:45:00', 'SAVINGS_OUT', 1, 8),

(1800.00, '2025-03-03 09:30:00', 'INCOME', 1, 9),
(400.00,  '2025-03-08 10:45:00', 'EXPENSE', 1, 10),
(700.00,  '2025-03-14 11:50:00', 'SAVINGS_IN', 1, 1),
(300.00,  '2025-03-20 13:10:00', 'SAVINGS_OUT', 1, 2),

(2000.00, '2025-04-01 08:00:00', 'INCOME', 1, 3),
(500.00,  '2025-04-05 09:20:00', 'EXPENSE', 1, 4),
(800.00,  '2025-04-11 10:35:00', 'SAVINGS_IN', 1, 5),
(350.00,  '2025-04-17 12:00:00', 'SAVINGS_OUT', 1, 6),

-- Extra 4 transacciones variadas
(250.00,  '2025-04-25 13:45:00', 'EXPENSE', 1, 7),
(1300.00, '2025-01-22 10:00:00', 'INCOME', 1, 8),
(450.00,  '2025-02-28 11:15:00', 'SAVINGS_IN', 1, 9),
(150.00,  '2025-03-26 14:10:00', 'SAVINGS_OUT', 1, 10);

-- Usuario 2
-- Transacciones para user_id = 2
INSERT INTO transaction (amount, created_date_time, type_transaction, user_id, category_id) VALUES
(1100.00, '2025-01-04 09:15:00', 'INCOME', 2, 1),
(280.00,  '2025-01-06 11:00:00', 'EXPENSE', 2, 2),
(520.00,  '2025-01-11 12:30:00', 'SAVINGS_IN', 2, 3),
(180.00,  '2025-01-16 14:20:00', 'SAVINGS_OUT', 2, 4),

(1400.00, '2025-02-03 10:10:00', 'INCOME', 2, 5),
(320.00,  '2025-02-08 09:50:00', 'EXPENSE', 2, 6),
(610.00,  '2025-02-14 13:10:00', 'SAVINGS_IN', 2, 7),
(220.00,  '2025-02-19 15:00:00', 'SAVINGS_OUT', 2, 8),

(1600.00, '2025-03-05 08:45:00', 'INCOME', 2, 9),
(390.00,  '2025-03-10 10:25:00', 'EXPENSE', 2, 10),
(680.00,  '2025-03-16 12:40:00', 'SAVINGS_IN', 2, 1),
(270.00,  '2025-03-21 14:30:00', 'SAVINGS_OUT', 2, 2),

(1900.00, '2025-04-02 09:05:00', 'INCOME', 2, 3),
(450.00,  '2025-04-06 10:55:00', 'EXPENSE', 2, 4),
(740.00,  '2025-04-12 11:50:00', 'SAVINGS_IN', 2, 5),
(310.00,  '2025-04-18 13:25:00', 'SAVINGS_OUT', 2, 6),

-- Extra 4 transacciones variadas
(230.00,  '2025-04-24 14:40:00', 'EXPENSE', 2, 7),
(1250.00, '2025-01-25 09:10:00', 'INCOME', 2, 8),
(490.00,  '2025-02-27 10:35:00', 'SAVINGS_IN', 2, 9),
(160.00,  '2025-03-28 15:15:00', 'SAVINGS_OUT', 2, 10);

--Usuario 3

-- Transacciones para user_id = 3
INSERT INTO transaction (amount, created_date_time, type_transaction, user_id, category_id) VALUES
(1050.00, '2025-01-03 08:30:00', 'INCOME', 3, 1),
(260.00,  '2025-01-07 10:15:00', 'EXPENSE', 3, 2),
(500.00,  '2025-01-12 11:40:00', 'SAVINGS_IN', 3, 3),
(150.00,  '2025-01-18 13:05:00', 'SAVINGS_OUT', 3, 4),

(1250.00, '2025-02-02 09:20:00', 'INCOME', 3, 5),
(310.00,  '2025-02-06 10:50:00', 'EXPENSE', 3, 6),
(590.00,  '2025-02-13 12:00:00', 'SAVINGS_IN', 3, 7),
(210.00,  '2025-02-17 14:10:00', 'SAVINGS_OUT', 3, 8),

(1500.00, '2025-03-01 08:10:00', 'INCOME', 3, 9),
(370.00,  '2025-03-05 10:00:00', 'EXPENSE', 3, 10),
(630.00,  '2025-03-11 11:20:00', 'SAVINGS_IN', 3, 1),
(260.00,  '2025-03-15 13:30:00', 'SAVINGS_OUT', 3, 2),

(1700.00, '2025-04-04 09:45:00', 'INCOME', 3, 3),
(420.00,  '2025-04-08 11:15:00', 'EXPENSE', 3, 4),
(710.00,  '2025-04-14 12:50:00', 'SAVINGS_IN', 3, 5),
(290.00,  '2025-04-20 14:35:00', 'SAVINGS_OUT', 3, 6),

-- Extra 4 transacciones variadas
(240.00,  '2025-01-28 15:10:00', 'EXPENSE', 3, 7),
(1190.00, '2025-02-25 09:35:00', 'INCOME', 3, 8),
(470.00,  '2025-03-27 10:45:00', 'SAVINGS_IN', 3, 9),
(190.00,  '2025-04-26 13:55:00', 'SAVINGS_OUT', 3, 10);



