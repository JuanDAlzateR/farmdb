USE farmdb;
-- ---------------------
-- 10 LEFT JOIN QUERIES

-- 1. List all Purchasable items with their name and quantities
SELECT Cble.name, Cble.quantity, Pble.price_per_unit
FROM Purchasable Pble
LEFT JOIN Countable Cble ON Pble.Countable_id = Cble.id;

-- 2. List all Crops with their name, quantities and their states
SELECT Cble.name, Cble.quantity, Crops.growth_percentage, Crops.growth_per_day, CS.name as state
FROM Crops 
LEFT JOIN Countable Cble ON Crops.Countable_id = Cble.id
LEFT JOIN CropStates CS ON Crops.CropStates_id = CS.id;

-- 3. List all Grains with their name, quantities and their prices
SELECT Cble.name, Cble.quantity, Pble.price_per_unit
FROM Grains 
LEFT JOIN Purchasable Pble ON Grains.Purchasable_id = Pble.id
LEFT JOIN Countable Cble ON Pble.Countable_id = Cble.id;

-- 4. List all Products with their name, quantities and their prices
SELECT Cble.name, Cble.quantity, Pble.price_per_unit, P.sell_price 
FROM Products P
LEFT JOIN Purchasable Pble ON P.Purchasable_id = Pble.id
LEFT JOIN Countable Cble ON Pble.Countable_id = Cble.id;

-- 5. List all Tools with their name, quantities, prices and wear and tear
SELECT Cble.name, Cble.quantity, Pble.price_per_unit, T.wear_and_tear_percentage
FROM Tools T
LEFT JOIN Purchasable Pble ON T.Purchasable_id = Pble.id
LEFT JOIN Countable Cble ON Pble.Countable_id = Cble.id;

-- 6. List all BankAccounts with their currency
SELECT BA.bank_name, BA.account_number, BA.balance, C.abreviation AS currency
FROM BankAccounts BA
LEFT JOIN Currency C ON BA.Currency_id = C.id;

-- 7. List all AnimalFeed with their name, quantities, consumption and ther units
SELECT Cble.name, Cble.quantity, Pble.price_per_unit,C.abreviation AS currency, AFeed.consumption_rate_per_day, U.unit
FROM AnimalFeed AFeed
LEFT JOIN Purchasable Pble ON AFeed.Purchasable_id = Pble.id
LEFT JOIN Countable Cble ON Pble.Countable_id = Cble.id
LEFT JOIN Units U ON AFeed.Units_id = U.id
LEFT JOIN Currency C ON Pble.Currency_id = C.id;

-- 8. List all AnimalFood with their name, quantities, production and ther units
SELECT Cble.name, Cble.quantity, AFood.production_rate_per_day, U.unit
FROM AnimalFood AFood
LEFT JOIN Countable Cble ON AFood.Countable_id = Cble.id
LEFT JOIN Units U ON AFood.Units_id = U.id;

-- 9. List all Animals with their name, quantities, and their food, feed and units
SELECT ACble.name, ACble.quantity, AFeedCble.name AS feed, AFeedCble.quantity, AFeedU.unit, AFoodCble.name AS food, AFoodCble.quantity, AFoodU.unit
FROM Animals A
-- Animals Joins
LEFT JOIN Purchasable APble ON A.Purchasable_id = APble.id
LEFT JOIN Countable ACble ON APble.Countable_id = ACble.id
-- AnimalFeed Joins
LEFT JOIN AnimalFeed AFeed ON AFeed.id = A.AnimalFeed_id
LEFT JOIN Purchasable AFeedPble ON AFeed.Purchasable_id = AFeedPble.id
LEFT JOIN Countable AFeedCble ON AFeedPble.Countable_id = AFeedCble.id
LEFT JOIN Units AFeedU ON AFeed.Units_id = AFeedU.id
-- AnimalFood Joins
LEFT JOIN AnimalFood AFood ON AFood.id = A.AnimalFood_id
LEFT JOIN Countable AFoodCble ON AFood.Countable_id = AFoodCble.id
LEFT JOIN Units AFoodU ON AFood.Units_id = AFoodU.id;

