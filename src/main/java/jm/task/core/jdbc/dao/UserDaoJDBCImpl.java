package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util;
    private ResultSet resultSet;
    private DatabaseMetaData dbm;
    private Connection connection;
    private Statement statement;
    final private String createTableSQL = "CREATE TABLE IF NOT EXISTS USER (id INTEGER not NULL AUTO_INCREMENT,  name VARCHAR(255), lastName VARCHAR(255), age INTEGER, PRIMARY KEY (id))";
    final private String dropTableSQL = "DROP TABLE USER";
    final private String addNewUser = "INSERT INTO USER (name,lastName,age) VALUES (";
    final private String getAllRow = "SELECT * FROM USER";
    final private String deleteUser = "DELETE FROM USER WHERE id =";
    final private String truncUser = "TRUNCATE TABLE USER";

    public UserDaoJDBCImpl() {
        try {
            util = new Util();
            connection = util.getConnection();
            statement = util.getStatement();
            dbm = connection.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createUsersTable() {
        try {
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try {
            resultSet = dbm.getTables(null, "test", "USER", null);
            if (resultSet.next()) {
                statement.executeUpdate(dropTableSQL);
                System.out.println("Table delete");
            } else {
                System.out.println("Table not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String addNewUser = this.addNewUser + "'" + name + "'" + "," + "'" + lastName + "'" + "," + age + ")";
        try {

            statement.executeUpdate(addNewUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            statement.executeUpdate(deleteUser + "'" + (int) id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> resultUserList = new LinkedList<>();

        try {
            ResultSet resultSet = statement.executeQuery(getAllRow);
            while (resultSet.next()) {
                resultUserList.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), (byte) resultSet.getInt("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultUserList;
    }


    public void cleanUsersTable() {
        try {
            statement.executeUpdate(truncUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
