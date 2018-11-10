import java.awt.*;

public class DropBlk extends Block implements Path {
    private int counter=0;
    private int counter2 = 0;

    public DropBlk(){
        super();
        super.setColor(Color.RED);

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
