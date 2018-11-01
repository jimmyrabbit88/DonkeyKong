import java.awt.*;

public class Ladder {
    private int xp;
    private int yp;
    private int w;
    private int h;
    private Color color;


    public Ladder(int xp, int yp, int h) {
        this.xp = xp;
        this.yp = yp;
        setW(20);
        this.h = h;
        setColor(Color.BLACK);
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
}

