import javax.swing.*;
import java.awt.*;

public class Player extends JComponent {
    private Color color;
    private int xp;
    private int yp;
    private int w;
    private int h;

    //constructor
    public Player() {
        setColor(Color.GREEN);
        setXp(100);
        setYp(500);
        setH(40);
        setW(30);
    }

    //methods
    public void moveRight(){
        xp += 1;
    }

    public void moveLeft(){
        xp -= 1;
    }

    public void moveUp(){
        yp -= 1;
    }

    public void moveDown(){
        yp += 1;
    }

    public Point bottom(){
        return new Point(xp, yp + (h=1));
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
}