-- 10. List all Weather registries with their respective farm name
SELECT W.date, W.temperature, W.weather, F.name AS farm
FROM Weather W
LEFT JOIN Farms F ON W.Farms_id = F.id;

-- ---------------------
-- 10 RIGHT JOIN QUERIES

-- 1. List all Countable items with their name, quantities and price (if it applies) 
SELECT Cble.name, Cble.quantity, Pble.price_per_unit
FROM Purchasable Pble
RIGHT JOIN Countable Cble ON Pble.Countable_id = Cble.id;

-- 2. List all Crops with their name, quantities
SELECT Cble.name, Cble.quantity, Crops.growth_percentage, Crops.growth_per_day
FROM Countable Cble
RIGHT JOIN Crops ON Crops.Countable_id = Cble.id;

-- 3. List all Grains with their name, quantities and their prices
SELECT Cble.name, Cble.quantity, Pble.price_per_unit
FROM Countable Cble
RIGHT JOIN Purchasable Pble ON Pble.Countable_id = Cble.id
RIGHT JOIN Grains ON Grains.Purchasable_id = Pble.id;

-- 4. List all Products with their name, quantities and their prices OK
SELECT Cble.name, Cble.quantity, Pble.price_per_unit, P.sell_price 
FROM Countable Cble
RIGHT JOIN Purchasable Pble ON Pble.Countable_id = Cble.id
RIGHT JOIN Products P ON P.Purchasable_id = Pble.id;

-- 5. List all Tools with their name, quantities, prices and wear and tear
SELECT Cble.name, Cble.quantity, Pble.price_per_unit, T.wear_and_tear_percentage
FROM Countable Cble
RIGHT JOIN Purchasable Pble ON Pble.Countable_id = Cble.id
RIGHT JOIN Tools T ON T.Purchasable_id = Pble.id;

-- 6. List all BankAccounts with their currency
SELECT BA.bank_name, BA.account_number, BA.balance, C.abreviation AS currency
FROM Currency C
RIGHT JOIN BankAccounts BA ON BA.Currency_id = C.id;

-- 7. List all AnimalFeed with their name, quantities, consumption
SELECT Cble.name, Cble.quantity, Pble.price_per_unit,C.abreviation AS currency, AFeed.consumption_rate_per_day
FROM Countable Cble
RIGHT JOIN Purchasable Pble ON Pble.Countable_id = Cble.id
RIGHT JOIN Currency C ON Pble.Currency_id = C.id
RIGHT JOIN AnimalFeed AFeed ON AFeed.Purchasable_id = Pble.id;

-- 8. List all AnimalFood with their name, quantities, production OK
SELECT Cble.name AS food, Cble.quantity, AFood.production_rate_per_day
FROM Countable Cble
RIGHT JOIN AnimalFood AFood ON AFood.Countable_id = Cble.id;

-- 9. List all Animals with their name, quantities and price
SELECT Cble.name, Cble.quantity, Pble.price_per_unit
FROM Countable Cble
-- Animals Joins
RIGHT JOIN Purchasable Pble ON Pble.Countable_id = Cble.id
RIGHT JOIN Animals A ON A.Purchasable_id = Pble.id;

-- 10. List all Weather registries with their respective farm name
SELECT W.date, W.temperature, W.weather, F.name AS farm
FROM Farms F
RIGHT JOIN Weather W ON W.Farms_id = F.id;

-- ---------------------
-- 10 INNER JOIN QUERIES

-- 1. List all Purchasable items with their name and quantities
SELECT Cble.name, Cble.quantity, Pble.price_per_unit
FROM Purchasable Pble
INNER JOIN Countable Cble ON Pble.Countable_id = Cble.id;

-- 2. Sum the quantity of all crops by state
SELECT CS.name as state, SUM(Cble.quantity) as total_quantity
FROM Crops 
INNER JOIN Countable Cble ON Crops.Countable_id = Cble.id
INNER JOIN CropStates CS ON Crops.CropStates_id = CS.id
GROUP BY state;

-- 3. List all Grains with their name, quantities and their prices
SELECT Cble.name, Cble.quantity, Pble.price_per_unit
FROM Grains 
INNER JOIN Purchasable Pble ON Grains.Purchasable_id = Pble.id
INNER JOIN Countable Cble ON Pble.Countable_id = Cble.id;

