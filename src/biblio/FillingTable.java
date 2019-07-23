package biblio;

/**
 * Класс для наполнения тестовой базы.
 * createStudent - вносит в базу студента.
 * createBook - вносит в базу книгу.
 * createTestRequest - тестовая запись в базу выдачи книг.
 * fillMyTable - создает и заполняет тестовую базу значениями для тестирования.
 */

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FillingTable {
    
    public FillingTable() {};
    
    public void createStudent (String first_name, String last_name, String birth_date, String passport) {
        try {
            SQLCon.useDB();
            PreparedStatement ps = SQLCon.conn.prepareStatement("INSERT INTO students (first_name, last_name, birth_date, passport) VALUES (?,?,?,?)");
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, birth_date);
            ps.setString(4, passport);
            ps.executeUpdate();   
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void createBook (String title, String author, String publisher, Integer pub_year, Integer sum) {
        try {
            SQLCon.useDB();
            PreparedStatement ps = SQLCon.conn.prepareStatement("INSERT INTO books (title, author, publisher, pub_year, sum) VALUES (?,?,?,?,?)");
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, publisher);
            ps.setInt(4, pub_year);
            ps.setInt(5, sum);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void createTestRequest (Integer stud_id, Integer book_id, String issue, String retrn) {
        try {
            SQLCon.useDB();
            PreparedStatement ps = SQLCon.conn.prepareStatement("INSERT INTO history (stud_id, book_id, issue, retrn) VALUES (?,?,?,?)");
            ps.setInt(1, stud_id);
            ps.setInt(2, book_id);
            ps.setString(3, issue);
            ps.setString(4, retrn);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void fillMyTable() {
        CreateTable ct = new CreateTable();        
        ct.createDB();
        ct.createBooksTable();
        ct.createStudentsTable();
        ct.createHistoryTable();

        FillingTable ft = new FillingTable();   
        ft.createBook("Master i Margarita", "Mikhail Bulgakov", "AST", 1990, 15);
        ft.createBook("Anna Karenina", "Lev Tolstoy", "Prosveshenie", 1988, 4);
        ft.createBook("Sobachye Serdce", "Mikhail Bulgakov", "Prosveshenie", 1986, 5);
        ft.createBook("Bratya Karamazovy", "Fedor Dostoevsky", "Moskva", 1979, 3);
        ft.createBook("Sbornik", "Osip Mandelshtam", "AST", 1995, 6);
        ft.createStudent("Ozzy", "Osbourne", "1948-12-03", "1234567890");
        ft.createStudent("Robbie", "Williams", "1974-02-13", "1432543490");
        ft.createStudent("Jimmy", "Hendrix", "1942-11-27", "1438034490");
        ft.createStudent("Iggy", "Pop", "1947-04-21", "1830564490");
        ft.createStudent("George", "Harrison", "1943-02-25", "1530564445");
        ft.createStudent("Robert", "Plant", "1948-08-20", "1936758445");
        ft.createTestRequest(1, 1, "2009-05-13", "2009-05-25");
        ft.createTestRequest(2, 4, "2019-02-11", "2019-03-25");
        ft.createTestRequest(3, 3, "1970-09-17", null);
        BookIssuing.bookIssue(4, 4, "2018-09-15");
        BookIssuing.bookIssue(5, 5, "2017-03-11");
        BookIssuing.bookIssue(6, 3, "2019-01-11");
        BookIssuing.bookIssue(1, 2, "2018-09-10");
        BookIssuing.bookIssue(2, 3, "2016-10-12");
        BookIssuing.bookIssue(2, 1, "2018-10-10");
        BookIssuing.bookIssue(2, 3, "2018-10-10");
        BookIssuing.bookReturn(4, 4, "2018-10-10");
        BookIssuing.bookReturn(5, 5, "2017-12-01");
        BookIssuing.bookReturn(10, 3, "2019-01-01");


        GetData data = new GetData();
        data.getStudentsFromDB();
        data.getBooksFromDB();
        data.getHistoryFromDB();
        data.getPopularAuthor();
        
        WorstReader wr = new WorstReader();
        wr.createReadersList();
        wr.searchWorstReader();
    }
    
}
