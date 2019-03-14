package sample.Classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.util.ArrayList;
import javafx.scene.image.*;
import sample.Classes.Rabbits.AlbinosRabbit;
import sample.Classes.Rabbits.OdinaryRabbit;
import sample.Classes.Rabbits.Rabbit;
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
    private static ArrayList<Object> collectionRabbit = new ArrayList<Object>();

    public Habitat(Pane _pane) throws Exception {
        //this.pane = _pane;
        Main.controller.getMainPane().getChildren().addAll(createBackGround());
    }

    public static void update(int time) {
        System.out.println("Habitat.Update; "+"Time: " + time );
        int N1; // Время рождения обыкновенного кролика(каждые N1 секунды)
        int P1; // Вероятность % рождения обыкновенного кролика

        int N2; // Время рождения альбиноса кролика(каждые N2 секунды)
        int K2; // проценнт от общего числа кроликов
        N1 = 10;
        P1 = 50;
        N2 = 20;
        K2 = 10;


         //   Born Odinary rabbit
         //   Обыкновенные кролики рождаются каждые N1 секунд с вероятностью P1.

        int randomVariation = (int)Math.floor(Math.random()*100);
        if (time % N1 == 0 && randomVariation<=P1){
            ImageView imageView = new ImageView(imageOdinaryRabbit);
            int x = (int)Math.floor(Math.random()*(WIDTH-Rabbit.WIDTH));
            int y = (int)Math.floor(Math.random()*(HEIGH-Rabbit.HEIGHT));
            OdinaryRabbit OdinaryRabbit = new OdinaryRabbit(imageView,x,y);
            collectionRabbit.add(OdinaryRabbit);

            String message = new String("Create new Rabbit: " + OdinaryRabbit.typeRabbit);
            System.out.println(message);
        }


        //    Born Albinos rabbit
        //    Альбиносы рождаются каждые N2 секунд, при условии, что их количество менее K% от общего числа кроликов,
        //    в противном случае – не рождаются вовсе.

        int K22 = Rabbit.countsAllRabbits * K2;
        if (time % N2 == 0 && Rabbit.countsAllRabbits <= K22 )
        {
            ImageView imageView = new ImageView(imageAlbinosRabbit);
            int x = (int)Math.floor(Math.random()*(WIDTH-Rabbit.WIDTH));
            int y = (int)Math.floor(Math.random()*(HEIGH-Rabbit.HEIGHT));
            AlbinosRabbit albinosRabbit = new AlbinosRabbit(imageView,x,y);
            collectionRabbit.add(albinosRabbit);
            String message = new String("Create new Rabbit: " + AlbinosRabbit.typeRabbit);
            System.out.println(message);
        }
    }

    public void removeAll(){
        Rabbit.countsAllRabbits = 0;
        AlbinosRabbit.countAlbinosRabbit = 0;
        OdinaryRabbit.countOdinaryRabbit = 0;
        Main.controller.getMainPane().getChildren().addAll(createBackGround());
    }

    private ImageView createBackGround(){
        ImageView imageViewBackground = new ImageView(imageBackground);
        imageViewBackground.setFitWidth(WIDTH);
        imageViewBackground.setFitHeight(HEIGH);
        return  imageViewBackground;
    }

}
