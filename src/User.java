public class User {
    private String name;
    private int lives;
    private int score;
    private int multiplyer;

    public User() {
        setName("no name");
        setLives(3);
        setScore(0);
        setMultiplyer(1);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMultiplyer() {
        return multiplyer;
    }

    public void setMultiplyer(int multiplyer) {
        this.multiplyer = multiplyer;
    }

    public void scoreOverBlock(){
        this.score = getScore() + (300 * getMultiplyer());
    }

    public String scoreToString(){
        return " :: " + getScore();
    }

    public String livesToString(){
        return " :: " + getLives();
    }

    public void addMultiply(){
        this.multiplyer += 1;
    }

    public void loseLife(){
        this.lives -= 1;
    }

    public void addlife(){
        this.lives += 1;
    }


}