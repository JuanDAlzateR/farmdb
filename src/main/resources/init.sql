DROP DATABASE IF EXISTS farmdb;

CREATE DATABASE farmdb;

USE farmdb;

-- First, lets create the tables
-- TABLES
CREATE TABLE Currency (
    id tinyint UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    abbreviation VARCHAR(3)
);

INSERT INTO
    Currency (name, abbreviation)
VALUES
    ("dolars", "USD"),
    ("euros", "EUR"),
    ("colombian pesos", "COP");

CREATE TABLE BankAccounts (
    bank_name VARCHAR(25),
    balance DECIMAL(14, 2),
    nickname VARCHAR(25),
    account_number VARCHAR(34),
    Currency_id tinyint UNSIGNED,
    PRIMARY KEY (bank_name, account_number),
    FOREIGN KEY(Currency_id) REFERENCES Currency(id)
);

INSERT INTO
    BankAccounts (bank_name, account_number, balance, Currency_id)
VALUES
    ("Bank ABC", "1111", 0, 1),
    ("Bank ABC", "2222", 1500, 1),
    ("France Bank", "123456", 3000, 2),
    ("Bancolombia", "1234", 100000, 3);

CREATE TABLE Farms (
    id tinyint UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(25),
    latitude VARCHAR(10),
    longitude VARCHAR(10)
);

INSERT INTO
    Farms (name, latitude, longitude)
VALUES
    ("Medellin Farm", "6.265644", "-75.574927"),
    ("Bogota Farm", "4.701495", "-74.081399");

CREATE TABLE Weather (
    id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    temperature DECIMAL(5, 2),
    weather VARCHAR(45),
    Farms_id tinyint UNSIGNED,
    date DATETIME,
    FOREIGN KEY(Farms_id) REFERENCES Farms(id)
);

INSERT INTO
    Weather (date, weather, temperature)
VALUES
    ("2025-01-01 12:00:00", "Sunny", 30),
    ("2025-01-01 14:00:00", "Sunny", 29),
    (
        "2025-01-02 10:00:00",
        "Cloudy changing to possibly rain",
        22
    ),
    ("2025-01-02 12:00:00", "Rain", 18),
    ("2025-01-03 09:00:00", "Cloudy", 20),
    (
        "2025-01-03 10:00:00",
        "Cloudy changing to possibly sunny",
        23
    );

UPDATE
    Weather
SET
    Farms_id = 1
LIMIT
    100;

-- UPDATE SET requires use LIMIT or WHERE, or desable SQL_SAFE_UPDATES
CREATE TABLE Countable (
    id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    quantity DECIMAL(6, 2),
    name VARCHAR(20),
    Farms_id tinyint UNSIGNED,
    FOREIGN KEY(Farms_id) REFERENCES Farms(id)
);

CREATE TABLE Purchasable (
    id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    price_per_unit DECIMAL(10, 2),
    Countable_id int UNSIGNED,
    Currency_id tinyint UNSIGNED,
    FOREIGN KEY(Countable_id) REFERENCES Countable(id),
    FOREIGN KEY(Currency_id) REFERENCES Currency(id)
);

CREATE TABLE Grains (
    id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    Purchasable_id int UNSIGNED,
    FOREIGN KEY(Purchasable_id) REFERENCES Purchasable(id)
);

CREATE TABLE Products (
    id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    sell_price DECIMAL(5, 2),
    rotten_percentage DECIMAL(5, 2),
    rot_per_day DECIMAL(5, 2),
    Purchasable_id int UNSIGNED,
    FOREIGN KEY(Purchasable_id) REFERENCES Purchasable(id)
);

CREATE TABLE CropStates (
    id tinyint UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(25)
);

INSERT INTO
    CropStates (name)
VALUES
    ("ready to plant"),
    ("planted"),
    ("growing"),
    ("ready to harvest"),
    ("harvested");

CREATE TABLE Crops (
    id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    germination_rate DECIMAL(5, 2),
    growth_percentage DECIMAL(5, 2),
    growth_per_day DECIMAL(5, 2),
    Countable_id int UNSIGNED,
    CropStates_id tinyint UNSIGNED,
    FOREIGN KEY(Countable_id) REFERENCES Countable(id),
    FOREIGN KEY(CropStates_id) REFERENCES CropStates(id)
);

CREATE TABLE Tools (
    id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    wear_and_tear_percentage DECIMAL(5, 2),
    wear_and_tear_per_day DECIMAL(5, 2),
    Purchasable_id int UNSIGNED,
    FOREIGN KEY(Purchasable_id) REFERENCES Purchasable(id)
);

