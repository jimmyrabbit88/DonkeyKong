import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Dk  extends JComponent implements KeyListener, ActionListener{
    private static Floors[] allfloors = new Floors[6];
    private static Polygon[] floorPolys = new Polygon[6];
    //private static Block[] allBlocks = new Block[25];
    private static ArrayList<Block> allBlocks = new ArrayList<>();
    private static Player player = new Player();
    private static Ladder[] ladders = new Ladder[6];
    private static User user = new User();
    private static Timer t;



    private static int framecount = 0;
    private static int TTNB = 50;  // this may be changed here to alter speed of new block eg level 2 may be harder.

    private boolean onLadder = false;



    public static void main(String[] args) {
        //this is the main code it generates the floor all the game objects and starts the GUI
        generatefloors();
        generateBlocks();
        generateLadders();
        startGame();

    }

    private static void startGame() {
        //GUI
        Dk gui = new Dk();
        JFrame window = new JFrame("my game");
        window.add(gui);
        window.setLocation(350, 20);
        window.setSize(600, 700);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.addKeyListener(gui);
        t = new Timer(25, gui);
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

        for(Block b: allBlocks){
            if(b.isActive()){
                g.setColor(b.getColor());
                g.fillOval(b.getxp(), b.getyp(), b.getw(), b.geth());
            }
        }
        if (framecount == TTNB){
/*            for(Block b : allBlocks) {
                if (!b.isActive()) {
                    b.setActive(true);
                    framecount = 0;
                    break;
                }
            }
*/          generateBlocks();
            framecount = 0;

        }
        
        if (framecount > TTNB) {
            framecount = 0;
        }
        else {
            framecount++;
        }

        g.setColor(Color.BLACK);
        g.drawString("Score " + user.scoreToString(), 480, 50);
        g.drawString("Lives " + user.livesToString(), 480, 30);

        cleanArrayListOfBlocks();
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
            if (b.isActive()) {
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

        if(!onLadder) {
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
            for (Block b:allBlocks){
                if ((player.getXp() == b.getxp()) && (b.getyp() - player.getYp()) <= 50 && (b.getyp() - player.getYp()) >= 0){
                    user.scoreOverBlock();
                }
            }
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
                dead();
            }
        }
        if (player.getYp() <= 20){
            nextLevel();
        }

        repaint();



    }// end the action listener

    //Here i am generating the platforms of the game map
    //Do not edit these numbers
    private static void generatefloors(){
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
    private static void generateBlocks(){
/*        for(int i=0; i<allBlocks.length; i++){
            if((int)(Math.random()*10) < 7){
                allBlocks[i] = new PlainBlk();
            }
            else{
                allBlocks[i] = new DropBlk();
            }
        }
       allBlocks[0].setActive(true);*/

        if ((int)(Math.random()*10) < 7){
            allBlocks.add(new LadderBlock());
        }
        else{
            allBlocks.add(new DropBlk());
        }
    }

    // here i am generating the ladders
    private static void generateLadders(){
        ladders[0] = new Ladder(400, 128, 98);
        ladders[1] = new Ladder(200, 239, 86);
        ladders[2] = new Ladder(450, 330, 93);
        ladders[3] = new Ladder(120, 430, 93);
        ladders[4] = new Ladder(400, 528, 122);
        ladders[5] = new Ladder(150, 0, 123);
    }


    private static void dead(){
        t.stop();
        if(user.getLives() > 0) {
            allBlocks.removeAll(allBlocks);
            generateBlocks();
            player = new Player();
            user.loseLife();
            framecount=0;
            t.start();
        }
        else {

        }
    }

    private static void nextLevel(){
        t.stop();
        generateBlocks();
        player = new Player();
        t.start();
        user.addlife();
        user.addMultiply();
        framecount = 0;
        TTNB -= 50;

    }

    private static void cleanArrayListOfBlocks(){
        for (Block b : allBlocks) {
            if (!b.isActive()) {
                allBlocks.remove(b);
            }
        }

    }

}

