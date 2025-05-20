package org.javaee.docfiller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private final String dbname = "people";  // Назва бази даних
    private final String dbuser = "root";    // Логін користувача
    private final String dbpassword = "12345"; // Пароль користувача

    // Підключення до бази даних
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/" + dbname + "?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
        try {
            return DriverManager.getConnection(url, dbuser, dbpassword);
        } catch (SQLException e) {
            System.err.println("Помилка з'єднання з базою даних: " + e.getMessage());
            throw e;
        }
    }
}


