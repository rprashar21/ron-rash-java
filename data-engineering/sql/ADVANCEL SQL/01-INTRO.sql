


EXPALIN SELECT * FROM MYTABLE
select AGE(DATE '2025-09-01',DATE '2024-09-01'); 1 YEAR

SELECT AGE(DATE '2023-04-01', DATE '2022-03-01');
SELECT AGE(TIMESTAMP '2023-04-01 14:30:00', TIMESTAMP '2022-03-01 12:00:00');
SELECT AGE(DATE '2022-03-01', CURRENT_DATE);


SELECT CURRENT_SETTING('TIMEZONE');

select now() + interval '1 day';

--- lets create an enum ===

create TYPE weekday as ENUM('monday','tuesday','wednesday','thursday','friday','saturday','sunday');

create table enum_demo(id serial primary key,day_of_week weekday not null,random character varying);


select  * from enum_demo order by day_of_week; -- makes sure enum is causing the sorting here

insert into enum_demo(day_of_week,random) values('monday','4');
insert into enum_demo(day_of_week,random) values('tuesday','400');
insert into enum_demo(day_of_week,random) values('wednesday','14');
insert into enum_demo(day_of_week,random) values('saturday','42');
insert into enum_demo(day_of_week,random) values('monday','41');


-- add some more columns with restrictions
alter table enum_demo
add column wage float check (wage >=0);

-- insert a value in wage
insert into enum_demo(day_of_week,wage) values('monday',100);

drop table enum_demo;


----- ARRAYS ----
CREATE TABLE array_table(id SERIAL PRIMARY KEY,myarray INTEGER[]);

SELECT * FROM array_table;

INSERT INTO ARRAY_TABLE(myarray) VALUES(ARRAY[1,2,3,4]);

SELECT * FROM ARRAY_TABLE WHERE 2 =ANY(MYARRAY);

SELECT * FROM ARRAY_TABLE WHERE 20 =ANY(MYARRAY); --CHECKS IF VALUE 20 IS THE ARRAY OR NOT

INSERT INTO ARRAY_TABLE(myarray) VALUES(ARRAY[10,20,30,40]);

SELECT * FROM ARRAY_TABLE WHERE ARRAY[10,20,30,40]::INTEGER[] = MYARRAY; -- COMPLETE MATCHES THE ARRAY

-- EXPLODE THE ARRAY --

SELECT ID,UNNEST(MYARRAY) AS EXPLODED FROM ARRAY_TABLE; -- EXPLOEDS THE ARRAY
ID  MYARRAY
1	1
1	2
1	3
1	4
2	10
2	20
2	30
2	40

-----NESTED DATA
CREATE TABLE customers(id serial primary key, name text,address jsonb); -- address i sstored as json

insert into customers(name,address) values('ron','{"street":"94 chatsworth road","city":"croydon","state":"surrey","country":"united kingdom"}');
insert into customers(name,address) values('don','{"street":"101 waddon road","city":"croydon","state":"surrey","country":"united kingdom"}');

select id, name, address->>'street' as street,address->>'city' as city,address->>'state' as state from customers;

CREATE TABLE customers(id serial primary key, name text,address jsonb); -- address i sstored as json

insert into customers(name,address) values('ron','{"street":"94 chatsworth road","city":"croydon","state":"surrey","country":"united kingdom"}');
insert into customers(name,address) values('don','{"street":"101 waddon road","city":"croydon","state":"surrey","country":"united kingdom"}');

select id, name, address->>'street' as street,address->>'city' as city,address->>'state' as state from customers;


--- create an index
create index indx_address_city on customers ((address->>'city'));

---an index  stores a reference to where those records are located in the main table, allowing the database to quickly locate them. Let me explain in more detail:

PostgreSQL creates a functional index, which is essentially a B-tree (or another suitable data structure) storing:
The extracted city values (e.g., "croydon", "london", etc.).
Pointers (or references) to the rows in the table where these city values are found.
Example of what the index might look like internally:

city	Pointer to Row
croydon	Points to Row 1, Row 2
london	Points to Row 3

select * from customers where address->> 'city' ='croydon';

update customers
set address = jsonb_set(address,'{city}','"manchester"')
where address->> 'city' ='london';

select * from customers;

-- remove a field in json like streer

update customers
set address = address - 'street'
where address->> 'city' ='croydon';



