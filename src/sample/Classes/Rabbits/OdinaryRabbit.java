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
        this.setPosition(x,y);
        countOdinaryRabbit++;
        Main.pane.getChildren().addAll(this.imageView);
        this.typeRabbit = "Odinary Rabbit";
    }

    public OdinaryRabbit(ImageView _imageView_rabbit){
        super(_imageView_rabbit);
        countOdinaryRabbit++;
        Main.pane.getChildren().addAll(this.imageView);
        this.typeRabbit = "Odinary Rabbit";
    }

}
