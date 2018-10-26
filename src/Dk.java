import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.concurrent.TimeUnit;

public class Dk  extends JComponent implements KeyListener, ActionListener{
    //static Floors floor1 = new Floors();
    static Floors[] allfloors = new Floors[7];
    static Block block = new Block();




    public static void main(String[] args) {
        generatefloors();
        startGame();

    }

    public static void startGame() {
        Dk gui = new Dk();
        JFrame window = new JFrame("my game");
        window.pack();
        window.add(gui);
        window.setLocation(50, 50);
        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.addKeyListener(gui);
        Timer t = new Timer(25, gui);
        t.start();
        t.addActionListener(gui);

    }


    @Override
    protected void paintComponent(Graphics g) {
        for(int i=0; i<2; i++) {
            g.setColor(allfloors[i].getColor());
            g.fillPolygon(allfloors[i].getXpoints(), allfloors[i].getYpoints(), allfloors[i].getNumpoints());
        }

        //g.setColor(floor1.getColor());
        //g.fillPolygon(floor1.getXpoints(), floor1.getYpoints(), floor1.getNumpoints());


        g.setColor(block.getColor());
        g.fillOval(block.getxp(), block.getyp(), block.getw(), block.geth());

    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){

        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){


        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<2; i++) {
            //System.out.println(allfloors[i].getYp());
            if (allfloors[i].contains(block.getBLpoint())) {
                block.decy();
                block.incx();

            } else {
                //System.out.println(block.getBLpoint().y);
                block.incy();
            }
        }

    repaint();
    }

    public static void generatefloors(){
        allfloors[0] = new Floors();
        int[] xpoints = new int[]{0,0,500,500};
        int[] ypoints = new int[]{420,450,480,450};
        allfloors[1] = new Floors(xpoints, ypoints);
    }
}

