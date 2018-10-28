import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.concurrent.TimeUnit;

public class Dk  extends JComponent implements KeyListener, ActionListener{
    static Floors[] allfloors = new Floors[7];
    static Polygon[] floorPolys = new Polygon[7];
    static Block[] allBlocks = new Block[20];
    static Player player = new Player();



    static int framecount = 0;
    public int TTNB = 300;  // this may be changed here to alter speed of new block eg level 2 may be harder.




    public static void main(String[] args) {
        //this is the main code it generates the floor all the game objects and starts the GUI
        generatefloors();
        generateBlocks();
        startGame();

    }

    public static void startGame() {
        //GUI
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

    // qverwriting the paint component method, this method is called automatically when the window is created or moved.
    // it is called by the repaint method.
    @Override
    protected void paintComponent(Graphics g) {
        //Here the floors are passed to in as attributes to create instances of the polygon class. the floor.contains or floor.intersects methods only work with Polygons.
        for(int i=0; i<6; i++) {
            g.setColor(allfloors[i].getColor());
            floorPolys[i] = new Polygon(allfloors[i].getXpoints(), allfloors[i].getYpoints(), allfloors[i].getNumpoints());
            g.fillPolygon(floorPolys[i]);
        }//end for draw floors


        
        //Here the player is drawn.
        g.setColor(player.getColor());
        g.fillOval(player.getXp(), player.getYp(), player.getW(), player.getH());



        //Here the array of blocks are tested, if the color of the block is Blue it painted to the screen.
        //Initially only the first block is Blue
        /*The framecount is counting the number of times the scene has been repainted when it reaches the TTNB (time to new Block) the first White block in the Blocks array
            that blocks color is set to Blue.
        */
        for(int i=0; i<allBlocks.length; i++){
            if(allBlocks[i].getColor() == Color.BLUE){
                g.setColor(Color.BLUE);
                g.fillOval(allBlocks[i].getxp(), allBlocks[i].getyp(), allBlocks[i].getw(), allBlocks[i].geth());
            }
        }
        if (framecount == TTNB){
            for(int i=0; i<allBlocks.length; i++) {
                if (allBlocks[i].getColor() == Color.WHITE) {
                    allBlocks[i].setColor(Color.BLUE);
                    framecount = 0;
                    break;
                }
            }
            framecount = 0;
        }

        framecount++;
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.moveRight();

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            player.moveLeft();

        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){



        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    // Action method which is called when the timer reaches its limit
    @Override
    public void actionPerformed(ActionEvent e) {
        //itterates through all blocks and if their color is Blue calls the instance method runPath
        //Run Path is a predefined path each block takes to get to the bottom of the game map
        for (Block b : allBlocks) {
            if (b.getColor() == Color.BLUE) {
                b.runPath();
            }
        }


        if (allfloors[0].contains(player.bottom())){
            System.out.println("a");
            player.moveUp();
        }
        else{
            System.out.println("B");
            player.moveDown();
        }




        repaint();



    }// end the action listener

    //Here i am generating the platforms of the game map
    //Do not edit these numbers
    public static void generatefloors(){
        allfloors[0] = new Floors();

        int[] xpoints = new int[]{0, 0, 500, 500};
        int[] ypoints = new int[]{120, 150, 160, 130};
        allfloors[5] = new Floors(xpoints, ypoints);

        xpoints = new int[]{100, 100, 600, 600};
        ypoints = new int[]{230, 260, 250, 220};
        allfloors[4] = new Floors(xpoints, ypoints);

        xpoints = new int[]{0, 0, 500, 500};
        ypoints = new int[]{320, 350, 360, 330};
        allfloors[3] = new Floors(xpoints, ypoints);

        xpoints = new int[]{100, 100, 600, 600};
        ypoints = new int[]{430, 460, 450, 420};
        allfloors[2] = new Floors(xpoints, ypoints);

        xpoints = new int[]{0, 0, 500, 500};
        ypoints = new int[]{520, 550, 560, 530};
        allfloors[1] = new Floors(xpoints, ypoints);
    }

    //Here I am generating the array of blocks
    public static void generateBlocks(){
        for(int i=0; i<allBlocks.length;i++){
            allBlocks[i] = new Block();
        }
        allBlocks[0].setColor(Color.BLUE);
    }

}

