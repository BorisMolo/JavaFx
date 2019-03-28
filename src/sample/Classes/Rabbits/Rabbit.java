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
    public static String typeRabbit;

    private ImageView imageView;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    static public int countsAllRabbits = 0;
    private int identifier = 0;
    private int timeBorn = 0;
    private int timeLife = 0;
    private boolean isDead = false;

    public Rabbit(ImageView imageView,int timeBorn,int timeLife){
        this.imageView = imageView;
        this.timeBorn = timeBorn;
        this.timeLife = timeLife;
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        generateIdentifer();
        countsAllRabbits++;
    }

    private void generateIdentifer(){
        int randomIdentifier = (int)Math.floor(Math.random()*10000);
        identifier = randomIdentifier;
    };

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

    public void updaTimeLiveRabbit(){
        timeLife--;
        if (timeLife<=0) isDead = true;
    }

    public ImageView getImageView(){
        return imageView;
    }

    public int getIdentifier() {
        return identifier;
    }

    public int getTimeBorn() {
        return timeBorn;
    }

    public int getTimeLive() {
        return timeLife;
    }

    public boolean isDead() {
        return isDead;
    }
}
