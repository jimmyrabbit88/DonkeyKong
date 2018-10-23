import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

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
        Timer t = new Timer(100, gui);
        t.start();

    }
    public Rectangle2D floor;
    public Polygon player;
    int[] xPplayer = new int[]{50, 50,70,70};
    int[] yPplayer = new int[]{100, 120, 120, 100};
    int[] xP4 = new int[]{0, 0, 600, 600};
    int[] yP4 = new int[]{370, 390, 390, 370};

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(16));
        player = new Polygon(xPplayer, yPplayer, 4);
        g.fillPolygon(player);


        g.setColor(new Color(0x000000));

        floor.setRect(0,370, 700, 30);
        g.fil(floor);


    }



    @Override
    public void actionPerformed(ActionEvent e) {


        if(player.intersects(floor)){
            System.out.println("xx");
        }


        for(int i = 0; i< yPplayer.length; i++) {
            yPplayer[i] += 5;
        }
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            for(int i = 0; i< xPplayer.length; i++) {
                xPplayer[i] += 5;
            }
            repaint();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
