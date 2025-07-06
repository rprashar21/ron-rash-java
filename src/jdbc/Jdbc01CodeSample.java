package jdbc;

public class Jdbc01CodeSample {

    /*
    *  what is jdbc  --> JDBC is a Java API to connect and execute the query with the database
    *  what is ajdbc driver
    * JDBC Driver is a software component that enables java application to interact with the database. There are 4 types of JDBC drivers:
        JDBC-ODBC bridge driver
        Native-API driver (partially java driver)
        Network Protocol driver (fully java driver)
        Thin driver (fully java driver)
    *
    *  how to connect ?? simple connection
    *  Java program is loading oracle driver to esteblish database connection
    * 1. register a driver Class.forName("oracle.jdbc.driver.OracleDriver")
    * 2. Create the connection object -- getConnection() method of DriverManager class is used to establish connection with the database
    *  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","root");
    * 3. create a statement
    *      Statement Interface helps us to execute quireis with the databse --executQuery,executeBatch
    *      Statement st = connection.createStatement()
*     4.     ResultSet rs=stmt.executeQuery("select * from emp");
    *
    *
    *.// Storing images and videos in db --> For storing image into the database, BLOB (Binary Large Object) datatype is used in the table
    * CREATE TABLE  "IMGTABLE"
   (    "NAME" VARCHAR2(4000),
      "PHOTO" BLOB
   )
    *   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","root");
    *    PreparedStatement ps = con.prepareStatement("insert into IMGTABLE values(?,?) ");
    *    ps.setString(1,"ron");
    *    ps.setBinaryStream(2,new FileInputStream("d:\\g.jpg")); // this where we load the image and give it as 2nd param
    *    ps.executeUpdate();
    *
    * // for file we need clob
    *  PreparedStatement ps=con.prepareStatement(  "insert into filetable values(?,?)");

        File f=new File("d:\\myfile.txt");
        FileReader fr=new FileReader(f);

        ps.setInt(1,101);
        ps.setCharacterStream(2,fr,(int)f.length());
        int i=ps.executeUpdate();
        System.out.println(i+" records affected");
    *
    *
    * CallableStatement interface is used to call the stored procedures and functions.
    Inn this example, we are calling the sum4 function that receives two input and returns the sum of the given number.

    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","system","oracle");

    CallableStatement stmt=con.prepareCall("{?= call sum4(?,?)}");
    stmt.setInt(2,10);
    stmt.setInt(3,43);
    stmt.registerOutParameter(1,Types.INTEGER); //
    stmt.execute();
    *
    *
    *
    *
    *
    *
    *
    * */


}
