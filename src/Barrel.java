public class Barrel {
    private int health;
    private int[] xPoints;
    private int[] yPoints;

    public Barrel(){
        health = 1;
        xPoints = new int[]{50,60,70,60};
        yPoints = new int[]{50,60,50,40};
    }

    public int[] getxPoints() {
        return xPoints;
    }

    public int[] getyPoints() {
        return yPoints;
    }

    public void setxPoints(int[] xPoints) {
        this.xPoints = xPoints;
    }

    public void setyPoints(int[] yPoints) {
        this.yPoints = yPoints;
    }

    public void adjustYpoints(int y){
        for(int i=0; i<yPoints.length; i++){
            System.out.println(this.yPoints[i]);
            this.yPoints[i] += y;
        }
    }
}
