//Player.java
/**
 * The Player class
 */

import javax.swing.*;
import java.awt.*;

public class Player extends JComponent {
    private Image image;
    private int xp;
    private int yp;
    private int w;
    private int h;
    private Point[] playerPoints = new Point[8];
    // jump sequence attributes
    private boolean jump = false;
    private int jumpCount = 0;
    private boolean moving_right;



    //constructor

    /**
     * constructor for the Player class
     * this sets up the default attributes for an Instance of the Player class
     */
    public Player() {
        setImage(image = new ImageIcon("images\\marioRight.png").getImage());
        setXp(100);
        setYp(530);
        setH(30);
        setW(30);
        moving_right = true;
    }


    // methods getters and setters that allow the player to jump
    // these have been kept together to allow them to be removed later if jump sequence is altered

    /** Mutator method to set jump boolean
     * @param jump which boolean you wish to set jump to.
     */
    public void setJump(boolean jump) {
        this.jump = jump;
    }

    /**Method to check if jump is set to true or false
     * @return true or false
     */

    public boolean isJump() {
        return jump;
    }

    /**
     * Method to move the sprites co-ordinate up( Reduce y co-ordinate)
     * @param u integer value to reduce y by.
     */
    public void moveUp(int u){
        yp -= u;
    }
    /**
     * Method to move the sprites co-ordinate down( Increase y co-ordinate)
     * @param d integer value to reduce y by.
     */
    public void moveDown(int d){
        yp += d;
    }

    /**
     * Method to get the JumpCount(an int which stores the number of times the jump sequence has been active for).
     * @return jumpCount.
     */
    public int getJumpCount() {
        return jumpCount;
    }

    /**
     * Method to set the JumpCount(an int which stores the number of times the jump sequence has been active for).
     * @param jumpCount integer value representing the jumpCount
     */
    public void setJumpCount(int jumpCount) {
        this.jumpCount = jumpCount;
    }

    /**
     * Method to add 1 to the JumpCount.
     */
    public void jumpCountadd(){
        jumpCount++;
    }

    /**
     * Method to remove one from the JumpCount
     */
    public void jumpCountless(){
        jumpCount--;
    }
    //end of jump sequence

    //methods

    /**
     * Method to move the sprite Right by 2 px
     */
    public void moveRight(){
        xp += 2;
    }
    /**
     * Method to move the sprite Left by 2 px
     */
    public void moveLeft(){
        xp -= 2;
    }
    /**
     * Method to move the sprite up by 1 px
     */
    public void moveUp(){
        yp -= 1;
    }
    /**
     * Method to move the sprite Down by 1 px
     */
    public void moveDown(){
        yp += 1;
    }

    /**
     * Method to get the Bottom point of the sprite plus 1px
     * @return Point the bottom Point of the Player object plus 1px
     */
    public Point bottomPlus1(){
        return new Point(getXp() + (getW()/2), getYp() + (getH() + 1));
    }
    /**
     * Method to get the Bottom point of the sprite
     * @return Point the bottom Point of the Player object
     */
    public Point bottom(){
        return new Point(getXp() + (getW()/2), getYp() + getH());
    }

    /**
     * Method to change the image of the Player Object
     * @param i an integer which is used to select which images to assign to the Player object.
     */
    public void changeImage(int i){
        switch (i){
            case 0:
                image = new ImageIcon("images\\marioRight.png").getImage();
                break;
            case 1:
                image = new ImageIcon("images\\marioLeft.png").getImage();
                break;
            case 3:
                if(this.moving_right) {
                    image = new ImageIcon("images\\marioJump.png").getImage();
                    break;
                }
                else{
                    image = new ImageIcon("images\\marioJumpLeft.png").getImage();
                    break;
                }
            case 4:
                if(this.moving_right) {
                    image = new ImageIcon("images\\marioRight.png").getImage();
                    break;
                }
                else{
                    image = new ImageIcon("images\\marioLeft.png").getImage();
                    break;
                }
        }
    }


    //getters

    /**
     * Accessor Method to get the Image object assigned to the player object.
     * @return Image the image object.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Accessor Method to get the top left X co-ordinate of the object.
     * @return int the x co-ordinate.
     */
    public int getXp() {
        return xp;
    }
    /**
     * Accessor Method to get the top left Y co-ordinate of the object.
     * @return int the y co-ordinate.
     */
    public int getYp() {
        return yp;
    }
    /**
     * Accessor Method to get the Width of the object.
     * @return int the Width in pixels.
     */
    public int getW() {
        return w;
    }
    /**
     * Accessor Method to get the Height of the object.
     * @return int the Height in pixels.
     */
    public int getH() {
        return h;
    }


    /*public boolean isMoving_right() {
        return moving_right;
    }*/

    //setters

    /**
     * Mutator Method to assign an image to the player object.
     * @param image the image object to assign.
     */
    public void setImage(Image image) {
        this.image = image;
    }
    /**
     * Mutator Method to set the x co-ordinate to the player object.
     * @param xp the x-point.
     */
    public void setXp(int xp) {
        this.xp = xp;
    }
    /**
     * Mutator Method to assign the y co-ordinate to the player object.
     * @param yp the y-point.
     */
    public void setYp(int yp) {
        this.yp = yp;
    }
    /**
     * Mutator Method to assign a width to the player object.
     * @param w the width.
     */
    public void setW(int w) {
        this.w = w;
    }
    /**
     * Mutator Method to assign a height to the player object.
     * @param h the height.
     */
    public void setH(int h) {
        this.h = h;
    }
    /**
     * Mutator Method to set moving_right boolean to true or false.
     * @param moving_right true or false.
     */
    public void setMoving_right(boolean moving_right) {
        this.moving_right = moving_right;
    }

    //atttempting to write a touches algorithim
    //create an array of points for Player
    //these are the outside points of the player and are calculated from the x,y coordinate

    /**
     * Method to generate an array of points which surround the player.
     * This method creates an array of 8 points which surround the player object.
     */

    public void PopPointsArray(){
        playerPoints[0] = new Point(getXp(), getYp());
        playerPoints[1] = new Point(getXp(), getYp() + (getH()/2));
        playerPoints[2] = new Point(getXp(), getYp() + getH());
        playerPoints[3] = new Point(getXp() + (getW()/2), getYp() + getH());
        playerPoints[4] = new Point(getXp() + getW(), getYp() + getH());
        playerPoints[5] = new Point(getXp() + getW(), getYp() + (getH()/2));
        playerPoints[6] = new Point(getXp() + getW(), getYp());
        playerPoints[7] = new Point(getXp() + (getW()/2), getYp());
    }

    /* test if player touchs one of the blocks
    this is done by finding the center point of the block
    and testing it against the 9 points of the player object.
    while this is not perfect its close enough
     */

    /**
     * Method to test if the player object is in contact with a block object
     * @param b the block object to be tested
     * @return true if one of the player objects points are in contact with the points of the block object
     */
    public boolean touches(Block b){
        Point cp = b.getCP();
        PopPointsArray();
        //System.out.println(playerPoints[7].x + " "  +playerPoints[7].y);
        for (Point p:playerPoints){
            if(Math.sqrt(((cp.x - p.x)*(cp.x - p.x)) + ((cp.y - p.y)*(cp.y - p.y))) <= 10){
                return true;

            }
        }
        return false;

    }



}
