package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&serverTimezone=UTC";
    private String user = "root";
    private String password = "1224";
    private Connection connection;
    private Statement statement;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            if (!connection.isClosed()) {
                System.out.println("We get connection");
            } else {
                System.out.println("We not get connection");
            }
        } catch (Exception e) {
            System.out.println("We not get connection to DB + " + url);
        }
        return connection;
    }

    public Statement getStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("We not create statement");
        }
        return statement;
    }
}