-- 4. List all Products with their name, quantities and their prices
SELECT Cble.name, Cble.quantity, Pble.price_per_unit, P.sell_price
FROM Products P
INNER JOIN Purchasable Pble ON P.Purchasable_id = Pble.id
INNER JOIN Countable Cble ON Pble.Countable_id = Cble.id;

-- 5. Sum the quantity of all Tools and average their price by wear and tear
SELECT T.wear_and_tear_percentage, SUM(Cble.quantity) as total_quantity, AVG(Pble.price_per_unit) as average_price
FROM Tools T
INNER JOIN Purchasable Pble ON T.Purchasable_id = Pble.id
INNER JOIN Countable Cble ON Pble.Countable_id = Cble.id
GROUP BY T.wear_and_tear_percentage;

-- 6. Count the number BankAccounts by bank
SELECT BA.bank_name, COUNT(BA.account_number) AS number_of_accounts
FROM BankAccounts BA
INNER JOIN Currency C ON BA.Currency_id = C.id
GROUP BY BA.bank_name;

-- 7. List all AnimalFeed with their name, quantities, consumption and ther units
SELECT Cble.name, Cble.quantity, Pble.price_per_unit,C.abreviation AS currency, AFeed.consumption_rate_per_day, U.unit
FROM AnimalFeed AFeed
INNER JOIN Purchasable Pble ON AFeed.Purchasable_id = Pble.id
INNER JOIN Countable Cble ON Pble.Countable_id = Cble.id
INNER JOIN Units U ON AFeed.Units_id = U.id
INNER JOIN Currency C ON Pble.Currency_id = C.id;

-- 8. Calculate the max production rate of animals by unit
SELECT MAX(AFood.production_rate_per_day) as max_production, U.unit
FROM AnimalFood AFood
INNER JOIN Countable Cble ON AFood.Countable_id = Cble.id
INNER JOIN Units U ON AFood.Units_id = U.id
GROUP BY U.unit;

-- 9. List all Animals with their name, quantities, and their food, feed and units
SELECT ACble.name, ACble.quantity, AFeedCble.name AS feed, AFeedCble.quantity, AFeedU.unit, AFoodCble.name AS food, AFoodCble.quantity, AFoodU.unit
FROM Animals A
-- Animals Joins
INNER JOIN Purchasable APble ON A.Purchasable_id = APble.id
INNER JOIN Countable ACble ON APble.Countable_id = ACble.id
-- AnimalFeed Joins
INNER JOIN AnimalFeed AFeed ON AFeed.id = A.AnimalFeed_id
INNER JOIN Purchasable AFeedPble ON AFeed.Purchasable_id = AFeedPble.id
INNER JOIN Countable AFeedCble ON AFeedPble.Countable_id = AFeedCble.id
INNER JOIN Units AFeedU ON AFeed.Units_id = AFeedU.id
-- AnimalFood Joins
INNER JOIN AnimalFood AFood ON AFood.id = A.AnimalFood_id
INNER JOIN Countable AFoodCble ON AFood.Countable_id = AFoodCble.id
INNER JOIN Units AFoodU ON AFood.Units_id = AFoodU.id;

-- 10. Count the number of all Weather registries by day and farm
SELECT DATE(W.date) as day, AVG(W.temperature), COUNT(W.weather), F.name AS farm
FROM Weather W
INNER JOIN Farms F ON W.Farms_id = F.id
GROUP BY day, farm;

-- ---------------------
-- 1 big QUERY THAT JOINS WHOLE DATABASE

