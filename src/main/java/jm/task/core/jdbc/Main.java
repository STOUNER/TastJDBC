package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl controller = new UserDaoHibernateImpl();
        controller.createUsersTable();
        controller.saveUser("Denis","Gorelkin",(byte) 18);
        controller.saveUser("Denis","Gorelkin",(byte) 18);
        controller.saveUser("Denis","Gorelkin",(byte) 18);
        controller.saveUser("Denis","Gorelkin",(byte) 18);
        controller.saveUser("Denis","Gorelkin",(byte) 18);
        controller.getAllUsers();
        controller.cleanUsersTable();
    }
}
