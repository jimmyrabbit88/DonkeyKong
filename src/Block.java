import javax.swing.*;
import java.awt.*;

public abstract class Block {
    private Color color;
    private boolean active;
    private int xp;
    private int yp;
    private int w;
    private int h;
    private static int blockNum;

    public Block(){
        active = true;
        xp = 1;
        yp = 1;
        w = 30;
        h = 30;
        setBlockNum(blockNum);



    }

    public static void setBlockNum(int blockNum) {
        Block.blockNum = blockNum;
        blockNum++;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getBlockNum() {
        return blockNum;
    }

    public Boolean isActive() {
        return active;
    }

    public int getxp() {
        return xp;
    }

    public int getyp() {
        return yp;
    }

    public int getw() {
        return w;
    }

    public int geth() {
        return h;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setxp(int xp) {
        this.xp = xp;
    }

    public void setyp(int yp) {
        this.yp = yp;
    }

    public void setw(int w) {
        this.w = w;
    }

    public void seth(int h) {
        this.h = h;
    }

    public void incy(){
        yp += 1;
    }
    public void decy(){
        yp -= 1;
    }

    public void incx(){
        xp += 1;
    }
    public void decx(){
        xp -= 1;
    }

    public Point getBLpoint(){
        return new Point(xp, yp + h);
    }

    public Point getBRpoint(){
        return new Point(xp + w, yp + h);
    }


    //the Predefined path for each block
    //These may be altered to provide some randomness to the path
    //clean these up if time permits
    public abstract void runPath();

    public abstract void chgImg();

    public abstract Image getImage();



    // centerPoint
    public Point getCP(){
        return new Point(getxp() + (getw()/2), getyp() + (geth()/2));
    }

    //return the four points for each block
    public Point blockPoint(int i){
        switch (i){
            case 0:
                return new Point(getxp() + (getw()/2), getyp());
            case 1:
                return new Point(getxp(), getyp() +(geth()/2));
            case 2:
                return new Point(getxp() + (getw()/2), getyp() + geth());
            case 3:
                return new Point(getxp() + getw(), getyp() + (geth()/2));
            default:
                break;

        }
        return new Point(0,0);
    }
}
