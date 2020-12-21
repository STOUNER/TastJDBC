package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    //Настраиваю композицию между DAO и Service
    UserDaoJDBCImpl dataObjectAccess;

    public UserServiceImpl() {
        dataObjectAccess = new UserDaoJDBCImpl();
    }

    public void createUsersTable() {
        dataObjectAccess.createUsersTable();
    }

    public void dropUsersTable() {
        dataObjectAccess.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        dataObjectAccess.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        dataObjectAccess.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return dataObjectAccess.getAllUsers();

    }

    public void cleanUsersTable() {
        dataObjectAccess.cleanUsersTable();
    }
}
