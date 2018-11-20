import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class highScores extends JFrame implements ActionListener {
    private static int globalon = 0;
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




        container.setLayout(null);
        JButton global = new JButton(new ImageIcon("images\\global.png"));
        global.setBounds(20, 50, 250, 80);
        container.add(global);

        global.addActionListener((ActionEvent e)->{
            globalon = 0;
            this.dispose();
            new highScores();

        });

        JButton mine = new JButton(new ImageIcon("images\\mine.png"));
        mine.setBounds(300, 50, 250, 80);
        container.add(mine);
        mine.addActionListener((ActionEvent e)->{
            if(IntroScreen.getUsername().equals("")){
                IntroScreen.login();
            }
            globalon = 1;
            this.dispose();
            new highScores();



        });

        if(globalon == 0) {
            jta = globalHighScoresText();
        }
        else{
            jta = mineHighScoresText();
        }



        jta.setBounds(100,170, 400, 500);
        container.add(jta);
        setVisible(true);
        repaint();
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
            jta.append(String.format("%-3s::%-25s%-7s\n", i+1,scores.get(i).getName(), scores.get(i).getScore()));
        }
        return  jta;
    }

    public JTextArea mineHighScoresText(){
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
        int j =1;
        for(int i=0; i<size; i++){
            if(IntroScreen.getUsername().equals(scores.get(i).getName())){
                jta.append(String.format("%-3s::%-25s%-7s\n", j,scores.get(i).getName(), scores.get(i).getScore()));
                j++;
            }
        }
        return jta;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
