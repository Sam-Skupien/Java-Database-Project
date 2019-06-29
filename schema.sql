-- Craig Donato & Sam Skupien Phase 1

DROP TABLE Store;
DROP TABLE Coffee;
DROP TABLE Promotion;
DROP TABLE MemberLevel;
DROP TABLE Customer;
DROP TABLE Purchase;
DROP TABLE OfferCoffee;
DROP TABLE HasPromotion;
DROP TABLE PromoteFor;
DROP TABLE BuyCoffee;


CREATE TABLE Store
( Store_ID     SERIAL,
  Name         VARCHAR(20),
  Address      VARCHAR(20),
  Store_Type   VARCHAR(20),
  GPS_Long     FLOAT,
  GPS_Lat      FLOAT,
  CONSTRAINT Store_PK PRIMARY KEY (Store_ID)
);

CREATE TABLE Coffee
( Coffee_ID     SERIAL,
  Name          VARCHAR(20),
  Description   VARCHAR(20),
  Intensity     INTEGER,
  Price         FLOAT,
  Reward_Points FLOAT,
  Redeem_Points FLOAT,
  CONSTRAINT Coffee_PK PRIMARY KEY (Coffee_ID)
);

CREATE TABLE Promotion
( Promotion_ID  SERIAL,
  Name          VARCHAR(20),
  Start_Date    date,
  End_Date      date,
  CONSTRAINT Promotion_PK PRIMARY KEY (Promotion_ID)
);

CREATE TABLE MemberLevel
( MemberLevel_ID    SERIAL,
  Name              VARCHAR(20),
  Booster_Factor    float,
  CONSTRAINT MemberLevel_PK PRIMARY KEY (MemberLevel_ID)
);

CREATE TABLE Customer
( Customer_ID        SERIAL,
  First_Name         VARCHAR(20),
  Last_Name          VARCHAR(20),
  Email              VARCHAR(20),
  MemberLevel_ID     SERIAL,
  Total_Points       FLOAT,
  CONSTRAINT Customer_PK PRIMARY KEY (Customer_ID),
  CONSTRAINT Customer_FK FOREIGN KEY (MemberLevel_ID)
  REFERENCES MemberLevel (MemberLevel_ID)
);

CREATE TABLE Purchase
( Purchase_ID       SERIAL,
  Customer_ID       SERIAL,
  Store_ID          SERIAL,
  Purchase_Time     date,
  CONSTRAINT Purchase_PK PRIMARY KEY (Purchase_ID),
  FOREIGN KEY (Customer_ID) REFERENCES Customer (Customer_ID),
  FOREIGN KEY (Store_ID) REFERENCES Store (Store_ID)
);

CREATE TABLE OfferCoffee
( Store_ID     SERIAL,
  Coffee_ID    SERIAL,
  CONSTRAINT OfferCoffee_PK PRIMARY KEY (Store_ID, Coffee_ID),
  FOREIGN KEY (Store_ID) REFERENCES Store (Store_ID),
  FOREIGN KEY (Coffee_ID) REFERENCES Coffee (Coffee_ID)
);

CREATE TABLE HasPromotion
( Store_ID      SERIAL,
  Promotion_ID  SERIAL,
  CONSTRAINT HasPromotion_PK PRIMARY KEY (Store_ID, Promotion_ID),
  FOREIGN KEY (Store_ID) REFERENCES Store (Store_ID),
  FOREIGN KEY (Promotion_ID) REFERENCES Promotion (Promotion_ID)
);

CREATE TABLE PromoteFor
( Promotion_ID   SERIAL,
  Coffee_ID      SERIAL,
  CONSTRAINT PromoteFor_PK PRIMARY KEY (Promotion_ID, Coffee_ID),
  FOREIGN KEY (Promotion_ID) REFERENCES Promotion (Promotion_ID),
  FOREIGN KEY (Coffee_ID) REFERENCES Coffee (Coffee_ID)
);

CREATE TABLE BuyCoffee
( Purchase_ID         SERIAL,
  Coffee_ID           SERIAL,
  Purchase_Quantity   INTEGER,
  Redeem_Quantity     INTEGER,
  CONSTRAINT BuyCoffee_PK PRIMARY KEY (Purchase_ID, Coffee_ID),
  FOREIGN KEY (Purchase_ID) REFERENCES Purchase (Purchase_ID),
  FOREIGN KEY (Coffee_ID) REFERENCES Coffee (Coffee_ID)
);
