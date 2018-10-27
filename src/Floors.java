import javax.swing.*;
import java.awt.*;

public class Floors extends JComponent {
    private Color color;
    private int xp;
    private int yp;
    private int w;
    private int h;
    private int[] xpoints;
    private int[] ypoints;
    private int numpoints;

    public Floors() {
        xp = 0;
        yp = 550;
        w = 600;
        h = 30;
        xpoints = new int[]{0,0,600,600};
        ypoints = new int[]{650,680,680,650};
        numpoints = 4;
        color = Color.BLACK;
    }

    public Floors(int[] xpoints, int[] ypoints) {
        this.xp = xpoints[0];
        this.yp = ypoints[0];
        this.xpoints = xpoints;
        this.ypoints = ypoints;
        this.numpoints = xpoints.length;
    }

    //getters and setters
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getYp() {
        return yp;
    }

    public void setYp(int yp) {
        this.yp = yp;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int[] getXpoints() {
        return xpoints;
    }

    public void setXpoints(int[] xpoints) {
        this.xpoints = xpoints;
    }

    public int[] getYpoints() {
        return ypoints;
    }

    public void setYpoints(int[] ypoints) {
        this.ypoints = ypoints;
    }

    public int getNumpoints() {
        return numpoints;
    }

    public void setNumpoints(int numpoints) {
        this.numpoints = numpoints;
    }
}
