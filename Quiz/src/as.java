import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class as extends JFrame {
    private JLabel questionLabel;
    private JRadioButton optionA, optionB, optionC, optionD;
    private JButton nextButton;

    // Sample questions and answers
    private String[] questions = {
            "What is the capital of France?",
            "Who wrote the play 'Romeo and Juliet'?",
            // Add more questions here...
    };

    private String[][] options = {
            {"Paris", "London", "Berlin", "Madrid"},
            {"William Shakespeare", "Charles Dickens", "Jane Austen", "George Orwell"},
            // Add more options for each question...
    };

    private int currentQuestion = 0;

    public as() {
        setTitle("10th Grade Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(6, 1)); // Adjust rows based on layout needs

        questionLabel = new JLabel(questions[currentQuestion]);
        questionPanel.add(questionLabel);

        optionA = new JRadioButton(options[currentQuestion][0]);
        optionB = new JRadioButton(options[currentQuestion][1]);
        optionC = new JRadioButton(options[currentQuestion][2]);
        optionD = new JRadioButton(options[currentQuestion][3]);

        ButtonGroup optionsGroup = new ButtonGroup();
        optionsGroup.add(optionA);
        optionsGroup.add(optionB);
        optionsGroup.add(optionC);
        optionsGroup.add(optionD);

        questionPanel.add(optionA);
        questionPanel.add(optionB);
        questionPanel.add(optionC);
        questionPanel.add(optionD);

        nextButton = new JButton("Next");
        questionPanel.add(nextButton);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentQuestion < questions.length - 1) {
                    currentQuestion++;
                    questionLabel.setText(questions[currentQuestion]);
                    optionA.setText(options[currentQuestion][0]);
                    optionB.setText(options[currentQuestion][1]);
                    optionC.setText(options[currentQuestion][2]);
                    optionD.setText(options[currentQuestion][3]);
                } else {
                    JOptionPane.showMessageDialog(null, "End of quiz!");
                    dispose(); // Close the frame at the end of the quiz
                }
            }
        });

        add(questionPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                as questionFrame = new as();
                questionFrame.setVisible(true);
            }
        });
    }
}
