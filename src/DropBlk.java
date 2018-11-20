import javax.swing.*;
import java.awt.*;

public class DropBlk extends Block implements Path {
    private int counter=0;
    private int counter2 = 0;
    private Image image;

    public DropBlk(){
        super();
        super.setColor(Color.RED);
        setImage(image = new ImageIcon("images\\barrel.png").getImage());

    }
    public void chgImg(){
        if (counter%2 == 0){
            setImage(image = new ImageIcon("images\\barrel.png").getImage());
        }
        else{
            setImage(image = new ImageIcon("images\\barrel2.png").getImage());
        }
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void runPath() {
        if(counter < 620){
            incy();
            counter++;
            if (counter%2 == 0){
                incx();
            }
            return;

        }
        if(counter < 950){
            decx();
            counter++;
        }
        if (counter == 950){
            setActive(false);
        }

    }
}
