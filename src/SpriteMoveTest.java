import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SpriteMoveTest extends JComponent implements ActionListener, KeyListener {
    private int ballx = 200;
    private int bally = 200;

    //public SpriteMoveTest(){
    public static void main(String[] args) {
        SpriteMoveTest gui = new SpriteMoveTest();
        JFrame window = new JFrame("my game");
        window.pack();
        window.add(gui);
        window.setLocation(50, 50);
        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.addKeyListener(gui);
        //Timer t = new Timer(100, gui);
        //t.start();

    }


    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(16));
        g.fillOval(ballx,bally, 30,30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //bally += 5;
        //repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            ballx += 5;
            repaint();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
