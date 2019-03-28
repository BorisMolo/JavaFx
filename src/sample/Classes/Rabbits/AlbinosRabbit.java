package sample.Classes.Rabbits;

import javafx.scene.image.ImageView;

/*
*  Альбиносы рождаются каждые N2 секунд, при условии, что их количество менее K% от общего числа кроликов,
* в противном случае – не рождаются вовсе.
* */
public class AlbinosRabbit extends Rabbit {

    public static int countAlbinosRabbit = 0;

    public AlbinosRabbit(ImageView imageView, int x, int y,int timeBorn, int timeLife){
        super(imageView,timeBorn,timeLife);
        this.setPosition(x,y);
        countAlbinosRabbit++;
        typeRabbit = "Albinos Rabbit";
    }
}
