import java.net.*;
import java.io.*;
class client{
    public static void main(String[] args)throws Exception{
        Socket s=new Socket("localhost",3333);
        ObjectInputStream objectInputStream = new ObjectInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str="";
        // Receive the string array from the server
        Object receivedObject = objectInputStream.readObject();
        while(receivedObject instanceof String[]) {
            String[] Ques = (String[]) receivedObject;
            System.out.println("ques:"+Ques[0]+"\nA)"+Ques[1]+"\nB)"+Ques[2]);
            str= br.readLine();
            dout.writeUTF(str);
            dout.flush();
            receivedObject = objectInputStream.readObject();
        }
        System.out.println("Total Score"+(int)receivedObject);


        dout.close();
        s.close();
    }}