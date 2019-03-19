package sample.Classes.Rabbits;

import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

/*
*
* Объект – кролик. Бывают 2 видов: обыкновенный и альбинос.
*
* Обыкновенные кролики рождаются каждые N1 секунд с вероятностью P1.
*
* Альбиносы рождаются каждые N2 секунд, при условии, что их количество менее K% от общего числа кроликов,
* в противном случае – не рождаются вовсе.
*/

// Класс наследуется от Pane, и имеет интерфейс поведения IBehavior
public abstract class Rabbit extends Pane implements IBehaviour{

    // объектом является изображение, которое помещается.
    ImageView imageView;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    static public int countsAllRabbits = 0;

    public static String typeRabbit;

    public Rabbit(ImageView _imageView){
        this.imageView = _imageView;
        this.imageView.setFitWidth(WIDTH);
        this.imageView.setFitHeight(HEIGHT);
        countsAllRabbits++;
    }

    @Override
    public void move(int x, int y) {

    }

    @Override
    public void setX(int x) {
        imageView.setTranslateX(x);
    }

    @Override
    public void setY(int y) {
        imageView.setTranslateY(y);
    }

    @Override
    public double getX() {
        return imageView.getX();
    }

    @Override
    public double getY() {
        return imageView.getY();
    }

    public void setPosition(int x, int y){
        this.setX(x);
        this.setY(y);
    }

    public ImageView getImageView(){
        return this.imageView;
    }
    /*
        Could born new rabbit? It depends on time.
     */

}