CREATE TABLE Units (
    id TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    unit VARCHAR(25),
    abreviation VARCHAR(5)
);

INSERT INTO
    Units (unit, abreviation)
VALUES
    ("each", "EA"),
    ("units", "UN"),
    ("pieces", "PC"),
    ("kilograms", "kg"),
    ("grams", "g"),
    ("milligrams", "mg"),
    ("tons", "t"),
    ("pounds", "lb"),
    ("ounces", "oz"),
    ("liters", "L"),
    ("milliliters", "mL"),
    ("gallons", "gal"),
    ("pints", "pt"),
    ("quarts", "qt");

CREATE TABLE AnimalFeed (
    id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    consumption_rate_per_day DECIMAL(5, 2),
    Units_id TINYINT UNSIGNED,
    Purchasable_id int UNSIGNED,
    FOREIGN KEY(Purchasable_id) REFERENCES Purchasable(id),
    FOREIGN KEY(Units_id) REFERENCES Units(id)
);

CREATE TABLE AnimalFood (
    id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    production_rate_per_day DECIMAL(5, 2),
    Units_id TINYINT UNSIGNED,
    Countable_id int UNSIGNED,
    FOREIGN KEY(Countable_id) REFERENCES Countable(id),
    FOREIGN KEY(Units_id) REFERENCES Units(id)
);

CREATE TABLE AnimalType (
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(15),
    subcategory VARCHAR(30)
);

INSERT INTO
    AnimalType (category, subcategory)
VALUES
    ("Aquaculture", "Fish"),
    ("Aquaculture", "Shellfish"),
    ("Aquaculture", "Undefined Aquaculture"),
    ("Equines", "Horses"),
    ("Equines", "Donkeys"),
    ("Equines", "Mules"),
    ("Equines", "Undefined Equines"),
    ("Livestock", "Cattle"),
    ("Livestock", "Pigs"),
    ("Livestock", "Sheep"),
    ("Livestock", "Goats"),
    ("Livestock", "Undefined Livestock"),
    ("Poultry", "Chickens"),
    ("Poultry", "Turkeys"),
    ("Poultry", "Ducks"),
    ("Poultry", "Geese"),
    ("Poultry", "Undefined Poultry"),
    ("Others", "Rabbits"),
    ("Others", "Bees"),
    ("Others", "Alpacas"),
    ("Others", "Undefined Others");

CREATE TABLE Animals (
    id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    Purchasable_id int UNSIGNED,
    AnimalFood_id int UNSIGNED,
    AnimalFeed_id int UNSIGNED,
    AnimalType_id int UNSIGNED,
    FOREIGN KEY(Purchasable_id) REFERENCES Purchasable(id),
    FOREIGN KEY(AnimalFood_id) REFERENCES AnimalFood(id),
    FOREIGN KEY(AnimalFeed_id) REFERENCES AnimalFeed(id),
    FOREIGN KEY(AnimalType_id) REFERENCES AnimalType(id)
);

-- Lets create some procedures, to abreviate the syntax and avoid code repetition in data insertions.
-- PROCEDURES
DELIMITER $ $ CREATE PROCEDURE InsertPurchasable(
    IN p_name VARCHAR(20),
    IN p_quantity DECIMAL(6, 2),
    IN p_price_per_unit DECIMAL(10, 2),
    IN p_Farms_id TINYINT
) BEGIN -- We start a transaction, to avoid errors when adding data
START TRANSACTION;

INSERT INTO
    Countable (name, quantity, Farms_id)
VALUES
    (p_name, p_quantity, p_Farms_id);

INSERT INTO
    Purchasable (price_per_unit, Countable_id, Currency_id)
VALUES
    (p_price_per_unit, LAST_INSERT_ID(), 1);

-- At the end we commit, if there are not errors.
COMMIT;

END $ $ CREATE PROCEDURE InsertGrain(
    IN p_name VARCHAR(10),
    IN p_quantity DECIMAL(6, 2),
    IN p_price_per_unit DECIMAL(5, 2),
    IN p_Farms_id TINYINT
) BEGIN START TRANSACTION;

CALL InsertPurchasable(p_name, p_quantity, p_price_per_unit, p_Farms_id);

INSERT INTO
    Grains (Purchasable_id)
VALUES
    (LAST_INSERT_ID());

COMMIT;

