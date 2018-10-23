import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.concurrent.TimeUnit;

public class Dk  extends JComponent implements KeyListener{
    int playerX = 40;
    int playerY = 600;

    public static void main(String[] args) {
        int winWidth = 600;
        int winHeight = 720;


        JFrame window = new JFrame("Donkey Kong");
        Dk gui = new Dk();
        window.add(gui);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocation(50, 10);
        window.setSize(winWidth, winHeight);
        window.setVisible(true);
        window.addKeyListener(gui);

    }


    @Override
    protected void paintComponent(Graphics g) {
        // build floor
        g.setColor(new Color(0x000000));
        g.fillRect(0, 650, 600, 30);

        // build first platform
        int[] xP1 = new int[]{0, 0, 500, 500};
        int[] yP1 = new int[]{500, 520, 540, 520};
        Polygon p1 = new Polygon(xP1, yP1, 4);
        g.fillPolygon(p1);

        // second platform
        int[] xP2 = new int[]{100, 100, 600, 600};
        int[] yP2 = new int[]{400, 420, 400, 380};
        //Polygon p2 = new Polygon(xP2, yP2, 4);
        //g.fillPolygon(p2);

        // third platform
        int[] xP3 = new int[]{0, 0, 500, 500};
        int[] yP3 = new int[]{270, 290, 310, 290};
        //Polygon p3 = new Polygon(xP3, yP3, 4);
        //g.fillPolygon(p3);

        // fourth platform
        int[] xP4 = new int[]{100, 100, 600, 600};
        int[] yP4 = new int[]{170, 190, 170, 150};
        //Polygon p4 = new Polygon(xP4, yP4, 4);
        //g.fillPolygon(p4);

        // fifth platform
        int[] xP5 = new int[]{0, 0, 100, 500, 500, 100};
        int[] yP5 = new int[]{70, 90, 90, 110, 90, 70};
        //Polygon p5 = new Polygon(xP5, yP5, 6);
        //g.fillPolygon(p5);

        g.setColor(new Color(0x3B962C));
        g.fillOval(playerX, playerY, 30, 50);


    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            playerX += 8;
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            playerX -= 8;
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){


        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

