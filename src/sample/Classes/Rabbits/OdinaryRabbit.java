package sample.Classes.Rabbits;

import javafx.scene.image.ImageView;


// Обыкновенные кролики рождаются каждые N1 секунд с вероятностью P1.

public class OdinaryRabbit extends Rabbit {

    public static int countOdinaryRabbit = 0;

    public OdinaryRabbit(ImageView imageView, int x, int y,int timeBorn,int timeLife){
        super(imageView,timeBorn,timeLife);
        setPosition(x,y);
        countOdinaryRabbit++;
        typeRabbit = "Odinary Rabbit";
    }

    @Override
    public void updaTimeLiveRabbit(){
        this.timeLife--;
        if (timeLife<0) {
            isDead = true;
            countOdinaryRabbit--;
            super.dicreementcountsAllRabbits();
        }
    }

}
