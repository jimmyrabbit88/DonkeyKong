import java.awt.*;

public class DropBlk extends Block implements Path {
    private int counter=0;
    private int counter2 = 0;

    public DropBlk(){
        super();
        super.setColor(Color.RED);

    }

    public void runPath() {
        if(counter < 570){
            incy();
            counter++;
            if (counter%10 == 0){
                incx();
            }
            return;

        }

    }
}
