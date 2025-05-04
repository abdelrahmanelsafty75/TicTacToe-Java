import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame("TicTacToe");
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textLabel = new JLabel();
    JButton[] buttons = new JButton[9];
    Boolean playerTurn;

    public TicTacToe() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.darkGray);

        frame.setVisible(true);

        textLabel.setBackground(new Color(25, 25, 25));
        textLabel.setForeground(Color.CYAN);
        textLabel.setFont(new Font("Times New Roman", Font.PLAIN, 75));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("testing");
        textLabel.setOpaque(true);

        buttonPanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.ITALIC, 150));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);
        titlePanel.add(textLabel);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);
        firstTurn();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (playerTurn) {
                    if (buttons[i].getText().isEmpty()) {
                        buttons[i].setText("X");
                        buttons[i].setForeground(Color.blue);

                        playerTurn = false;
                        textLabel.setText("O turn");
                        checkWinner();
                    }
                } else {
                    if (buttons[i].getText().isEmpty()) {
                        buttons[i].setText("O");
                        buttons[i].setForeground(Color.red);

                        playerTurn = true;
                        textLabel.setText("X turn");
                        checkWinner();
                    }
                }
            }
        }

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
        // For "X"
        if(buttons[0].getText()=="X" &&
                buttons[1].getText()=="X" &&
                buttons[2].getText()=="X"){
            xWins(0,1,2);
        }
        if(buttons[3].getText()=="X" &&
                buttons[4].getText()=="X" &&
                buttons[5].getText()=="X"){
            xWins(3,4,5);
        }
        if(buttons[6].getText()=="X" &&
                buttons[7].getText()=="X" &&
                buttons[8].getText()=="X"){
            xWins(6,7,8);
        }
        if(buttons[0].getText()=="X" &&
                buttons[3].getText()=="X" &&
                buttons[6].getText()=="X"){
            xWins(0,3,6);
        }
        if(buttons[1].getText()=="X" &&
                buttons[4].getText()=="X" &&
                buttons[7].getText()=="X"){
            xWins(1,4,7);
        }
        if(buttons[2].getText()=="X" &&
                buttons[5].getText()=="X" &&
                buttons[8].getText()=="X"){
            xWins(2,5,8);
        }
        if(buttons[0].getText()=="X" &&
                buttons[4].getText()=="X" &&
                buttons[8].getText()=="X"){
            xWins(0,4,8);
        }
        if(buttons[2].getText()=="X" &&
                buttons[4].getText()=="X" &&
                buttons[6].getText()=="X"){
            xWins(2,4,6);
        }

        // For "O"

        if(buttons[0].getText()=="O" &&
                buttons[1].getText()=="O" &&
                buttons[2].getText()=="O"){
            oWins(0,1,2);
        }
        if(buttons[3].getText()=="O" &&
                buttons[4].getText()=="O" &&
                buttons[5].getText()=="O"){
            oWins(3,4,5);
        }
        if(buttons[6].getText()=="O" &&
                buttons[7].getText()=="O" &&
                buttons[8].getText()=="O"){
            oWins(6,7,8);
        }
        if(buttons[0].getText()=="O" &&
                buttons[3].getText()=="O" &&
                buttons[6].getText()=="O"){
            oWins(0,3,6);
        }
        if(buttons[1].getText()=="O" &&
                buttons[4].getText()=="O" &&
                buttons[7].getText()=="O"){
            oWins(1,4,7);
        }
        if(buttons[2].getText()=="O" &&
                buttons[5].getText()=="O" &&
                buttons[8].getText()=="O"){
            oWins(2,5,8);
        }
        if(buttons[0].getText()=="O" &&
                buttons[4].getText()=="O" &&
                buttons[8].getText()=="O"){
            oWins(0,4,8);
        }
        if(buttons[2].getText()=="O" &&
                buttons[4].getText()=="O" &&
                buttons[6].getText()=="O"){
            oWins(2,4,6);
        }


    }
    public void xWins ( int x, int y, int z){
        buttons[x].setBackground(Color.green);
        buttons[y].setBackground(Color.green);
        buttons[z].setBackground(Color.green);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textLabel.setText("X wins");
    }
    public void oWins ( int x, int y, int z){
        buttons[x].setBackground(Color.green);
        buttons[y].setBackground(Color.green);
        buttons[z].setBackground(Color.green);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textLabel.setText("O wins");
    }

}