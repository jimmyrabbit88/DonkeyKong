import java.awt.*;

public class PlainBlk extends Block implements Path{
    private int counter=0;
    private int counterFR;

    public PlainBlk(){
        super();
        super.setColor(Color.GREEN);
    }

    public void fallRight(){
        incx();
        counterFR++;
        if (counterFR == 50){
            incy();
            counterFR = 0;
        }
    }

    public void fallLeft(){
        decx();
        counterFR++;
        if(counterFR == 50){
            incy();
            counterFR = 0;
        }
    }



    public void runPath() {
        if (counter <= 89){
            incy();
            counter++;
            return;
        }
        if (counter == 90){
            counterFR = 0;
        }
        if (counter <= 590){
            fallRight();
            counter++;
            return;
        }

        if(counter <= 681){
            incy();
            counter++;
            return;
        }
        if (counter == 682){
            counterFR = 20;
        }
        if(counter <= 1115){
            fallLeft();
            counter++;
            return;
        }
        if (counter<=1206){
            incy();
            counter++;
            return;
        }
        if (counter == 1207){
            counterFR = 15;
        }
        if (counter <= 1637){
            fallRight();
            counter++;
            return;
        }
        if (counter == 1638){
            counterFR = 20;
        }
        if (counter <= 1729){
            incy();
            counter++;
            return;
        }
        if (counter <= 2160){
            fallLeft();
            counter++;
            return;
        }
        if (counter == 2161){
            counterFR = 20;
        }
        if (counter <= 2251){
            incy();
            counter++;
            return;
        }
        if (counter <= 2684){
            fallRight();
            counter++;
            return;
        }
        if (counter < 2804){
            incy();
            counter++;
            return;
        }
        if (counter <= 3310){
            decx();
            counter++;
            return;
        }
        if (counter == 3311){
            counter = 0;
            counterFR = 0;
            setxp(1);
            setyp(1);
            setActive(false);

        }





    }

}
