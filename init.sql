DROP DATABASE IF EXISTS farmdb;

CREATE DATABASE farmdb;

USE farmdb;

-- First, lets create the tables
-- TABLES

CREATE TABLE Currency (
id tinyint UNSIGNED PRIMARY KEY auto_increment,
name VARCHAR(20),
abreviation VARCHAR(3)

);

INSERT INTO Currency (name, abreviation)
VALUES 
("dolars","USD"),
("euros", "EUR"),
("colombian pesos", "COP");

CREATE TABLE BankAccounts (
bank_name VARCHAR(25),
balance DECIMAL(14,2),
nickname VARCHAR(25),
account_number VARCHAR(34),
Currency_id tinyint UNSIGNED,
PRIMARY KEY (bank_name, account_number),
foreign key(Currency_id) references Currency(id)

);

INSERT INTO BankAccounts 
(bank_name, account_number, balance, Currency_id)
VALUES 
("Bank ABC","1111",0,1),
("Bank ABC","2222",1500,1),
("France Bank","123456",3000,2),
("Bancolombia","1234",100000,3);


CREATE TABLE Farms (
id tinyint UNSIGNED PRIMARY KEY auto_increment,
name VARCHAR(25),
latitude VARCHAR(10),
longitude VARCHAR(10)

);

INSERT INTO Farms (name, latitude, longitude)
VALUES 
("Medellin Farm","6.265644", "-75.574927"),
("Bogota Farm", "4.701495", "-74.081399" );

CREATE TABLE Weather (
id int UNSIGNED PRIMARY KEY auto_increment,
temperature DECIMAL(5,2),
weather VARCHAR(45),
Farms_id tinyint UNSIGNED,
date DATETIME,
foreign key(Farms_id) references Farms(id)

);

INSERT INTO Weather (date, weather, temperature)
VALUES 
("2025-01-01 12:00:00","Sunny",30),
("2025-01-01 14:00:00","Sunny",29),
("2025-01-02 10:00:00","Cloudy changing to possibly rain",22),
("2025-01-02 12:00:00","Rain",18),
("2025-01-03 09:00:00","Cloudy",20),
("2025-01-03 10:00:00","Cloudy changing to possibly sunny",23)
;
UPDATE Weather
SET Farms_id=1 LIMIT 100;
-- UPDATE SET requires use LIMIT or WHERE, or desable SQL_SAFE_UPDATES

CREATE TABLE Countable (
id int UNSIGNED PRIMARY KEY auto_increment,
quantity DECIMAL(6,2),
name VARCHAR(10),
Farms_id tinyint UNSIGNED,
foreign key(Farms_id) references Farms(id)

);

CREATE TABLE Purchasable (
id int UNSIGNED PRIMARY KEY auto_increment,
price_per_unit DECIMAL(5,2),
Countable_id int UNSIGNED,
Currency_id tinyint UNSIGNED,
foreign key(Countable_id) references Countable(id),
foreign key(Currency_id) references Currency(id)

);

CREATE TABLE Grains (
id int UNSIGNED PRIMARY KEY auto_increment,
Purchasable_id int UNSIGNED,
foreign key(Purchasable_id) references Purchasable(id)

);

CREATE TABLE Products (
id int UNSIGNED PRIMARY KEY auto_increment,
sell_price DECIMAL(5,2),
rotten_percentage DECIMAL(5,2),
rot_per_day DECIMAL(5,2),
Purchasable_id int UNSIGNED,
foreign key(Purchasable_id) references Purchasable(id)

);

-- Lets create some procedures, to abreviate the syntax and avoid code repetition in data insertions.
-- PROCEDURES
DELIMITER $$

CREATE PROCEDURE InsertPurchasable(
    IN p_name VARCHAR(10),
    IN p_quantity DECIMAL(6,2),
    IN p_price_per_unit DECIMAL(5, 2),
    IN p_Farms_id TINYINT
    )
BEGIN
    -- We start a transaction, to avoid errors when adding data
    START TRANSACTION;

	INSERT INTO Countable (name, quantity, Farms_id)
	VALUES (p_name, p_quantity,p_Farms_id);
	INSERT INTO Purchasable (price_per_unit, Countable_id)
	VALUES (p_price_per_unit, LAST_INSERT_ID());
	
    -- At the end we commit, if there are not errors.
    COMMIT;

END$$


CREATE PROCEDURE InsertGrain(
    IN p_name VARCHAR(10),
    IN p_quantity DECIMAL(6,2),
    IN p_price_per_unit DECIMAL(5, 2),
    IN p_Farms_id TINYINT
    )
BEGIN
    START TRANSACTION;
	
    CALL InsertPurchasable(p_name,p_quantity,p_price_per_unit,p_Farms_id);
	INSERT INTO Grains (Purchasable_id)
	VALUES (LAST_INSERT_ID());
   
    COMMIT;
END$$

CREATE PROCEDURE InsertProduct(
    IN p_sell_price DECIMAL(5,2),
    IN p_rotten_percentage DECIMAL(5,2),
    IN p_rot_per_day DECIMAL(5,2)    
    )
BEGIN
    START TRANSACTION;
	    
	INSERT INTO Products (sell_price,rotten_percentage,rot_per_day,Purchasable_id)
	VALUES (p_sell_price,p_rotten_percentage,p_rot_per_day,LAST_INSERT_ID());
   
    COMMIT;
END$$


DELIMITER ;


-- Use of the PROCEDURES

-- Insert grain (name, quantity, price_per_unit, Farms_id)
CALL InsertGrain("Corn",100,1,1);
CALL InsertGrain("Wheat",50,2,1);
CALL InsertGrain("Rice",80,0.5,1);
CALL InsertGrain("Soybean",70,0.8,1);
CALL InsertGrain("Barley",110,1,1);
CALL InsertGrain("Quinoa",150,1.5,1);

-- Insert purchasable (name, quantity, price_per_unit, Farms_id)
-- Insert product (sell_price, rotten_percentage, rot_per_day)
CALL InsertPurchasable("Corn",100,1,1);
CALL InsertProduct(2,0,0.5);
