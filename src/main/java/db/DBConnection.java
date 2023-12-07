package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private final Connection connection;
    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","1234");
    }
    public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
//        if(dbConnection!=null){
//            return dbConnection;
//        }
//        return (dbConnection = new DBConnection());
          return dbConnection!=null? dbConnection:(dbConnection=new DBConnection());
    }

    public Connection getConnection(){
        return connection;
    }
}