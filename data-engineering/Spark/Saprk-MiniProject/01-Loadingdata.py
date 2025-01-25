df = spark.read\    ===> this gives u a dataframereader object
          .format('csv')\
          .option('header','true')\
          .option('inferSchema','true')
          .load('you file location')

display(df)


spark is ur spark session object
Spark session is your entry point for the Spark programming APIs.
Every Spark program starts with the Spark Session
because Spark APIs are available to you via the Spark Session object.



global temporary view ?
df.createorReplaceGlobalTempView("fire_global_temp_view");

%sql  ===? add this global_temp in front ??
select * from global_temp.fire_global_temp_view limit 10


====create a database or table using sql===
create database if not exists demo_db
create table if not exists demo_db.fire_service_table

insert into demo_db.fire_service
select * from fire_global_temp_view

Overview of data
callNumber unitId incidentnumber callType calldate watchdate callfinaldisposition availabledttime .... delay

Q1. how many distinct type of calls were made to the fire department??
>> select count(distinct callType) as distinct_calltype_count
from demo_db.fire_service_table
where callType is not null;

Q2. what are the distinct type of calls made to the fire department??
>>  select distinct callType as distinct_callType
    from demo_db.fire_service_table
    where callType is not null;

Q3. most common calltypes ?

>> select callType, count(callType) callType_count
  from demo_db.fire_service_table
  where callType is not null
  group by callType
  order by callType_count desc;

o/p
callType	            callType_count
Medical Emergency	    2
Fire Incident	        2
Traffic Accident	    1

Q4 . what zipcodes accounted for the most common calls ?

select callType,zipcodes, count(*) zip_code_count
  from demo_db.fire_service_table
  where callType is not null
  group by callType,zipcodes
  order by zip_code_count desc;

Q5 -- how many distinct years of data is in the data set ?

callDate. -- bsacially the data column is a string , we need to convert that to a date column and then get/extract distinct years
21/01/2025
21/02/2025
21/01/2025
21/03/2025

select distinct year(to_date(callDate,"dd/mm/yyyy") as distinct_year from demo_db.fire_service_table

Q6 -- what week of the year in 2018 has the most fire calls

select weekofyear(to_date(callDate,"DD/MM/YYYY") as week_year, count(*)
from demo_db.fire_service_table
where year(to_date(callDate,"DD/MM/YYYY") == 2018
group by week_year
order by count desc;






