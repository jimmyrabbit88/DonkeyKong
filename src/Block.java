import javax.swing.*;
import java.awt.*;

public class Block extends JComponent {
    private Color color;
    private int xp;
    private int yp;
    private int w;
    private int h;

    public Block(){
        color = Color.BLUE;
        xp = 300;
        yp = 300;
        w = 30;
        h = 30;
    }

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

    public void incY(){
        yp += 1;
    }
    public void decY(){
        yp -= 1;
    }

    public void incX(){
        xp += 1;
    }
    public void decX(){
        xp -= 1;
    }

    public Point getBLpoint(){
        return new Point(xp, yp + h);
    }
}
