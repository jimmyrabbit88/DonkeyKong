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
    int[] xPplayer = new int[]{50, 50,70,70};
    int[] yPplayer = new int[]{100, 120, 120, 100};
    int[] xfloor = new int[]{0, 0, 600, 600};
    int[] yfloor = new int[]{370, 390, 450, 430};

    public Polygon player;
    public Polygon floor;

    //public SpriteMoveTest(){
    public static void main(String[] args) {
        startGame();

    }

    public static void startGame(){
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

    //painting the screen
    @Override
    protected void paintComponent(Graphics g) {
        // paint player
        g.setColor(new Color(0x54CC54));
        player = new Polygon(xPplayer, yPplayer, 4);
        g.fillPolygon(player);

        // paint platform floor
        g.setColor(new Color(0x000000));
        floor = new Polygon(xfloor, yfloor, 4);
        g.fillPolygon(floor);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Rectangle playerRect = player.getBounds();
        if (floor.intersects(playerRect)){
            for(int i = 0; i< yPplayer.length; i++) {
                yPplayer[i] -= 1;
            }// end for
            repaint();

        } // end if
        else {
            for (int i = 0; i < yPplayer.length; i++) {
                yPplayer[i] += 1;
            }//end for
            repaint();
        }//end if
    }// end method

    //key input methods
    @Override
    public void keyTyped(KeyEvent e) {

    }//end key typed

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            for(int i = 0; i< xPplayer.length; i++) {
                xPplayer[i] += 5;
            }//end for

            repaint();
        }//end if

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            for(int i = 0; i< xPplayer.length; i++) {
                xPplayer[i] -= 5;
            }//end for

            repaint();
        }//end if

        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            for(int i = 0; i< xPplayer.length; i++) {
                yPplayer[i] -= 50;
            }//end for

            repaint();
        }//end if

    }//end key pressed method

    @Override
    public void keyReleased(KeyEvent e) {

    }//end key released

}// end class
