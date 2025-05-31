package jdbc;

public class SpringJdbc {

    // we can use jdbctemplate ---> in that case we dont  have to use explictally createa a connection object and prepared statement and
    // use resultset to get data ---

    // SoringBOOtAutoconfiguration
    // but if we use a springboot application --> what will happen is the the spring boots auto configuration will do all of these
    // it will look at the class path dependecise or simply look at our classes in the classpath and automatically configures a conection for us

    // spring jdbc is better than normal jdbc -- simply use jdbc template
    // here the comlexities is that we have to write these comlex queries nd when we get the data do
    // mapping of the results which is again a tedious task

    // now why not use JPA Java persistence Api
    // IN  Jpa we use entity -- which like a mapping of the table
    // Hibernate is an implementation of jpa -- jpa is the standard for orm -- mapping of an object to a table is called object relaton mapping

    // that is where jpa comes in
}
