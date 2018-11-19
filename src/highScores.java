import javax.swing.*;
import java.awt.*;

public class highScores extends JFrame {



    public highScores(){
        Container container;
        setSize(500,900);
        setLocationRelativeTo(null);
        setTitle("HighScores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        container = getContentPane();

        JButton global = new JButton("Global");
        setBounds(50, 50, 100, 100);
        global.setBackground(Color.RED);
        global.setForeground(Color.black);
        container.add(global);


        setVisible(true);
    }
}
