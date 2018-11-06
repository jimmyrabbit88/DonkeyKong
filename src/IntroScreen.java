import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class IntroScreen {
    public static void main(String[] args) {
        JFrame menu = new JFrame("Donkey Kong by Jason Dowling");
        menu.setSize(500, 500);
        menu.setLocation(400, 300);
        menu.setLayout(null);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Button newGame = new Button("Start Game");
        newGame.setLocation(100, 100);
        newGame.setSize(100, 100);
        menu.add(newGame);

        Button highScores = new Button("High Scores");
        highScores.setLocation(300, 100);
        highScores.setSize(100, 100);
        menu.add(highScores);

        newGame.addActionListener((ActionEvent e)->{
            menu.setVisible(false);
            Dk.main(null);
            }
        );
        highScores.addActionListener((ActionEvent e)->{

                }
        );

        menu.setVisible(true);
    }
}
