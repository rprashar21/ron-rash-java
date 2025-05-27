package jdbc;

public class JDBC01 {

    //jdbc is an api which defines how a client application can connect to a database
    //jdbc template --simpliefies the use of jdbc ,executes core jdbc workflow leaving application code to provide sql and extract results

    // datasource object provides a connection pool for clients to connect to the db -- there are many implementation for datasource
    // like hikaricp or tomcatcp -- hikari is the lightest
    // this datasource points to a list of connections -- so when a client wnat to connect to a db the connetion pool will pick a free
    // connection and provide that to the client and when it is done it will be free

    //jdbc is java database and connectivity to connecto diff databases sql nosql

    // what is jdbc driver ??
    //  to use a particalur datasource from an application u will need a jdbc driver for that data source
    // drivers are basically java libraries which has classes that implement a jdbc api
    // these drivers are usually provide by the database vendor either as a java library jar or java module
    // which we can import in our application

    // why do we need these driver s
    // 1.to connect to a database -- each db will have a different mechanism to connect to it
    // 2. execute dml or ddl commands, execute stored procedures

 // DriverManager               vs                              DataSource
// Core JDBC class that uses a static registry of drivers
// and opens a new connection on
// each call via DriverManager.getConnection(...).              Standard javax.sql.DataSource interface;
//                                                              a configurable factory for Connection objects, often with pooling,
//                                                              JNDI binding, transaction support, etc

 // every call creates a fresh socket   ie there is           typically provided by the implementation (HikariCP, DBCP, Tomcat JDBC, etc.).
 // no pooling here slow poor bad performance


  // what is sql Injection-- > Sql injection is a serious security vulnerablity
  // it occurs when an attacker tries manipulate the input data sent to an applications database query
    //eg using url paramters used in dynamic queries

    // prevent this by using parameterized queries

    // AutoCommit --> tells the db to commit automatically changes after each statement -- good for small statements
    // but if you want a series of statements to get executed try autocommit off -- since its on by default

    // what is transaction -- in db world a set of db operations treated as a single unit of work
    // if any part of the transaction fails ,, rollback happens

    //PREPAREDSTATETMENTS OVER STATEMENTS IN JAVA
    // THEY ARE BETTER THAN STATETMENTS

    // PreparedStatements help prevent sql injections -- we can execute the same statememt multiple times with paramter valuesate
    //Prepared statements are precompiled -- that includes parsing optimizinga and storing the sql statements once
    // parameteerized queries with ? select * from users.user where name =?   they use dynmaic values
    // effiecint reuse -- also it handles automatic type conversion meaning datatypes between java and sql is handled by prepared statemnets

}
