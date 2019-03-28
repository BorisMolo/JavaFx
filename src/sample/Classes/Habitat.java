package sample.Classes;

import javafx.scene.image.Image;
import java.util.ArrayList;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import sample.Classes.Rabbits.AlbinosRabbit;
import sample.Classes.Rabbits.OdinaryRabbit;
import sample.Classes.Rabbits.Rabbit;


/*
* Создать класс Habitat (среда), определяющий размер рабочей области и хранящий массив объектов,
* с параметрами, заданными вариантом.
* Предусмотреть в классе метод Update, вызывающийся по таймеру и получающий на вход время, прошедшее от начала симуляции.
* В данном методе должны генерироваться новые объекты и помещаться в поле визуализации в случайном месте.
* Визуализация объекта - использовать готовые небольшие картинки;

*
* */
public class Habitat {

    // размер и картинка заднего фона
    private static final int WIDTH = 400;
    private static final int HEIGH = 400;
    private static final Image imageBackground = new Image("sample/Images/GreenField.png");

    // картинки для кроликов
    private static final Image imageAlbinosRabbit = new Image("sample/Images/AlbinosRabbit.png");
    private static final Image imageOdinaryRabbit = new Image("sample/Images/OdinaryRabbit.png");

    private int N1; // Время рождения обыкновенного кролика(каждые N1 секунды)
    private int P1; // Вероятность % рождения обыкновенного кролика
    private int N2; // Время рождения альбиноса кролика(каждые N2 секунды)
    private int K2; // проценнт от общего числа кроликов
    // массив кроликов
    private Collections collectionsRabbit;
   // private ArrayList<Rabbit> rabbitArrayList = new ArrayList<Rabbit>();

    public Habitat(){
        collectionsRabbit = new Collections();
    }

    public void update(int time, Pane pane) {
        if (canBornOdinaryRabbit(N1,P1,time))
        {
            OdinaryRabbit odinaryRabbit = makeOdinaryRabbit(time);
            collectionsRabbit.add(odinaryRabbit);
            pane.getChildren().addAll(odinaryRabbit.getImageView());
        }

        if (canBornAlbinosRabbit(N2,K2,time))
        {
            AlbinosRabbit albinosRabbit = makeAlbinosRabbit(time);
            collectionsRabbit.add(albinosRabbit);
            pane.getChildren().addAll(albinosRabbit.getImageView());
        }

        collectionsRabbit.updateCollectionsPerTime(pane);
    }

    //   Обыкновенные кролики рождаются каждые N1 секунд с вероятностью P1.
    private boolean canBornOdinaryRabbit(int N1,int P1,int time){
        int randomVariation = (int)Math.floor(Math.random()*100);
        if(time % N1 == 0 && randomVariation<=P1) return true;
        return false;
    }

    private OdinaryRabbit makeOdinaryRabbit(int time){
        ImageView imageView = new ImageView(imageOdinaryRabbit);
        int x = (int)Math.floor(Math.random()*(WIDTH-Rabbit.WIDTH));
        int y = (int)Math.floor(Math.random()*(HEIGH-Rabbit.HEIGHT));
        OdinaryRabbit odinaryRabbit = new OdinaryRabbit(imageView,x,y,time,4);
        return odinaryRabbit;
    }

    //    Альбиносы рождаются каждые N2 секунд, при условии, что их количество менее K% от общего числа кроликов,
    //    в противном случае – не рождаются вовсе.
    private boolean canBornAlbinosRabbit(int N2,int K2,int time){
        if(time % N2 == 0 && Rabbit.countsAllRabbits <= Rabbit.countsAllRabbits * K2)
        {
            return true;
        }
        return false;
    }

    private AlbinosRabbit makeAlbinosRabbit(int time){
        ImageView imageView = new ImageView(imageAlbinosRabbit);
        int x = (int)Math.floor(Math.random()*(WIDTH-Rabbit.WIDTH));
        int y = (int)Math.floor(Math.random()*(HEIGH-Rabbit.HEIGHT));
        AlbinosRabbit albinosRabbit = new AlbinosRabbit(imageView,x,y,time,5);
        return albinosRabbit;
    }

    public void removeAll(){
        Rabbit.countsAllRabbits = 0;
        AlbinosRabbit.countAlbinosRabbit = 0;
        OdinaryRabbit.countOdinaryRabbit = 0;
    }

    public void setN1(int n1) {
        N1 = n1;
    }
    public void setP1(int p1) {
        P1 = p1;
    }
    public void setN2(int n2) {
        N2 = n2;
    }
    public void setK2(int k2) {
        K2 = k2;
    }

    private void showLogConsole(Object object){
        String message = new String();
        if (object instanceof AlbinosRabbit) {
            message = "Create new Rabbit: " + AlbinosRabbit.typeRabbit;
        }
        if (object instanceof OdinaryRabbit) {
            message = "Create new Rabbit: " + OdinaryRabbit.typeRabbit;
        }
        System.out.println(message);
    }

    public ImageView getImageViewBackground() {
        ImageView imageViewBackground = new ImageView(imageBackground);
        imageViewBackground.setFitWidth(WIDTH);
        imageViewBackground.setFitHeight(HEIGH);
        return imageViewBackground;
    }
}
