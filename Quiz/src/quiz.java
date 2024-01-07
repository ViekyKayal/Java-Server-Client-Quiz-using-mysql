import java.sql.*;

public class quiz {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/si";
    static final String USER = "root";
    static final String PASS = "Kayal@7742";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            // Retrieving questions with their answers
            String sql = "SELECT * FROM qa";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String questionText = rs.getString("question_text");
                String answerText = rs.getString("answer_text");
                boolean isCorrect = rs.getBoolean("is_correct");

                System.out.println("Question: " + questionText);
                System.out.println("Answer: " + answerText + " (Correct: " + isCorrect + ")");
                System.out.println();
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
