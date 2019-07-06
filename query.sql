-- Project phase 1
-- inserts
-- Craig Donato - crd69
-- Sam Skupien - sss78

-- BoutiqueCoffee database

-- Table inserts


-- STORE table inserts
-- ------------------------------------------------------------------------------------------------------------------
insert into store (name, address, store_type, gps_long, gps_lat)
values ('Coffee Grounds','123 Pacific Street', 'Kiosk', -124.453125, 6.158027 );
insert into store (name, address, store_type, gps_long, gps_lat)
values ('The Java Cup', '863 Atlantic Drive', 'Cafe', -46.77861, 28142864);
insert into store(name, address, store_type, gps_long, gps_lat)
values ('Caffeine Champion', '1024 Bearing Road', 'Hangout', 179.427242, 55.9538);
-- ------------------------------------------------------------------------------------------------------------------

-- MEMBERLEVEL table inserts
-- ------------------------------------------------------------------------------------------------------------------
insert into memberlevel (name, booster_factor) values ('Steel', 0.00);
insert into memberlevel (name, booster_factor) values ('Bronze', 0.05);
insert into memberlevel (name, booster_factor) values ('Silver', 0.10);
insert into memberlevel (name, booster_factor) values ('Gold', 0.15);
insert into memberlevel (name, booster_factor) values ('Employee', 0.20);
-- ------------------------------------------------------------------------------------------------------------------

-- CUSTOMER table inserts
-- ------------------------------------------------------------------------------------------------------------------
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Sam', 'Skupien', 'sss78@pitt.edu', 5);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Craig', 'Donato', 'crd69@pitt.edu', 5);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Sandra', 'Someone','',1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Peter', 'Perterson', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Billy', 'Billiard', 'poolShark@fake.com', 2);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Julia', 'Jackson', '', 3);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Steve', 'Jackson', '', 4);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Dave', 'Davely', 'dd@fakery.com', 2);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Victor', 'Hickory', 'trees@fake.com', 3);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Monica', 'Labrador', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Audrey', 'Campbell', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Alexander', 'Bailey', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Hank', 'Hill', 'propain@fake.com', 2);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Colin', 'Bower', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Elizabeth', 'Newman', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Ronald', 'McDonald', 'burgers@fake.com', 3);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Gordon', 'Walker', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Molly', 'Robertson', 'random@fake.com', 2);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Harry', 'Skinner', '', 1);
insert into Customer (first_name, last_name, email, memberlevel_id) values ('Jack', 'Wilson', 'jw@fake.com', 3);
-- ------------------------------------------------------------------------------------------------------------------

-- COFFEE table inserts
-- ------------------------------------------------------------------------------------------------------------------
insert into coffee (name, description, intensity, price, reward_points, redeem_points)
values ('Espresso', 'Full flavor', 9, 2.80, 10, 250);
insert into coffee (name, description, intensity, price, reward_points, redeem_points)
values ('Light', 'Weak flavor', 2, 1.50, 5, 100);
insert into coffee (name, description, intensity, price, reward_points, redeem_points)
values ('Medium', 'full flavor', 5, 2.00, 5, 100);
insert into coffee (name, description, intensity, price, reward_points, redeem_points)
values ('Dark', 'bold flavor', 8, 2.00, 5, 100);
insert into coffee (name, description, intensity, price, reward_points, redeem_points)
values ('Green Tea', 'Light and earthy', 1, 1.80, 10, 150);
insert into coffee (name, description, intensity, price, reward_points, redeem_points)
values ('Black Tea', 'Bold and earthy', 5, 2.20, 10, 200);
-- ------------------------------------------------------------------------------------------------------------------

-- OFFERCOFFEE table inserts
-- ------------------------------------------------------------------------------------------------------------------
insert into offercoffee values (1, 2);
insert into offercoffee values (1, 3);
insert into offercoffee values (1, 4);
insert into offercoffee values (2, 1);
insert into offercoffee values (2, 2);
insert into offercoffee values (2, 3);
insert into offercoffee values (2, 5);
insert into offercoffee values (3, 1);
insert into offercoffee values (3, 2);
insert into offercoffee values (3, 3);
insert into offercoffee values (3, 4);
insert into offercoffee values (3, 5);
-- ------------------------------------------------------------------------------------------------------------------

--
-- ------------------------------------------------------------------------------------------------------------------

-- ------------------------------------------------------------------------------------------------------------------

-- Test inserts for view with trigger for purchases
-- ------------------------------------------------------------------------------------------------------------------
insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity)
values (1, 3, 5, 1, 0)

insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity)
values (2, 3, 1, 2, 0)

insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity)
values (20, 1, 2, 1, 0)

insert into CustomerPurchases (customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity)
values (10, 2, 2, 1, 0)
-- ------------------------------------------------------------------------------------------------------------------
