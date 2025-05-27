package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC03 {

    public static void main(String[] args) {

        // load the properties file
        // some data source var datasource = new HikariDataSource();
        // datasource.
                                     // replace this with datasource
        String url = "jdbc:postgresql://localhost:5425/sjpviewstore?user=pgreadonly&password=pgreadonly&sslmode=require";
        try(Connection connection = DriverManager.getConnection(url)){

         String sql = "Select * from emp where id=? and name=?";
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setInt(1, 1);
         preparedStatement.setString(2,"ron");
         ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
