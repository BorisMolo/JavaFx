package sample.Classes.Rabbits;

import javafx.scene.image.ImageView;
import sample.Main;


/*
*
* Обыкновенные кролики рождаются каждые N1 секунд с вероятностью P1.
* */
public class OdinaryRabbit extends Rabbit {

    public static int countOdinaryRabbit = 0;

    public OdinaryRabbit(ImageView _imageView_rabbit, int x, int y){
        super(_imageView_rabbit);
        setPosition(x,y);
        countOdinaryRabbit++;
        typeRabbit = "Odinary Rabbit";
        //Main.controller.getMainPane().getChildren().addAll(this.imageView);
    }


    public OdinaryRabbit(ImageView _imageView_rabbit){
        super(_imageView_rabbit);
        countOdinaryRabbit++;
        this.typeRabbit = "Odinary Rabbit";
        //Main.controller.getMainPane().getChildren().addAll(this.imageView);
    }
}
