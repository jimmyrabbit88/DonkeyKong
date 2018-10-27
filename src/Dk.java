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
    static Polygon[] floorPolys = new Polygon[7];
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
        window.setLocation(350, 20);
        window.setSize(600, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.addKeyListener(gui);
        Timer t = new Timer(25, gui);
        t.start();
        t.addActionListener(gui);

    }


    @Override
    protected void paintComponent(Graphics g) {
        for(int i=0; i<6; i++) {
            g.setColor(allfloors[i].getColor());
            floorPolys[i] = new Polygon(allfloors[i].getXpoints(), allfloors[i].getYpoints(), allfloors[i].getNumpoints());
            g.fillPolygon(floorPolys[i]);
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
        //test if block is on floor
        if (floorPolys[0].contains(block.getBLpoint())){
            block.decy();
            block.decx();
        }//end if
        //test if block is on any other platform
        else {
            for (int i = 1; i < 6; i++) {
                if (floorPolys[i].contains(block.getBLpoint())) {
                    block.decy();
                    block.incx();
                }//end if
                else {
                    //block.incy();
                }//end else for bottom right

                //if bottom right makes contact
                if (floorPolys[i].contains(block.getBRpoint())) {
                    block.decy();
                    block.decx();

                }//end if
                else {
                    //block.incy();
                }//end else bottom right contact
            }//end for loop testing for contact with floor
            block.incy();
        }//end else for floor test
    repaint();
    }

    public static void generatefloors(){
        allfloors[0] = new Floors();

        int[] xpoints = new int[]{0, 0, 500, 500};
        int[] ypoints = new int[]{120, 150, 170, 140};
        allfloors[5] = new Floors(xpoints, ypoints);

        xpoints = new int[]{100, 100, 600, 600};
        ypoints = new int[]{240, 270, 250, 220};
        allfloors[4] = new Floors(xpoints, ypoints);

        xpoints = new int[]{0, 0, 500, 500};
        ypoints = new int[]{320, 350, 370, 340};
        allfloors[3] = new Floors(xpoints, ypoints);

        xpoints = new int[]{100, 100, 600, 600};
        ypoints = new int[]{440, 470, 450, 420};
        allfloors[2] = new Floors(xpoints, ypoints);

        xpoints = new int[]{0, 0, 500, 500};
        ypoints = new int[]{520, 550, 570, 540};
        allfloors[1] = new Floors(xpoints, ypoints);


    }
}

