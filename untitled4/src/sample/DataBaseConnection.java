package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    public static final String URL="jdbc:mysql://localhost:3306/java?serverTimezone=UTC";
    public static final String DRIVER="com.mysql.cj.jdbc.Driver";
    public static final String LOGIN="root";
    public static final String PASSWORD="1234";


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection=null;
        Class.forName(DRIVER);
        connection= DriverManager.getConnection(URL,LOGIN,PASSWORD);
        return connection;
    }
    public void closeConnection(Connection connection){
        if (connection==null)return;
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
