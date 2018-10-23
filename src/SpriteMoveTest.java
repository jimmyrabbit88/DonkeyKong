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
        Timer t = new Timer(10, gui);
        t.start();

    }







    int[] xPplayer = new int[]{50, 50,70,70};
    int[] yPplayer = new int[]{100, 120, 120, 100};
    int[] xfloor = new int[]{0, 0, 600, 600};
    int[] yfloor = new int[]{370, 390, 450, 430};

    public Polygon player;
    public Polygon floor;

    @Override
    protected void paintComponent(Graphics g) {

        g.setColor(new Color(0x54CC54));
        player = new Polygon(xPplayer, yPplayer, 4);
        g.fillPolygon(player);


        g.setColor(new Color(0x000000));
        floor = new Polygon(xfloor, yfloor, 4);
        g.fillPolygon(floor);


    }



    @Override
    public void actionPerformed(ActionEvent e) {


     //   if(yPplayer[1]+1 <= yfloor[0]){
     //       for(int i = 0; i< yPplayer.length; i++) {
     //           yPplayer[i] += 5;
     //       }
     //       repaint();
     //   }

        if(floor.contains(xPplayer[2], yPplayer[2])){
            for(int i = 0; i< yPplayer.length; i++) {
                yPplayer[i] -= 1;
            }
            repaint();
        }
        else if(floor.contains(xPplayer[2] -1, yPplayer[2] +1)){
            repaint();
        }
        else{
            for(int i = 0; i< yPplayer.length; i++) {
                yPplayer[i] += 1;
            }
            repaint();
        }

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

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            for(int i = 0; i< xPplayer.length; i++) {
                xPplayer[i] -= 5;
            }

            repaint();
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            for(int i = 0; i< xPplayer.length; i++) {
                yPplayer[i] -= 50;
            }

            repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
