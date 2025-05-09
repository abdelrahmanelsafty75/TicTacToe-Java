import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame("TicTacToe");
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textLabel = new JLabel();
    JButton[] buttons = new JButton[9];
    Boolean playerTurn;

    JButton restartButton = new JButton("Restart");

    Boolean isAIMode = false;

    JPanel homepagePanel = new JPanel();
    JButton playHumanButton = new JButton("Play vs Human");
    JButton playAIButton = new JButton("Play vs AI");
    JButton instructionsButton = new JButton("View Instructions");

    public TicTacToe() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.white);

        frame.setVisible(true);
        homepagePanel.setLayout(null);
        homepagePanel.setBackground(Color.darkGray);

        JLabel homepageLabel = new JLabel("Welcome to TicTacToe Game!");
        homepageLabel.setForeground(Color.CYAN);
        homepageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        homepageLabel.setHorizontalAlignment(JLabel.CENTER);
        homepageLabel.setBounds(150, 100, 500, 60);

        playHumanButton.setBounds(300, 250, 200, 50);
        playHumanButton.setFocusable(false);
        playHumanButton.addActionListener(this);

        playAIButton.setBounds(300, 320, 200, 50); // Positioned below "Play vs Human"
        playAIButton.setFocusable(false);
        playAIButton.addActionListener(this);

        instructionsButton.setBounds(300, 390, 200, 50);
        instructionsButton.setFocusable(false);
        instructionsButton.addActionListener(this);

        homepagePanel.add(homepageLabel);
        homepagePanel.add(playHumanButton);
        homepagePanel.add(playAIButton);
        homepagePanel.add(instructionsButton);

        frame.add(homepagePanel);
    }
    public void setupGame(){
        textLabel.setBackground(new Color(25, 25, 25));
        textLabel.setForeground(Color.CYAN);
        textLabel.setFont(new Font("Times New Roman", Font.PLAIN, 75));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);
        titlePanel.add(textLabel);

        restartButton.setFont(new Font("Arial", Font.PLAIN, 30));
        restartButton.setFocusable(false);
        restartButton.setBackground(new Color(96, 151, 167 ));
        restartButton.setForeground(Color.black);
        restartButton.addActionListener(e -> restartGame());
        titlePanel.add(restartButton, BorderLayout.EAST);

        buttonPanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.ITALIC, 150));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(new Color(96, 151, 167  ));
            buttons[i].addActionListener(this);
        }

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);
        firstTurn();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == playHumanButton) {
            frame.remove(homepagePanel);
            setupGame();
            return;
        }
        if (e.getSource() == playAIButton) {
            isAIMode = true;
            frame.remove(homepagePanel);
            setupGame();
            return;
        }

        if (e.getSource() == instructionsButton) {
            JOptionPane.showMessageDialog(frame,
            "TicTacToe Instructions:\n" +
             "1. Players take turns placing 'X' or 'O' on a 3x3 grid.\n" +
             "2. The first player to get 3 marks in a row (horizontal, vertical, or diagonal) wins.\n" +
             "3. If all cells are filled without a winner, the game is a draw.",
                    "Instructions", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (buttons[i].getText().isEmpty()) {
                    if (isAIMode) {
                        buttons[i].setText("X");
                        buttons[i].setForeground(Color.BLUE);
                        textLabel.setText("AI (O) turn");
                        playerTurn = false;
                        checkWinner();
                        if (gameIsActive()) {
                            aiMove();
                        }
                    } else {

                        if (playerTurn) {
                            buttons[i].setText("X");
                            buttons[i].setForeground(Color.BLUE);
                            textLabel.setText("O turn");
                        } else {
                            buttons[i].setText("O");
                            buttons[i].setForeground(Color.RED);
                            textLabel.setText("X turn");
                        }
                        playerTurn = !playerTurn;
                        checkWinner();
                    }
                }
            }
        }
    }
    public void aiMove() {
        ArrayList<Integer> emptygrids = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().isEmpty()) {
                emptygrids.add(i);
            }
        }

        if (!emptygrids.isEmpty()) {
            int randomIndex = random.nextInt(emptygrids.size());
            int aigrid = emptygrids.get(randomIndex);
            buttons[aigrid].setText("O");
            buttons[aigrid].setForeground(Color.RED);
            textLabel.setText("Your turn (X)");
            playerTurn = true;
            checkWinner();
        }
    }

    public boolean gameIsActive() {
        for (JButton button : buttons) {
            if (button.isEnabled()) {
                return true;
            }
        }
        return false;
    }
    public void firstTurn () {
        if (random.nextInt(2) == 0) {
            playerTurn = true;
            textLabel.setText("X turn");
        } else {
            playerTurn = false;
            textLabel.setText("O turn");
        }

    }
    public void checkWinner() {
        int[][] winCombos = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // row
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // column
                {0, 4, 8}, {2, 4, 6}             // diagonal
        };

        for (int[] combo : winCombos) {  // loop 8 times
            String t1 = buttons[combo[0]].getText();
            String t2 = buttons[combo[1]].getText();
            String t3 = buttons[combo[2]].getText();

            if (t1.equals("X") && t2.equals("X") && t3.equals("X")) {
                xWins(combo[0], combo[1], combo[2]);
                return;
            }
            if (t1.equals("O") && t2.equals("O") && t3.equals("O")) {
                oWins(combo[0], combo[1], combo[2]);
                return;
            }
        }
        boolean draw = true;
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                draw = false;
                break;
            }
        }
        if (draw) {
            drawGame();
        }
    }
    public void xWins ( int x, int y, int z){
       highlightWinner(x, y, z);
        textLabel.setText(isAIMode ? "You win (X)!" : "(X) wins!");
    }
    public void oWins ( int x, int y, int z){
        highlightWinner(x, y, z);
        textLabel.setText(isAIMode ? "AI wins (O)!" : "(O) wins!");
    }
    public void highlightWinner(int x, int y, int z) {
        buttons[x].setBackground(Color.GREEN);
        buttons[y].setBackground(Color.GREEN);
        buttons[z].setBackground(Color.GREEN);
    //  textLabel.setForeground(Color.GREEN);
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
    public void drawGame() {
        textLabel.setText("Draw!");
        textLabel.setForeground(Color.lightGray);
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
    public void restartGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackground(new Color(96, 151, 167  ));
        }
        textLabel.setForeground(Color.CYAN);
        firstTurn();
    }

}