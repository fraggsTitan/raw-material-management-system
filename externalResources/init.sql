
CREATE TABLE units(unit_name varchar(15) PRIMARY KEY);
INSERT INTO units(unit_name)
VALUES ('kg'),('g'),('mg'),('lb'),('oz'),('L'),('mL'),('kL'),('gal'),
       ('cup'),('tsp'),('tbsp'),('pcs'),('doz'),('packs'),('unit'),
       ('box'),('tray'),('bunch'),('slice');
CREATE TABLE restaurants( id bigserial,rest_name varchar(100),location varchar(100), 
PRIMARY KEY(id),UNIQUE(rest_name,location));

CREATE TABLE raw_materials( material_id bigserial,
rest_id bigint REFERENCES restaurants(id) ON DELETE CASCADE
,material_name varchar(50),
unit_name varchar(15),threshold int,expiry_period INTERVAL,current_stock int,
auto_restock boolean DEFAULT FALSE,restock_period INTERVAL,reorder_quantity int,
PRIMARY KEY(material_id),FOREIGN KEY(unit_name) REFERENCES units(unit_name));

CREATE TABLE suppliers(supplier_id bigserial,supplier_name varchar(100),
PRIMARY KEY(supplier_id));
CREATE TABLE restaurant_supplier(supplier_id bigint REFERENCES suppliers(supplier_id) ON DELETE CASCADE
,restaurant_id bigint REFERENCES restaurants(id) ON DELETE CASCADE,
PRIMARY KEY(supplier_id,restaurant_id));
CREATE TABLE contacts(supplier_id REFERENCES suppliers(supplier_id) ON DELETE CASCADE,
phone_number varchar(15),PRIMARY KEY(supplier_id,phone_number));

CREATE TYPE delivery_status AS ENUM('Ordered','Dispatched','Delivered');
CREATE TABLE material_order(order_id bigserial PRIMARY KEY,
material_id bigint REFERENCES raw_materials(material_id) ON DELETE CASCADE,
supplier_id bigint REFERENCES suppliers(supplier_id) ON DELETE CASCADE,place_date  timestamp,
receive_date timestamp,ordered_units int,received_units int,
status delivery_status);
CREATE TYPE usage_status AS ENUM('Used','Wasted','Unused');
CREATE TABLE material_updates(shipment_id bigserial PRIMARY KEY,
material_id bigint REFERENCES raw_materials(material_id) ON DELETE CASCADE
,units int,date_added timestamp,was_used boolean DEFAULT FALSE,
was_wasted boolean DEFAULT FALSE);

CREATE TABLE wastage(waste_id bigserial PRIMARY KEY,expiry_date timestamp,
stock_wasted int,material_id bigint REFERENCES raw_materials(material_id) ON DELETE CASCADE,
shipment_id bigint REFERENCES material_updates(shipment_id) ON DELETE CASCADE);


CREATE USER rmms WITH password 'rmms!';
GRANT CONNECT ON DATABASE inventory_of_rmms TO rmms;
GRANT USAGE ON SCHEMA public TO rmms;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO rmms;

 