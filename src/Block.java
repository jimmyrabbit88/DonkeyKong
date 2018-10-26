import javax.swing.*;
import java.awt.*;

public class Block {
    private Color color;
    private int xp;
    private int yp;
    private int w;
    private int h;

    public Block(){
        color = Color.BLUE;
        xp = 0;
        yp = 350;
        w = 30;
        h = 30;
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
}
