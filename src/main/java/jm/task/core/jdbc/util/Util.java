package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Util {
    private static String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "1224";
    private static Connection connection = getConnection();
    private Statement statement;
    public static SessionFactory sessionFactory = buildSessionFactory();

    public static Connection getConnection() {
        if (connection == null) {
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
    //Реализация для Hibernate
    public static SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mydb?useUnicode=true&serverTimezone=UTC");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "1224");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                //Создает за меня POJO + Последовательно.
                //settings.put(Environment.HBM2DDL_AUTO, "create");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}