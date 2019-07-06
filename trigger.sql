-- Project phase 1
-- Triggers
-- Craig Donato - crd69
-- Sam Skupien - sss78

-- BoutiqueCoffee database

-- views
-- ------------------------------------------------------------------------------------------------------------------
create or replace view CustomerPurchases as
select p.purchase_id, p.customer_id, c.first_name, c.last_name, p.store_id, s.name as store_name, b.coffee_id,
       o.name as coffee_name, b.purchase_quantity, b.redeem_quantity
from purchase p, buycoffee b, customer c, coffee o, store s
where p.purchase_id = b.purchase_id
    and p.customer_id = c.customer_id
    and b.coffee_id = o.coffee_id
    and p.store_id = s.store_id;

-- ------------------------------------------------------------------------------------------------------------------


-- ------------------------------------------------------------------------------------------------------------------
create or replace function PurchaseInsert() returns trigger as
$$
declare
    purchase_temp_id_pur numeric(15,0);
    purchase_temp_id_buy numeric(15,0);
begin
    select max(purchase_id) into purchase_temp_id_pur
    from purchase;

    select max(purchase_id) into purchase_temp_id_buy
    from buycoffee;

    if purchase_temp_id_pur is null then
        purchase_temp_id_pur := 0;
    end if;

    if purchase_temp_id_buy is null then
        purchase_temp_id_buy := 0;
    end if;

    insert into purchase (purchase_id, customer_id, store_id)
    values (purchase_temp_id_pur + 1,new.customer_id, new.store_id);
    insert into buycoffee (purchase_id, coffee_id, purchase_quantity, redeem_quantity)
    values (purchase_temp_id_buy + 1, new.coffee_id, new.purchase_quantity, new.redeem_quantity);
    return new;
end;
$$ language plpgsql;

drop trigger if exists PurchaseInsert_1 on CustomerPurchases;
create trigger PurchaseInsert_1
    instead of insert on CustomerPurchases
    for each row
execute procedure PurchaseInsert();
