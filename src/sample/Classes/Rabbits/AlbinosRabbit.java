package sample.Classes.Rabbits;

import javafx.scene.image.ImageView;
import sample.Main;

/*
*  Альбиносы рождаются каждые N2 секунд, при условии, что их количество менее K% от общего числа кроликов,
* в противном случае – не рождаются вовсе.
* */
public class AlbinosRabbit extends Rabbit {

    public static int countAlbinosRabbit = 0;

    public AlbinosRabbit(ImageView _imageView_rabbit, int x, int y){
        super(_imageView_rabbit);
        this.setPosition(x,y);
        countAlbinosRabbit++;
        this.typeRabbit = "Albinos Rabbit";
        //Main.controller.getMainPane().getChildren().addAll(this.imageView);
    }

    public AlbinosRabbit(ImageView _imageView_rabbit){
        super(_imageView_rabbit);
        countAlbinosRabbit++;
        this.typeRabbit = "Albinos Rabbit";
        //Main.controller.getMainPane().getChildren().addAll(this.imageView);

    }
}
