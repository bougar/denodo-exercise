-- Create Users table
CREATE TABLE Users
(
    UserID INT PRIMARY KEY,
    Age    INT
);

-- Insert data into Users table
INSERT INTO Users (UserID, Age)
VALUES (101, 18),
       (102, 32),
       (103, 44),
       (104, 38),
       (105, 42);

-- Create Purchases table
CREATE TABLE Purchases
(
    PurchaseID   INT PRIMARY KEY,
    UserID       INT,
    PurchaseDate DATETIME,
    TotalAmount  DECIMAL(10, 2),
    FOREIGN KEY (UserID) REFERENCES Users (UserID)
);

-- Insert data into Purchases table
INSERT INTO Purchases(PurchaseID, UserID, PurchaseDate, TotalAmount)
VALUES
    (201, 104, '2023-11-22 10:36:00', 78),
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
    ItemID    INT PRIMARY KEY,
    ItemPrice DECIMAL(10, 2)
);

-- Insert data into Prices table
INSERT INTO Prices (ItemID, ItemPrice)
VALUES (1, 52),
       (2, 26),
       (3, 99),
       (4, 86),
       (5, 79);