package turkishlearning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TurkishLinguisticsApp {
    private static Map<String, String> turkishWords = new HashMap<>();
    private static int score = 0;
    private static int currentWordIndex = 0;

    public static void main(String[] args) {
        turkishWords.put("Merhaba", "Hello");
        turkishWords.put("Teşekkür ederim", "Thank you");
        turkishWords.put("Evet", "Yes");
        turkishWords.put("Hayır", "No");
        turkishWords.put("Lütfen", "Please");

        JFrame frame = new JFrame("Advanced Turkish Linguistics App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel learnPanel = createLearnPanel();
        JPanel testPanel = createTestPanel();
        tabbedPane.addTab("Learn Turkish", learnPanel);
        tabbedPane.addTab("Test Your Knowledge", testPanel);

        frame.add(tabbedPane);

        frame.setVisible(true);
    }

    private static JPanel createLearnPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        for (Map.Entry<String, String> entry : turkishWords.entrySet()) {
            JLabel turkishLabel = new JLabel(entry.getKey());
            JLabel englishLabel = new JLabel(entry.getValue());
            panel.add(turkishLabel);
            panel.add(englishLabel);
        }
        return panel;
    }

    private static JPanel createTestPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));

        JLabel wordLabel = new JLabel("Translate: ");
        JLabel currentWord = new JLabel(getRandomWord());

        JTextField translationField = new JTextField(15);
        JButton checkButton = new JButton("Check");

        JLabel scoreLabel = new JLabel("Score: " + score); // New score label

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userTranslation = translationField.getText();
                String correctTranslation = turkishWords.get(currentWord.getText());

                if (userTranslation.equalsIgnoreCase(correctTranslation)) {
                    JOptionPane.showMessageDialog(panel, "Correct!");
                    score++;
                } else {
                    JOptionPane.showMessageDialog(panel, "Incorrect. The correct answer is '" + correctTranslation + "'.");
                }

                currentWord.setText(getRandomWord());
                translationField.setText("");
                scoreLabel.setText("Score: " + score); // Update the score label
            }
        });

        panel.add(wordLabel);
        panel.add(currentWord);
        panel.add(translationField);
        panel.add(checkButton);
        panel.add(scoreLabel); // Add the score label to the panel

        return panel;
    
    }

    private static String getRandomWord() {
        String[] words = turkishWords.keySet().toArray(new String[0]);
        Random random = new Random();
        int randomIndex = random.nextInt(words.length);
        currentWordIndex = randomIndex;
        return words[currentWordIndex];
    }
}