END $ $ CREATE PROCEDURE InsertProduct(
    IN p_sell_price DECIMAL(5, 2),
    IN p_rotten_percentage DECIMAL(5, 2),
    IN p_rot_per_day DECIMAL(5, 2)
) BEGIN START TRANSACTION;

INSERT INTO
    Products (
        sell_price,
        rotten_percentage,
        rot_per_day,
        Purchasable_id
    )
VALUES
    (
        p_sell_price,
        p_rotten_percentage,
        p_rot_per_day,
        LAST_INSERT_ID()
    );

COMMIT;

END $ $ CREATE PROCEDURE InsertCountable(
    IN p_name VARCHAR(20),
    IN p_quantity DECIMAL(6, 2),
    IN p_Farms_id TINYINT
) BEGIN START TRANSACTION;

INSERT INTO
    Countable (name, quantity, Farms_id)
VALUES
    (p_name, p_quantity, p_Farms_id);

COMMIT;

END $ $ CREATE PROCEDURE InsertCrop(
    IN p_germination_rate DECIMAL(5, 2),
    IN p_growth_percentage DECIMAL(5, 2),
    IN p_growth_per_day DECIMAL(5, 2),
    IN p_CropStates_id TINYINT
) BEGIN START TRANSACTION;

INSERT INTO
    Crops (
        germination_rate,
        growth_percentage,
        growth_per_day,
        Countable_id,
        CropStates_id
    )
VALUES
    (
        p_germination_rate,
        p_growth_percentage,
        p_growth_per_day,
        LAST_INSERT_ID(),
        p_CropStates_id
    );

COMMIT;

END $ $ CREATE PROCEDURE InsertTool(
    IN p_wear_and_tear_percentage DECIMAL(5, 2),
    IN p_wear_and_tear_per_day DECIMAL(5, 2)
) BEGIN START TRANSACTION;

INSERT INTO
    Tools (
        wear_and_tear_percentage,
        wear_and_tear_per_day,
        Purchasable_id
    )
VALUES
    (
        p_wear_and_tear_percentage,
        p_wear_and_tear_per_day,
        LAST_INSERT_ID()
    );

COMMIT;

END $ $ CREATE PROCEDURE InsertAnimalFood(
    IN p_name VARCHAR(20),
    IN p_quantity DECIMAL(6, 2),
    IN p_Farms_id TINYINT,
    IN p_production_rate_per_day DECIMAL(5, 2),
    IN p_Units_id TINYINT UNSIGNED
) BEGIN START TRANSACTION;

Call InsertCountable(p_name, p_quantity, p_Farms_id);

INSERT INTO
    AnimalFood (production_rate_per_day, Units_id, Countable_id)
VALUES
    (
        p_production_rate_per_day,
        p_Units_id,
        LAST_INSERT_ID()
    );

COMMIT;

END $ $ CREATE PROCEDURE InsertAnimalFeed(
    IN p_name VARCHAR(20),
    IN p_quantity DECIMAL(6, 2),
    IN p_price_per_unit DECIMAL(10, 2),
    IN p_Farms_id TINYINT,
    IN p_consumption_rate_per_day DECIMAL(5, 2),
    IN p_Units_id TINYINT UNSIGNED
) BEGIN START TRANSACTION;

Call InsertPurchasable(p_name, p_quantity, p_price_per_unit, p_Farms_id);

INSERT INTO
    AnimalFeed (
        consumption_rate_per_day,
        Units_id,
        Purchasable_id
    )
VALUES
    (
        p_consumption_rate_per_day,
        p_Units_id,
        LAST_INSERT_ID()
    );

COMMIT;

END $ $ CREATE PROCEDURE InsertAnimal(
    IN p_Purchasable_id INT,
    IN p_AnimalFood_id INT,
    IN p_AnimalFeed_id INT,
    IN p_AnimalType_id INT
) BEGIN START TRANSACTION;

INSERT INTO
    Animals (
        Purchasable_id,
        AnimalFood_id,
        AnimalFeed_id,
        AnimalType_id
    )
VALUES
    (
        p_Purchasable_id,
        p_AnimalFood_id,
        p_AnimalFeed_id,
        p_AnimalType_id
    );

COMMIT;

END $ $ CREATE FUNCTION GetUnitId(p_unit VARCHAR(25) -- Parameter
) RETURNS TINYINT UNSIGNED READS SQL DATA BEGIN DECLARE unit_id TINYINT UNSIGNED;

