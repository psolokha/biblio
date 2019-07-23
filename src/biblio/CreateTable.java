package biblio;

/**
 * Класс для стоздания тестовой базы и таблиц.
 * createDB - создает тестовую базу.
 * createBooksTable - создает таблицу книг.
 * createStudentsTable - создает таблицу студентов.
 * createHistoryTable - создает таблицу выдачи книг.
 * 
 * 
 */

import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    
    private Statement stmnt;

    public CreateTable() {};
    
    public void createDB(){
         
        try {
            stmnt = SQLCon.conn.createStatement();
            stmnt.executeUpdate("CREATE DATABASE biblio");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void createBooksTable() {
        
        try {
            stmnt = SQLCon.conn.createStatement();
            stmnt.executeUpdate("USE biblio");
            stmnt.executeUpdate("CREATE TABLE books (book_id int(8) not null primary key auto_increment, title varchar(64), author varchar(32), publisher varchar(32), pub_year int(4), sum int(3))");  
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void createStudentsTable() {
        
        try {
            stmnt = SQLCon.conn.createStatement();
            stmnt.executeUpdate("USE biblio");
            stmnt.executeUpdate("CREATE TABLE students (stud_id int(8) not null primary key auto_increment, first_name varchar(20), last_name varchar(20), birth_date datetime, passport int(10))");  
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void createHistoryTable() {
        
        try {
            stmnt = SQLCon.conn.createStatement();
            stmnt.executeUpdate("USE biblio");
            stmnt.executeUpdate("CREATE TABLE history (hist_id int(10) not null primary key auto_increment, stud_id int(8), book_id int(8), issue datetime, retrn datetime)");  
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
