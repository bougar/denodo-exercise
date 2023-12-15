-- Create Users table
CREATE TABLE Users
(
    USER_ID INT PRIMARY KEY,
    AGE     INT
);

-- Insert data into Users table
INSERT INTO Users (USER_ID, AGE)
VALUES (101, 18),
       (102, 32),
       (103, 44),
       (104, 38),
       (105, 42);

-- Create Purchases table
CREATE TABLE Purchases
(
    PURCHASE_ID   INT PRIMARY KEY,
    USER_ID       INT,
    PURCHASE_DATE DATETIME,
    TOTAL_AMOUNT  DECIMAL(10, 2),
    FOREIGN KEY (USER_ID) REFERENCES Users (USER_ID)
);

-- Insert data into Purchases table
INSERT INTO Purchases(PURCHASE_ID, USER_ID, PURCHASE_DATE, TOTAL_AMOUNT)
VALUES (201, 104, '2023-11-22 10:36:00', 78),
       (202, 105, '2023-11-22 12:56:00', 131),
       (203, 104, '2023-11-22 16:41:00', 99),
       (204, 102, '2023-11-20 18:03:00', 26),
       (205, 101, '2023-11-21 19:53:00', 165),
       (206, 101, '2023-11-20 20:44:00', 125),
       (207, 105, '2023-11-20 22:06:00', 217),
       (208, 105, '2023-11-21 22:28:00', 177),
       (209, 104, '2023-11-22 23:11:00', 86),
       (210, 105, '2023-11-23 23:20:00', 164);

-- Create Prices table
CREATE TABLE Prices
(
    ITEM_ID    INT PRIMARY KEY,
    ITEM_PRICE DECIMAL(10, 2)
);

-- Insert data into Prices table
INSERT INTO Prices (ITEM_ID, ITEM_PRICE)
VALUES (1, 52),
       (2, 26),
       (3, 99),
       (4, 86),
       (5, 79);

CREATE INDEX idx_purchase_date ON Purchases (PURCHASE_DATE);