package biblio;

/**
 * Класс для получения информации из таблиц.
 * getStudentsFromDB() - выводит список студентов.
 * getBooksFromDB() - выводит список студентов.
 * getHistoryFromDB() - выводит историю выдачи/возврата книг. (ID записи, ID студента, ID книги, дата выдачи(issue), дата возврата(retrn))
 * getPopularAuthor() - возвращает имя самого популярного автора за последний год.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetData {
    
    private Statement stmnt;
    
    public GetData() {};
    
    public void getStudentsFromDB() {
        try {
            SQLCon.useDB();
            stmnt = SQLCon.conn.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM students ORDER BY stud_id");
            while(rs.next()) {
                System.out.println(rs.getInt("stud_id") + ". " + rs.getString("first_name") + " " + rs.getString("last_name"));
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void getBooksFromDB() {
        try {
            SQLCon.useDB();
            stmnt = SQLCon.conn.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM books ORDER BY book_id");
            while(rs.next()) {
                System.out.println(rs.getInt("book_id") + ". " + rs.getString("author") + " \"" + rs.getString("title") + "\"" + " count:" + rs.getInt("sum"));
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void getHistoryFromDB() {
        try {
            SQLCon.useDB();
            stmnt = SQLCon.conn.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM history ORDER BY hist_id");
            while(rs.next()) {
                System.out.println(rs.getInt("hist_id") + ". stud_id:" + rs.getInt("stud_id") + " book_id:" + rs.getInt("book_id") + " issue:" + rs.getString("issue") + " return:" + rs.getString("retrn"));
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public String getPopularAuthor() {
        String author = "";
        
        try {
            SQLCon.useDB();
            stmnt = SQLCon.conn.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT books.author, COUNT(books.author) ac FROM books JOIN history WHERE books.book_id = history.book_id AND history.issue > DATE_ADD(CURDATE(), INTERVAL -1 YEAR) GROUP BY books.author ORDER BY ac DESC LIMIT 1");
            
            while(rs.next()) {
                System.out.println(rs.getString("author"));
                author = rs.getString("author");
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        
        return author;
    }
  
}
