data_frame = spark.read('')
                   .format('csv')
                   .option('header','true')
                   .option('inferSchema','true')
                   .load('file location')

display(data_frame)

// here we will learn some trasformations

1. rename the columns -- > read ur data frame into renamed dataframe
data_frame_renamed = data_frame.withCoulumnRenamed("old_column_name","new_column_name")

eg - data_frame_renamed = data_frame.withCoulumnRenamed("call number","callNumber")
                                     .withCoulumnRenamed("date expired","dateExpired")
                                     .withCoulumnRenamed("call date","callDate")
                                     .withCoulumnRenamed("call number ","callNumber")

// spark dataframes are immutable

2. we can check the schema
data_frame_renamed.printSchema()

3. transform the data type pf a certain coulumn say call date is in string and we need to convert the column data type to date
from pyspark.sql.functions import *

data_frame = data_frame_renamed
                              .withCoulmn("callDate",to_date("callDate","MM/DD/YYYY")
                              .withCoulmn("avaliabledtm",to_timestamp("avaliabledtm","MM/DD/YYYY hh:mm:ss a")
                              .withCoulmn("delay", round("delay",2))


 withCoulumnRenamed -- rename the columns
 withCoulmn --  transform the column to data type or round the values
 printSchema() -- utility method to

 lets cache the dataframe into the memory --     data_frame.cache()

lets do the same questions done in sql to data frame or pyspark

lets create temporary view
data_frame.createOrReplaceTemporaryView('fire_temp_view')

Q1. how many distinct type of calls were made to the fire department??
>> select count(distinct callType) as distinct_calltype_count
from demo_db.fire_service_table
where callType is not null;

--> spark session object give us many methods to create a dataframes
q1_sql_data_frame = spark.sql('
select count(distinct callType) as distinct_calltype_count
from fire_temp_view
where callType is not null

 ');
 display(q1_sql_data_frame)


