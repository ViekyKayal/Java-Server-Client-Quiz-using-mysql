import java.net.*;
import java.io.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

class server{

    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/quiz";
    static final String USER = "root";
    static final String PASS = "password";//write your password

    public static void main(String[] args)throws Exception{
        ServerSocket ss=new ServerSocket(3303);
        Socket s=ss.accept();
        DataInputStream din=new DataInputStream(s.getInputStream());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(s.getOutputStream());

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement= conn.createStatement();
            ResultSet rs= statement.executeQuery("SELECT * FROM qa");
            System.out.println(conn);
            String question,optionA,optionB,ans,userAns;
            int qMarks,TotalMarks=0;

            while (rs.next()){
                /* sent to client */
                question=rs.getString("question");
                optionA=rs.getString("oA");
                optionB=rs.getString("oB");
                String[] ques={question,optionA,optionB};
                objectOutputStream.writeObject(ques);
                userAns= din.readUTF();
                ans=rs.getString("answer");
                System.out.println("question"+question + "answer"+userAns);
                if (ans.equals(userAns)) {
                    System.out.println("ans "+userAns);
                    TotalMarks += rs.getInt("Marks");
                }
            }
            objectOutputStream.writeObject(TotalMarks);
            objectOutputStream.flush();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
        din.close();
        s.close();
        ss.close();
    }}
