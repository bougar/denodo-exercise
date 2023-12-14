# Query Exercise
## Create tables and populate date
The following code will create the tables and populate data:
```
-- Create Users table
CREATE TABLE Users (
    UserID INT PRIMARY KEY,
    Age INT
);

-- Insert data into Users table
INSERT INTO Users (UserID, Age) VALUES
    (101, 18),
    (102, 32),
    (103, 44),
    (104, 38),
    (105, 42);

-- Create Purchases table
CREATE TABLE Purchases (
    PurchaseID INT PRIMARY KEY,
    UserID INT,
    PurchaseDate DATE,
    PurchaseTime TIME,
    TotalAmount DECIMAL(10, 2),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Insert data into Purchases table
INSERT INTO Purchases (PurchaseID, UserID, PurchaseDate, PurchaseTime, TotalAmount) VALUES
    (201, 104, '2023-11-22', '10:36', 78),
    (202, 105, '2023-11-22', '12:56', 131),
    (203, 104, '2023-11-22', '16:41', 99),
    (204, 102, '2023-11-20', '18:03', 26),
    (205, 101, '2023-11-21', '19:53', 165),
    (206, 101, '2023-11-20', '20:44', 125),
    (207, 105, '2023-11-20', '22:06', 217),
    (208, 105, '2023-11-21', '22:28', 177),
    (209, 104, '2023-11-22', '23:11', 86),
    (210, 105, '2023-11-23', '23:20', 164);

-- Create Prices table
CREATE TABLE Prices (
    ItemID INT PRIMARY KEY,
    ItemPrice DECIMAL(10, 2)
);

-- Insert data into Prices table
INSERT INTO Prices (ItemID, ItemPrice) VALUES
    (1, 52),
    (2, 26),
    (3, 99),
    (4, 86),
    (5, 79);
```

## Resolution

In order to resolve the proposed exercise we can use the following query:
```
-- Set dates variables
@set start_date = '2023-11-20'
@set end_date = '2023-11-21'
@set start_time = '18:00'
@set end_time = '12:00'

-- Query
SELECT purchaseid, userid, purchasedate, purchasetime, MAX(TotalAmount) AS MaxAmount
FROM Purchases
WHERE PurchaseDate BETWEEN ${start_date} AND ${end_date}
    AND (
        (PurchaseDate = ${start_date} AND PurchaseTime >= ${start_time})
        OR (PurchaseDate > ${start_date})
        OR (PurchaseDate = ${end_date} AND PurchaseTime <= ${end_hour})
    )
    AND TotalAmount > 100
    group by purchaseid, userid, purchasedate, purchasetime
    order by MaxAmount desc
    limit 1;

```

## Clarifications
Take into account that the schema used on the REST API is different from the one used in the query exercise. The schema 
used in has sighly different names for the tables and columns (in english). On the other hand we have decided to merge the `PURCHASE`
date and time into a single column. In this way queries can be simplified and also indexes can be used to improve performance
by defining an index on the new `PURCHASE_DATE` column.