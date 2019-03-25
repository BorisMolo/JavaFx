package sample.Classes;

import javafx.scene.image.Image;
import java.util.ArrayList;
import javafx.scene.image.*;
import sample.Classes.Rabbits.AlbinosRabbit;
import sample.Classes.Rabbits.OdinaryRabbit;
import sample.Classes.Rabbits.Rabbit;
import sample.Controller;
import sample.Main;

import java.awt.*;

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

    // массив кроликов
    private ArrayList<Object> collectionRabbit = new ArrayList<Object>();

    public Habitat(){
    }

    public ImageView update(int time) {
        int N1; // Время рождения обыкновенного кролика(каждые N1 секунды)
        int P1; // Вероятность % рождения обыкновенного кролика

        int N2; // Время рождения альбиноса кролика(каждые N2 секунды)
        int K2; // проценнт от общего числа кроликов

        N1 = 1;//Main.controller.getTextTextFieldValue(Main.controller.getFieldTimeRabbitOdinaty());
        P1 = 50;
        N2 = 1;//Main.controller.getTextTextFieldValue(Main.controller.getFieldTimeRabbitAlbinos());
        K2 = 2;//Main.controller.getTextTextFieldValue(Main.controller.getFieldVariationRabbitAlbinos());

        if (canBornOdinaryRabbit(N1,P1,time))
            return makeOdinaryRabbit();

        if (canBornAlbinosRabbit(N2,K2,time))
            return makeAlbinosRabbit();

        return this.getImageViewBackground();
    }

    //   Обыкновенные кролики рождаются каждые N1 секунд с вероятностью P1.
    private boolean canBornOdinaryRabbit(int N1,int P1,int time){
        int randomVariation = (int)Math.floor(Math.random()*100);
        if(time % N1 == 0 && randomVariation<=P1) return true;
        return false;
    }

    private ImageView makeOdinaryRabbit(){
        ImageView imageView = new ImageView(imageOdinaryRabbit);
        int x = (int)Math.floor(Math.random()*(WIDTH-Rabbit.WIDTH));
        int y = (int)Math.floor(Math.random()*(HEIGH-Rabbit.HEIGHT));
        OdinaryRabbit odinaryRabbit = new OdinaryRabbit(imageView,x,y);
        collectionRabbit.add(odinaryRabbit);
        showLog(odinaryRabbit);
        return odinaryRabbit.getImageView();
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

    private ImageView makeAlbinosRabbit(){
        ImageView imageView = new ImageView(imageAlbinosRabbit);
        int x = (int)Math.floor(Math.random()*(WIDTH-Rabbit.WIDTH));
        int y = (int)Math.floor(Math.random()*(HEIGH-Rabbit.HEIGHT));
        AlbinosRabbit albinosRabbit = new AlbinosRabbit(imageView,x,y);
        collectionRabbit.add(albinosRabbit);
        showLog(albinosRabbit);
        return albinosRabbit.getImageView();
    }

    public void removeAll(){
        Rabbit.countsAllRabbits = 0;
        AlbinosRabbit.countAlbinosRabbit = 0;
        OdinaryRabbit.countOdinaryRabbit = 0;
    }

    private void showLog(Object object){
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
