package turkishlearning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class TurkishLinguisticsApp {
    private static Map<String, String> turkishWords = new HashMap<>();
    private static JPanel mainPanel;
    private static JPanel learnPanel;
    private static JPanel testPanel;
    private static CardLayout cardLayout;

    public static void main(String[] args) {
        turkishWords.put("Merhaba", "Hello");
        turkishWords.put("Teşekkür ederim", "Thank you");
        turkishWords.put("Evet", "Yes");
        turkishWords.put("Hayır", "No");
        turkishWords.put("Lütfen", "Please");

        JFrame frame = new JFrame("Basic Turkish Linguistics App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        mainPanel = new JPanel();
        learnPanel = new JPanel();
        testPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        createLearnPanel();
        createTestPanel();

        mainPanel.add(learnPanel, "Learn");
        mainPanel.add(testPanel, "Test");

        JButton learnButton = new JButton("Learn Turkish");
        learnButton.addActionListener(e -> cardLayout.show(mainPanel, "Learn"));

        JButton testButton = new JButton("Test Your Knowledge");
        testButton.addActionListener(e -> cardLayout.show(mainPanel, "Test"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(learnButton);
        buttonPanel.add(testButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static void createLearnPanel() {
        learnPanel.setLayout(new GridLayout(0, 2));
        for (Map.Entry<String, String> entry : turkishWords.entrySet()) {
            JLabel turkishLabel = new JLabel(entry.getKey());
            JLabel englishLabel = new JLabel(entry.getValue());
            learnPanel.add(turkishLabel);
            learnPanel.add(englishLabel);
        }
    }

    private static void createTestPanel() {
        testPanel.setLayout(new GridLayout(0, 3));
        for (Map.Entry<String, String> entry : turkishWords.entrySet()) {
            JLabel turkishLabel = new JLabel(entry.getKey());
            JTextField translationField = new JTextField(10);
            JButton checkButton = new JButton("Check");

            checkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String userTranslation = translationField.getText();
                    if (userTranslation.equalsIgnoreCase(entry.getValue())) {
                        JOptionPane.showMessageDialog(testPanel, "Correct!");
                    } else {
                        JOptionPane.showMessageDialog(testPanel, "Incorrect. The correct answer is '" + entry.getValue() + "'.");
                    }
                }
            });

            testPanel.add(turkishLabel);
            testPanel.add(translationField);
            testPanel.add(checkButton);
        }
    }
}