-- Variable to store the id
-- Consult the id based on the unit
SELECT
    id INTO unit_id
FROM
    Units
WHERE
    unit = p_unit;

RETURN unit_id;

-- returns found id
END $ $ CREATE FUNCTION GetAnimalTypeId(p_subcategory VARCHAR(30) -- Parameter
) RETURNS INT UNSIGNED READS SQL DATA BEGIN DECLARE AnimalType_id INT UNSIGNED;

-- Variable to store the id
SELECT
    id INTO AnimalType_id
FROM
    AnimalType
WHERE
    subcategory = p_subcategory;

RETURN AnimalType_id;

-- returns found id
END $ $ DELIMITER;

-- Use of the PROCEDURES
-- Insert grain (name, quantity, price_per_unit, Farms_id)
CALL InsertGrain("Corn", 100, 1, 1);
CALL InsertGrain("Wheat", 50, 2, 1);
CALL InsertGrain("Rice", 80, 0.5, 1);
CALL InsertGrain("Soybean", 70, 0.8, 1);
CALL InsertGrain("Barley", 110, 1, 1);
CALL InsertGrain("Quinoa", 150, 1.5, 1);

-- Insert countable (name, quantity, Farms_id)
-- Insert crop (germination_rate, growth_percentage, growth_per_day,CropStates_id)
CALL InsertCountable("Corn Crop", 80, 1);

CALL InsertCrop(0.8, 0, 0.1, 2);

CALL InsertCountable("Wheat Crop", 40, 1);

CALL InsertCrop(0.7, 0, 0.2, 2);

-- Insert purchasable (name, quantity, price_per_unit, Farms_id)
-- Insert product (sell_price, rotten_percentage, rot_per_day)
CALL InsertPurchasable("Corn Product", 100, 1, 1);

CALL InsertProduct(2, 0, 0.5);

-- Insert tool (wear_and_tear_percentage, wear_and_tear_per_day)
CALL InsertPurchasable("Tractor", 2, 20000, 1);
CALL InsertTool(0, 1);
CALL InsertPurchasable("Harvester", 1, 10000, 1);
CALL InsertTool(0, 1);
CALL InsertPurchasable("Plow", 2, 5000, 1);
CALL InsertTool(0, 1);
CALL InsertPurchasable("Disc Harrow", 1, 4000, 1);
CALL InsertTool(0, 1);
CALL InsertPurchasable("Seeder", 5, 15000, 1);
CALL InsertTool(0, 1);
CALL InsertPurchasable("Wheelbarrow", 2, 100, 1);
CALL InsertTool(0, 1);
CALL InsertPurchasable("Shovel", 3, 35, 1);
CALL InsertTool(20, 0.5);
CALL InsertPurchasable("Pitchfork", 1, 50, 1);
CALL InsertTool(0, 2);

-- Insert AnimalFood (p_name, p_quantity,p_Farms_id, production_rate_per_day, Units_id)
CALL InsertAnimalFood("Milk", 0, 1, 5, GetUnitID("gallons"));
CALL InsertAnimalFood("Eggs", 0, 1, 10, GetUnitID("units"));
CALL InsertAnimalFood("Honey", 0, 1, 0.25, GetUnitID("pounds"));

-- Insert AnimalFeed 
-- (p_name, p_quantity,p_price_per_unit, p_Farms_id, consumption_rate_per_day, Units_id)
CALL InsertAnimalFeed("Hay", 2000, 0.15, 1, 40, GetUnitID("pounds"));
CALL InsertAnimalFeed("Layer Pellets", 500, 0.35, 1, 0.25, GetUnitID("pounds"));
CALL InsertAnimalFeed("Sugar Syrup", 10, 0.5, 1, 0.1, GetUnitID("pounds"));

-- Insert purchasable (name, quantity, price_per_unit, Farms_id)
-- Insert Animal (Purchasable_id, AnimalFood_id, AnimalFeed_id, AnimalType_id)
CALL InsertPurchasable("Cows", 3, 1800, 1);
CALL InsertAnimal(LAST_INSERT_ID(), 1, 1, GetAnimalTypeId("Cattle"));
CALL InsertPurchasable("Chikens", 20, 8, 1);
CALL InsertAnimal(LAST_INSERT_ID(), 2, 2, GetAnimalTypeId("Chickens"));
CALL InsertPurchasable("Bee Hive", 2, 300, 1);
CALL InsertAnimal(LAST_INSERT_ID(), 3, 3, GetAnimalTypeId("Bees"));