import java.awt.*;

public class LadderBlock extends Block implements Path{
        private int counter = 0;
        private int counterFR;
        private boolean downLadder;

        public LadderBlock(){
            super();
            super.setColor(Color.BLUE);

            setDownLadder(false);

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

    public boolean isDownLadder() {
        return downLadder;
    }

    public void setDownLadder(boolean downLadder) {
        this.downLadder = downLadder;
    }

    @Override
    public void runPath() {
        if (counter <= 89){
            incy();
            counter++;
            return;
        }
        if (counter == 90){
            counterFR = 0;
        }
        if (counter == 485) {
            random50();
        }
        if (counter <= 590 && downLadder == false){
            fallRight();
            counter++;
            return;
        }
        else if(counter <= 580 && downLadder == true) {
            incy();
            counter++;
            return;
        }
        if(counter == 581){
            counter = 790;
            counterFR = 20;
        }

        if(counter <= 681){
            incy();
            counter++;
            return;
        }
        if (counter == 682){
            counterFR = 20;
        }
        // now on platform 2
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

        public void random50(){
            if(Math.random() < .5f){
                setDownLadder(true);
            }
        }



}
