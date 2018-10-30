import javax.swing.*;
import java.awt.*;

public class Block {
    private Color color;
    private int xp;
    private int yp;
    private int w;
    private int h;
    private static int blockNum;
    private int counter=0;
    private int counterFR;

    public Block(){
        color = Color.WHITE;
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

    public int getBlockNum() {
        return blockNum;
    }

    public Color getColor() {
        return color;
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

    public void setColor(Color color) {
        this.color = color;
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
    public void runPath(){

        if (counter <= 89){
            incy();
            counter++;
            return;
        }
        if (counter == 90){
            counterFR = 0;
        }
        if (counter <= 590){
            fallRight();
            counter++;
            return;
        }

        if(counter <= 681){
            incy();
            counter++;
            return;
        }
        if (counter == 682){
            counterFR = 20;
        }
        if(counter <= 1115){
            fallLeft();
            counter++;
            return;
        }
        if (counter<=1206){
            incy();
            counter++;
            return;
        }
        if (counter == 1207){
            counterFR = 15;
        }
        if (counter <= 1637){
            fallRight();
            counter++;
            return;
        }
        if (counter == 1638){
            counterFR = 20;
        }
        if (counter <= 1729){
            incy();
            counter++;
            return;
        }
        if (counter <= 2160){
            fallLeft();
            counter++;
            return;
        }
        if (counter == 2161){
            counterFR = 20;
        }
        if (counter <= 2251){
            incy();
            counter++;
            return;
        }
        if (counter <= 2684){
            fallRight();
            counter++;
            return;
        }
        if (counter < 2804){
            incy();
            counter++;
            return;
        }
        if (counter <= 3310){
            decx();
            counter++;
            return;
        }
        if (counter == 3311){
            counter = 0;
            counterFR = 0;
            setxp(1);
            setyp(1);
            setColor(Color.WHITE);

        }





    }
    public void fallRight(){
        incx();
        counterFR++;
        if (counterFR == 50){
            incy();
            counterFR = 0;
        }
    }

    public void fallLeft(){
        decx();
        counterFR++;
        if(counterFR == 50){
            incy();
            counterFR = 0;
        }
    }
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
