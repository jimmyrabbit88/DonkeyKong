import javax.swing.*;
import java.awt.*;

public class Player extends JComponent {
    private Color color;
    private int xp;
    private int yp;
    private int w;
    private int h;
    private Point[] playerPoints = new Point[8];
    // jump sequence attributes
    private boolean jump = false;
    private int jumpCount = 0;

    //constructor
    public Player() {
        setColor(Color.GREEN);
        setXp(100);
        setYp(530);
        setH(30);
        setW(30);
    }


    // HERE
    // methods getters and setters that allow the player to jump
    // these have been kept together to allow them to be removed later if jump sequence is altered


    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public boolean isJump() {
        return jump;
    }

    public void moveUp(int u){
        yp -= u;
    }
    public void moveDown(int d){
        yp += d;
    }

    public int getJumpCount() {
        return jumpCount;
    }

    public void setJumpCount(int jumpCount) {
        this.jumpCount = jumpCount;
    }

    public void jumpCountadd(){
        jumpCount++;
    }
    public void jumpCountless(){
        jumpCount--;
    }
    //TO HERE

    //methods
    public void moveRight(){
        xp += 2;
    }

    public void moveLeft(){
        xp -= 2;
    }

    public void moveUp(){
        yp -= 1;
    }

    public void moveDown(){
        yp += 1;
    }


    public Point bottomPlus1(){
        return new Point(getXp() + (getW()/2), getYp() + (getH() + 1));
    }

    public Point bottom(){
        return new Point(getXp() + (getW()/2), getYp() + getH());
    }


    //getters

    public Color getColor() {
        return color;
    }

    public int getXp() {
        return xp;
    }

    public int getYp() {
        return yp;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    //setters

    public void setColor(Color color) {
        this.color = color;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setYp(int yp) {
        this.yp = yp;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    //atttempting to write a touches algorithim
    //create an array of points for Player
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
