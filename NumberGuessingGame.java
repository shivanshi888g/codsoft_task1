import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame {
    private JFrame frame;
    private JTextField guessField;
    private JLabel feedbackLabel;
    private JLabel scoreLabel;
    private JButton guessButton;
    private JButton playAgainButton;

    private Random random;
    private int randomNumber;
    private int attempts;
    private int totalScore;

    public NumberGuessingGame() {
        random = new Random();
        totalScore = 0;
        initialize();
        newGame();
    }

    private void initialize() {
        frame = new JFrame("Number Guessing Game");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("Guess the number between 1 and 100");
        titleLabel.setBounds(100, 20, 250, 25);
        frame.getContentPane().add(titleLabel);

        JLabel guessLabel = new JLabel("Your Guess:");
        guessLabel.setBounds(50, 70, 100, 25);
        frame.getContentPane().add(guessLabel);

        guessField = new JTextField();
        guessField.setBounds(150, 70, 50, 25);
        frame.getContentPane().add(guessField);
        guessField.setColumns(10);

        guessButton = new JButton("Guess");
        guessButton.setBounds(210, 70, 100, 25);
        frame.getContentPane().add(guessButton);

        feedbackLabel = new JLabel("");
        feedbackLabel.setBounds(50, 120, 300, 25);
        frame.getContentPane().add(feedbackLabel);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(50, 160, 300, 25);
        frame.getContentPane().add(scoreLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(140, 200, 120, 25);
        frame.getContentPane().add(playAgainButton);
        playAgainButton.setVisible(false);

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makeGuess();
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });
    }

    private void newGame() {
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
        feedbackLabel.setText("");
        guessField.setText("");
        guessButton.setEnabled(true);
        playAgainButton.setVisible(false);
        scoreLabel.setText("Score: " + totalScore);
    }

    private void makeGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;
            if (guess == randomNumber) {
                feedbackLabel.setText("Congratulations! You've guessed the number correctly.");
                totalScore += 10 - attempts + 1;
                scoreLabel.setText("Score: " + totalScore);
                guessButton.setEnabled(false);
                playAgainButton.setVisible(true);
            } else if (guess < randomNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else {
                feedbackLabel.setText("Too high! Try again.");
            }

            if (attempts == 10 && guess != randomNumber) {
                feedbackLabel.setText("Game over! The number was: " + randomNumber);
                guessButton.setEnabled(false);
                playAgainButton.setVisible(true);
            }
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NumberGuessingGame window = new NumberGuessingGame();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
