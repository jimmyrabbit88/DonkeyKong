import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class highScores extends JFrame implements ActionListener {
    private static boolean globalon = true;
    private JTextArea jta;


    public highScores(){
        Container container;

        setTitle("HighScores");
        setSize(600,900);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        container = getContentPane();
        container.setBackground(Color.darkGray);

        jta = new JTextArea();
        jta.setFont(new Font("monospaced", Font.PLAIN, 15));
        jta.setBounds(100,170, 400, 500);


        container.setLayout(null);
        JButton global = new JButton(new ImageIcon("images\\global.png"));
        global.setBounds(20, 50, 250, 80);
        container.add(global);

        global.addActionListener((ActionEvent e)->{
            globalon = true;
            repaint();
        });

        JButton mine = new JButton(new ImageIcon("images\\mine.png"));
        mine.setBounds(300, 50, 250, 80);
        container.add(mine);
        mine.addActionListener((ActionEvent e)->{
            globalon = false;
            repaint();
        });

        if (globalon){
            globalHighScoresText();
            repaint();
        }
        else{
            System.out.println("here");
            mineHighScoresText();
            repaint();
        }

        container.add(jta);
        setVisible(true);
    }


    public JTextArea globalHighScoresText(){
        JTextArea jta = new JTextArea();
        jta.setFont(new Font("monospaced", Font.PLAIN, 15));
        ArrayList<User> scores = IntroScreen.sortedHighScores();
        int size;
        if(scores.size() > 20){
            size = 20;
        }
        else{
            size = scores.size();
        }
        for(int i=0; i<size; i++){
            jta.append(String.format("%-3s::%-25s%-7s\n", i,scores.get(i).getName(), scores.get(i).getScore()));
        }
        return  jta;
    }

    public void mineHighScoresText(){
        jta.removeAll();
        jta.setFont(new Font("monospaced", Font.PLAIN, 15));
        ArrayList<User> scores = IntroScreen.sortedHighScores();
        int size;
        if(scores.size() > 20){
            size = 20;
        }
        else{
            size = scores.size();
        }
        for(int i=0; i<size; i++){
            if(IntroScreen.getUsername().equals(scores.get(i).getName()))
            jta.append(String.format("%-3s::%-25s%-7s\n", i,scores.get(i).getName(), scores.get(i).getScore()));
        }
        System.out.println("hh");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
