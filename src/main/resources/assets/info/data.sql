-- Roles
INSERT INTO roles (role_name) VALUES
('ADMIN'),
('USER');

-- Usuarios
INSERT INTO "user" (username, email, password, role_id) VALUES
('Alice', 'alice@example.com', 'ID001', 1),
('Bob', 'bob@example.com', 'ID002', 2),
('Charlie', 'charlie@example.com', 'ID003', 2),
('Diana', 'diana@example.com', 'ID004', 2),
('Ethan', 'ethan@example.com', 'ID005',2);


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
INSERT INTO transaction (amount, created_at, type_transaction, user_id, category_id) VALUES
(50.00, '2024-01-05', 'EXPENSE', 5, 1),
(120.00, '2024-01-10', 'INCOME', 5, 2),
(30.00, '2024-01-15', 'EXPENSE', 5, 3),
(200.00, '2024-01-20', 'INCOME', 5, 4),
(90.00, '2024-01-25', 'EXPENSE', 5, 5);

-- Marzo 2024
INSERT INTO transaction (amount, created_at, type_transaction, user_id, category_id) VALUES
(15.50, '2024-03-02', 'EXPENSE', 1, 6),
(100.00, '2024-03-08', 'INCOME', 1, 7),
(75.00, '2024-03-14', 'EXPENSE', 1, 8),
(300.00, '2024-03-22', 'INCOME', 1, 9),
(65.00, '2024-03-30', 'EXPENSE', 1, 10);

-- Junio 2024
INSERT INTO transaction (amount, created_at, type_transaction, user_id, category_id) VALUES
(60.00, '2024-06-01', 'EXPENSE', 2, 2),
(250.00, '2024-06-07', 'INCOME', 2, 3),
(40.00, '2024-06-13', 'EXPENSE', 2, 4),
(90.00, '2024-06-19', 'INCOME', 2, 5),
(55.00, '2024-06-27', 'EXPENSE', 2, 6);

-- Agosto 2024
INSERT INTO transaction (amount, created_at, type_transaction, user_id, category_id) VALUES
(25.00, '2024-08-03', 'EXPENSE', 3, 7),
(500.00, '2024-08-09', 'INCOME', 3, 8),
(70.00, '2024-08-15', 'EXPENSE', 3, 9),
(150.00, '2024-08-21', 'INCOME', 3, 10),
(85.00, '2024-08-29', 'EXPENSE', 3, 1);

-- Octubre 2024
INSERT INTO transaction (amount, created_at, type_transaction, user_id, category_id) VALUES
(45.00, '2024-10-06', 'EXPENSE', 4, 3),
(320.00, '2024-10-11', 'INCOME', 4, 4),
(35.00, '2024-10-17', 'EXPENSE', 4, 5),
(400.00, '2024-10-23', 'INCOME', 4, 6),
(95.00, '2024-10-30', 'EXPENSE', 4, 7);
