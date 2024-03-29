INSERT INTO ITEMS (Item_Type, Name) VALUES
('Fruit', 'Apple'),
('Fruit', 'Banana'),
('Vegetable', 'Carrot'),
('Vegetable', 'Broccoli'),
('Grain', 'Rice'),
('Grain', 'Pasta'),
('Fruit', 'Orange'),
('Dairy', 'Milk'),
('Dairy', 'Cheese'),
('Meat', 'Chicken');

INSERT INTO RETAILERS (username, name, password) VALUES
('retailer1', 'Loblaws', 'password123'),
('retailer2', 'Costco', 'password123'),
('retailer3', 'Farm Boy', 'password123');

INSERT INTO STOCK (Item_ID, Retailer_ID, Expiration_Date, Quantity, Discounted_Price, Surplus) VALUES
(1, 1, '2024-12-31', 100, 19.99, FALSE),
(2, 1, '2024-11-30', 200, 29.99, TRUE),
(3, 2, '2024-10-31', 150, 9.99, FALSE),
(4, 2, '2024-09-30', 250, 14.99, TRUE),
(5, 3, '2024-08-31', 175, 4.99, FALSE),
(6, 3, '2024-07-31', 300, 7.99, TRUE),
(7, 1, '2024-06-30', 120, 24.99, FALSE),
(8, 2, '2024-05-31', 220, 34.99, TRUE),
(9, 3, '2024-04-30', 180, 11.99, FALSE),
(10, 1, '2024-03-31', 290, 6.99, TRUE);
(10, 1, '2024-03-28', 290, 0.00, TRUE);