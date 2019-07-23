package biblio;

/**
 * Подключение к базе MySQL под юзером root и паролем 123321
 * Изменить CONN_STRING для корректного подключения.
 * 
 * Метод useDB() для подключения к базе.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLCon {
    
    public static final String CONN_STRING = "jdbc:mysql://localhost:3306/?user=root&password=123321&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static Connection conn = null;
    static {
        
        try {
            conn = DriverManager.getConnection(CONN_STRING);
        } catch (SQLException ex) {
            System.out.println("Cannot open connection" + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void useDB() {
        try {
            conn.createStatement().executeUpdate("USE biblio");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
