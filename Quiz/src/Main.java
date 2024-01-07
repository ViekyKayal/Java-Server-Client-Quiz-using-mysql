
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/quiz";
    static final String USER = "root";
    static final String PASS = "Kayal@7742";

    public static void main(String[] arg){

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement= conn.createStatement();
            ResultSet rs= statement.executeQuery("SELECT * FROM qa");
            System.out.println(conn);
            while (rs.next()){
                //sent to client
                System.out.println(rs.getString("question"));
            }


        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}