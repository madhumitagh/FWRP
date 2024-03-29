DROP DATABASE IF EXISTS FWRP;
CREATE DATABASE FWRP;
USE FWRP;

CREATE TABLE ITEMS (
    Item_ID INT AUTO_INCREMENT PRIMARY KEY,
    Item_Type VARCHAR(64),
    Name VARCHAR(255)
);


CREATE TABLE RETAILERS (
    Retailer_ID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    name VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE CHARITY (
    Charity_ID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(255) UNIQUE,
    Name VARCHAR(255),
    Password VARCHAR(255),
    Subscribe boolean NOT NULL
);

CREATE TABLE INDIVIDUAL (
    Individual_ID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(255) UNIQUE,
    Name VARCHAR(255),
    Password VARCHAR(255),
    Subscribe boolean NOT NULL
);

CREATE TABLE CONSUMPTION (
    Item_ID INT NOT NULL,
    Retailer_ID INT NOT NULL,
    Expiration_Date DATE NOT NULL,
    ConsumerType ENUM('CHA', 'IND'),
    Consumer_ID INT NOT NULL,
    Quantity INT NOT NULL,
    Date_Purchased DATE NOT NULL,
    Price_Purchased DECIMAL(10,2) NOT NULL,
    
    FOREIGN KEY (Item_ID) REFERENCES ITEMS(Item_ID),
    FOREIGN KEY (Retailer_ID) REFERENCES RETAILERS(Retailer_ID)
);


CREATE TABLE STOCK (
    Item_ID INT NOT NULL,
    Retailer_ID INT NOT NULL,
    Expiration_Date DATE NOT NULL,
    Quantity INT NOT NULL,
    Discounted_Price DECIMAL(10,2) NOT NULL,
    Surplus boolean NOT NULL,
    PRIMARY KEY (Item_ID, Retailer_ID, Expiration_Date),
    FOREIGN KEY (Item_ID) REFERENCES ITEMS(Item_ID),
    FOREIGN KEY (Retailer_ID) REFERENCES RETAILERS(Retailer_ID)
);


-- View to list surplus items for consumers

CREATE VIEW LIST_SURPLUS AS
SELECT 
    R.name AS Retailer,
    I.Item_Type,
    I.Name AS 'Item Name',
    S.Expiration_Date,
    S.Discounted_Price,
    S.Quantity
FROM 
    STOCK S
INNER JOIN 
    ITEMS I ON I.Item_ID = S.Item_ID
INNER JOIN 
    RETAILERS R ON R.Retailer_ID = S.Retailer_ID
WHERE 
    S.Surplus = TRUE and Discounted_Price > 0;
    
-- View to list donation items for charitable org assuming price=0 is for donation

CREATE VIEW LIST_DONATION AS
SELECT 
    R.name AS Retailer,
    I.Item_Type,
    I.Name AS 'Item Name',
    S.Expiration_Date,
    S.Discounted_Price,
    S.Quantity
FROM 
    STOCK S
INNER JOIN 
    ITEMS I ON I.Item_ID = S.Item_ID
INNER JOIN 
    RETAILERS R ON R.Retailer_ID = S.Retailer_ID
WHERE 
    S.Surplus = TRUE and Discounted_Price = 0;
    
    
-- Trigger to update quantity in Stock table
DELIMITER $$

CREATE TRIGGER AfterConsumptionInsert
AFTER INSERT ON CONSUMPTION
FOR EACH ROW
BEGIN
    UPDATE STOCK
    SET Quantity = Quantity - NEW.Quantity
    WHERE Item_ID = NEW.Item_ID AND Retailer_ID = NEW.Retailer_ID AND Expiration_Date=NEW.Expiration_Date;
END$$

DELIMITER ;

-- Trigger to delete record from stock table if the quantity is updated to 0

DELIMITER $$
CREATE TRIGGER AfterStockUpdate
AFTER UPDATE ON STOCK
FOR EACH ROW
BEGIN
    IF NEW.Quantity = 0 THEN
        DELETE FROM STOCK WHERE Item_ID = NEW.Item_ID AND Retailer_ID = NEW.Retailer_ID AND Expiration_Date = NEW.Expiration_Date;
    END IF;
END$$

DELIMITER ;