--  List all Animals with their name, quantities, and their food, feed and units
SELECT Cble.name, Cble.quantity, AType.subcategory as animal_type, AFeedCble.name AS animal_feed, AFoodCble.name AS animal_food, AFoodU.unit as animal_food_unit, 
BA.account_number, BA.balance, C.name as currency, F.name AS farm, W.weather, CS.name as crop_state, P.sell_price as product_sell_price, T.wear_and_tear_percentage as tool_wear
FROM Countable Cble
-- Animals Joins
LEFT JOIN Purchasable Pble ON Pble.Countable_id = Cble.id
LEFT JOIN Animals A ON A.Purchasable_id = Pble.id
LEFT JOIN AnimalType AType ON A.AnimalType_id = AType.id
-- AnimalFeed Joins
LEFT JOIN AnimalFeed AFeed ON AFeed.id = A.AnimalFeed_id
LEFT JOIN Purchasable AFeedPble ON AFeed.Purchasable_id = AFeedPble.id
LEFT JOIN Countable AFeedCble ON AFeedPble.Countable_id = AFeedCble.id
LEFT JOIN Units AFeedU ON AFeed.Units_id = AFeedU.id
-- AnimalFood Joins
LEFT JOIN AnimalFood AFood ON AFood.id = A.AnimalFood_id
LEFT JOIN Countable AFoodCble ON AFood.Countable_id = AFoodCble.id
LEFT JOIN Units AFoodU ON AFood.Units_id = AFoodU.id
-- Farms Joins
LEFT JOIN Farms F ON Cble.Farms_id = F.id
LEFT JOIN Weather W ON W.Farms_id  = F.id
-- BankAccounts Joins
LEFT JOIN Currency C ON Pble.Currency_id = C.id
LEFT JOIN BankAccounts BA ON BA.Currency_id = C.id
-- Crops Joins
LEFT JOIN Crops ON Crops.Countable_id = Cble.id
LEFT JOIN CropStates CS ON Crops.CropStates_id = CS.id
-- Products Joins
LEFT JOIN Products P ON P.Purchasable_id = Pble.id
-- Tools Joins
LEFT JOIN Tools T ON T.Purchasable_id = Pble.id
;

-- ---------------------
-- 3 GROUP BY

-- 1. Sum the price for all Animals by category
SELECT AType.category, SUM(ACble.quantity*APble.price_per_unit) as total
FROM Animals A
INNER JOIN Purchasable APble ON A.Purchasable_id = APble.id
INNER JOIN Countable ACble ON APble.Countable_id = ACble.id
INNER JOIN AnimalType AType ON A.AnimalType_id = AType.id
GROUP BY AType.category;

-- 2. Sum the total amount of money in all bank accounts by currency
SELECT C.name, SUM(BA.balance) as total_balance
FROM BankAccounts BA
INNER JOIN Currency C ON BA.Currency_id = C.id
GROUP BY C.name;

-- 3. Count the number of crops by state 
SELECT CS.name AS crop_state, COUNT(*) as number_of_crops
FROM Crops
JOIN CropStates CS ON Crops.CropStates_id = CS.id
GROUP BY CS.name;

-- ---------------------
-- 3 HAVING

-- 1. Temperature average by day by farm after jan 1st.
SELECT DATE(W.date) as date_day, AVG(W.temperature), MIN(W.weather), F.name AS farm
FROM Weather W
INNER JOIN Farms F ON W.Farms_id = F.id
GROUP BY date_day, farm
HAVING date_day>"2025-01-01";

-- 2. List all AnimalFood with their name, quantities, production and ther units, with a production>1.
SELECT Cble.name, Cble.quantity, AFood.production_rate_per_day, U.unit
FROM AnimalFood AFood
INNER JOIN Countable Cble ON AFood.Countable_id = Cble.id
INNER JOIN Units U ON AFood.Units_id = U.id
HAVING AFood.production_rate_per_day>1;

-- 3. List the MAX AnimalsFeed cost by category, except the Others category
SELECT AType.category as animal_type, MAX(AFeedPble.price_per_unit*AFeed.consumption_rate_per_day) as max_cost_per_day
FROM Countable Cble
-- Animals Joins
LEFT JOIN Purchasable Pble ON Pble.Countable_id = Cble.id
LEFT JOIN Animals A ON A.Purchasable_id = Pble.id
LEFT JOIN AnimalType AType ON A.AnimalType_id = AType.id
-- AnimalFeed Joins
LEFT JOIN AnimalFeed AFeed ON AFeed.id = A.AnimalFeed_id
LEFT JOIN Purchasable AFeedPble ON AFeed.Purchasable_id = AFeedPble.id
LEFT JOIN Countable AFeedCble ON AFeedPble.Countable_id = AFeedCble.id
LEFT JOIN Units AFeedU ON AFeed.Units_id = AFeedU.id
GROUP BY animal_type
HAVING animal_type!="Others";




