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
        try {
            PreparedStatement saveUser = connection.prepareStatement("INSERT INTO USER (name,lastName,age) VALUES (?,?,?)");
            saveUser.setString(1, name);
            saveUser.setString(2, lastName);
            saveUser.setByte(3, age);
            saveUser.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement removeUID = connection.prepareStatement("DELETE FROM USER WHERE id = ?");
            removeUID.setLong(1, id);
            removeUID.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> resultUserList = new LinkedList<>();
        try {
            PreparedStatement getAllUsers = connection.prepareStatement("SELECT * FROM USER");
            ResultSet resultSet = getAllUsers.executeQuery();
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
            PreparedStatement truncateUserTable = connection.prepareStatement("TRUNCATE TABLE USER");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
