package biblio;

/**
 * Класс для регистрации выдачи и возврата книг.
 * bookIssue - выдает книгу, создает запись, регистрирует дату выдачи и уменьшает значение кол-ва.
 * bookReturn - возвращает книгу в базу, регистрирует дату возврата и увеличивает кол-во экземпляров книг.
 */

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookIssuing {
    
    public static void bookIssue (Integer stud_id, Integer book_id, String issue) {
        try {
            SQLCon.useDB();
            PreparedStatement ps = SQLCon.conn.prepareStatement("INSERT INTO history (stud_id, book_id, issue) VALUES (?,?,?)");
            ps.setInt(1, stud_id);
            ps.setInt(2, book_id);
            ps.setString(3, issue);
            ps.executeUpdate();
            
            PreparedStatement upd = SQLCon.conn.prepareStatement("UPDATE books SET sum = sum-1 WHERE book_id = ?");
            upd.setInt(1, book_id);
            upd.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void bookReturn (Integer hist_id, Integer book_id, String retrn) {
        try {
            SQLCon.useDB();
            PreparedStatement ps = SQLCon.conn.prepareStatement("UPDATE history SET retrn = ? WHERE hist_id = ?");
            ps.setString(1, retrn);
            ps.setInt(2, hist_id);
            ps.executeUpdate();
            
            PreparedStatement upd = SQLCon.conn.prepareStatement("UPDATE books SET sum = sum+1 WHERE book_id = ?");
            upd.setInt(1, book_id);
            upd.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
