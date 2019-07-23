package biblio;

/**
 * Класс поиска "злостного читателя".
 * Reader - внутренний класс для хранения экземпляров читателей.
 * createReadersList() - метод для создания списка читателей на основании sql-запроса.
 * searchWorstReader() - метод на основании sql-запросов увелицивает поля читателей 
 *          badreturn - если книга была у читателя дольше 90 дней.
 *          noreturn - если книга была возвращена.
 * далее метод searchWorstReader() сортирует читателей, сравнивая поля невозврата.
 * если значения noreturn равны, то сравниваются поздние возвраты.
 * 
 * сначала вызввать createReadersList(), а потом searchWorstReader().
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class WorstReader {
    
    ArrayList<Reader> readers = new ArrayList<>();
    
    private class Reader{
        Integer id;
        String first_name;
        String last_name;
        Integer badreturn;
        Integer noreturn; 
        
        Reader(Integer id, String first_name, String last_name) {
            this.id = id;
            this.first_name = first_name;
            this.last_name = last_name;
            this.badreturn = 0;
            this.noreturn = 0;
        }

        public Integer getBadreturn() {
            return badreturn;
        }

        public Integer getNoreturn() {
            return noreturn;
        }
        
        void incBadreturn(){
            this.badreturn++;
        }
        
        void incNoreturn() {
            this.noreturn++;
        }

        @Override
        public String toString() {
            return "Reader{" + "id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", badreturn=" + badreturn + ", noreturn=" + noreturn + '}';
        }

    }
    
    public void createReadersList() {
        try {
            SQLCon.useDB();
            Statement stmnt = SQLCon.conn.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                readers.add(new Reader(rs.getInt("stud_id"), rs.getString("first_name"), rs.getString("last_name")));
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
    //
    //  Поиск "Злостного читателя"
    //
    
    public void searchWorstReader() {
        try {
            SQLCon.useDB();
            Statement stmnt = SQLCon.conn.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM history WHERE retrn IS NULL ");
            while(rs.next()) {
                readers.get(rs.getInt("stud_id")-1).incNoreturn();
            }
            rs.close();
            rs = stmnt.executeQuery("SELECT * FROM history WHERE issue < DATE_ADD(retrn, INTERVAL -90 DAY)");
            while (rs.next()) {
                readers.get(rs.getInt("stud_id")-1).incBadreturn();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

           // сортировка читателей.     
        Collections.sort(readers, ((o1, o2) -> {
            if (o1.noreturn < o2.noreturn) return 1;
            else if (o1.noreturn > o2.noreturn) return -1;
            else {
                if (o1.badreturn < o2.badreturn) return 1;
                else if (o1.badreturn > o2.badreturn) return -1;
                else return 0;
            }
        }));
        
        System.out.println("THE WORTS READER IS: " + readers.get(0));
        for (Reader reader : readers) {
            System.out.println(reader.toString());
        }
    }
}
