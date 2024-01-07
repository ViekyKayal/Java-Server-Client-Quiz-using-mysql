import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.net.*;
import java.io.*;

public class QuestionFrame extends JFrame {
    private JLabel questionLabel;
    private JRadioButton optionA, optionB;
    private JButton nextButton;

    public QuestionFrame() {
        setTitle("Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(4, 1)); // Adjust rows based on layout needs


        try {Socket s = new Socket("localhost", 3303);

            ObjectInputStream objectInputStream = new ObjectInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            final Object[] receivedObject = {objectInputStream.readObject()};
            String[] Question = (String[]) receivedObject[0];

            questionLabel = new JLabel(Question[0]);
            questionPanel.add(questionLabel);

            optionA = new JRadioButton(Question[1]);
            optionB = new JRadioButton(Question[2]);

            ButtonGroup optionsGroup = new ButtonGroup();
            for (JRadioButton jRadioButton : Arrays.asList(optionA, optionB)) {
                optionsGroup.add(jRadioButton);
                questionPanel.add(jRadioButton);
            }


            nextButton = new JButton("Next");
            questionPanel.add(nextButton);

            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String str="";
                    if (receivedObject[0] instanceof String[]) {
                        String[] Question = (String[]) receivedObject[0];

                        questionLabel.setText(Question[0]);
                        optionA.setText(Question[1]);
                        optionB.setText(Question[2]);
                        if (optionA.isSelected()) {
                            str = "a";
                        }
                        else if (optionB.isSelected()) {
                            str = "b";}
                        try {
                            dout.writeUTF(str);
                            } catch (IOException ex) {
                            throw new RuntimeException(ex);
                            }
                        try {
                            dout.flush();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            receivedObject[0] = objectInputStream.readObject();
                        } catch (IOException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }


                    } else {
                        JOptionPane.showMessageDialog(null, "End of quiz!marks" + receivedObject[0]);
                        dispose(); // Close the frame at the end of the quiz
                        try {
                            dout.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            s.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });

            add(questionPanel, BorderLayout.CENTER);
            pack();
            setLocationRelativeTo(null); // Center the frame
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                QuestionFrame questionFrame = new QuestionFrame();
                questionFrame.setVisible(true);
            }
        });
    }
}
