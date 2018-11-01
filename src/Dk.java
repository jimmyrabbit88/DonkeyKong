import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.concurrent.TimeUnit;

public class Dk  extends JComponent implements KeyListener, ActionListener{
    static Floors[] allfloors = new Floors[6];
    static Polygon[] floorPolys = new Polygon[6];
    static Block[] allBlocks = new Block[20];
    static Player player = new Player();
    static Ladder[] ladders = new Ladder[6];



    static int framecount = 0;
    public int TTNB = 300;  // this may be changed here to alter speed of new block eg level 2 may be harder.

    public boolean onLadder = false;



    public static void main(String[] args) {
        //this is the main code it generates the floor all the game objects and starts the GUI
        generatefloors();
        generateBlocks();
        generateLadders();
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

        for (Ladder l:ladders) {
            g.setColor(l.getColor());
            g.drawRect(l.getXp(), l.getYp(), l.getW(), l.getH());
        }



        //Here the player is drawn.
        g.setColor(player.getColor());
        g.fillRect(player.getXp(), player.getYp(), player.getW(), player.getH());



        //Here the array of blocks are tested, if the color of the block is Blue it painted to the screen.
        //Initially only the first block is Blue
        /*The framecount is counting the number of times the scene has been repainted when it reaches the TTNB (time to new Block) the first White block in the Blocks array
            that blocks color is set to Blue.
        */
        g.setColor(Color.BLUE);
        for(Block b: allBlocks){
            if(b.getColor() == Color.BLUE){
                g.fillOval(b.getxp(), b.getyp(), b.getw(), b.geth());
            }
        }
        if (framecount == TTNB){
            for(Block b : allBlocks) {
                if (b.getColor() == Color.WHITE) {
                    b.setColor(Color.BLUE);
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
        if (e.getKeyCode() == KeyEvent.VK_UP){
            for (Ladder l:ladders){
                if ((player.getXp() < l.getXp() && player.getXp() >= (l.getXp() - 5)) &&
                        (player.bottom().y >= l.getYp() && player.bottom().y <= (l.getYp() + l.getH()))){
                    player.moveUp(3);
                }

            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            for (Ladder l:ladders){
                if ((player.getXp() < l.getXp() && player.getXp() >= (l.getXp() - 5)) &&
                        (player.bottom().y >= (l.getYp() -1) && player.bottom().y <= (l.getYp() + l.getH()))){
                    player.moveDown();
                }

            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            player.setJump(true);
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

        // test to see if the player is in contact withe the floor
        //the 2 player methods used here test the bottom center of the sprite and the bottom center plus 1
        // when the plus1 test is true the player stops,
        // gravity does not occur when player is on a ladder
        for (Ladder l:ladders){
            if ((player.getXp() < l.getXp() && player.getXp() >= (l.getXp() - 5)) &&
                    (player.bottom().y >= l.getYp() && player.bottom().y <= (l.getYp() + l.getH()))){
                onLadder = true;
                break;
            }
            else{
                onLadder = false;
            }

        }

        if(onLadder == false) {
            int floorContainsPlayer = 0;
            for (Polygon f : floorPolys) {
                if (f.contains(player.bottom())) {
                    floorContainsPlayer = 2;

                } else if (f.contains(player.bottomPlus1())) {
                    floorContainsPlayer = 1;
                    break;
                }

            }
            if (floorContainsPlayer == 2) {
                player.moveUp();
            } else if (floorContainsPlayer == 0) {
                player.moveDown();
            }
        }
        //Player Jump sequence
        //When the user presses the space key the player sprite is moved up and is delayed at the top of his jump
        //Hopefully this sequence can be altered later when all movements can be offset
        if (player.isJump()){
            if (player.getJumpCount() < 40){
                player.moveUp(2);
                player.jumpCountadd();
            }
            else if (player.getJumpCount() < 80){
                player.moveUp(1);
                player.jumpCountadd();
            }
            else{
                player.setJump(false);
                player.setJumpCount(0);
            }
        }

       // test for contact between the player and the blocks
       // for now this turns the player Pink, it will eventually reset the level
        for (Block b:allBlocks) {
            if (player.touches(b)) {
                player.setColor(Color.MAGENTA);
            }
        }
       // }

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
        for(int i=0; i<allBlocks.length; i++){
            allBlocks[i] = new Block();
        }
        allBlocks[0].setColor(Color.BLUE);
    }

    // here i am generating the ladders
    public static void generateLadders(){
        ladders[0] = new Ladder(400, 128, 98);
        ladders[1] = new Ladder(200, 239, 86);
        ladders[2] = new Ladder(450, 330, 93);
        ladders[3] = new Ladder(120, 430, 93);
        ladders[4] = new Ladder(400, 528, 122);
        ladders[5] = new Ladder(150, 0, 122);
    }

}

