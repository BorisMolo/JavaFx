package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import sample.Classes.Habitat;

//import sun.security.mscapi.KeyStore;


import javafx.animation.*;
import javafx.scene.layout.*;
import sample.Classes.Rabbits.AlbinosRabbit;
import sample.Classes.Rabbits.OdinaryRabbit;
import sample.Classes.Rabbits.Rabbit;
import sample.Classes.StopWatch;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static Controller controller;
    public static AppController primeAppController;
    public static Pane pane;
    //Habitat hibitian;



    @Override
    public void start(Stage primaryStage) throws Exception{
        // load FXML file and set it to root
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        /*
            create scene
            add scene to stage
            show it
        */
        primaryStage.setTitle("FirstLab");
        Scene mainScene = new Scene(root, 600, 600);
        primaryStage.setScene(mainScene);
        primaryStage.show();

        // get controller
        controller = (Controller)loader.getController();
        primeAppController = new AppController();
        this.pane = controller.getMainPane();
    }


    public AppController getAppController() {
        return primeAppController;
    }

    class AppController{
        private Habitat hibitian;
        private StopWatch timer;

        /*
        private AnimationTimer timer;
        {
            this.timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    printTime();
                    //if(bSimulation)
                    {
                        long timeCurrent = System.currentTimeMillis() - timeStart;
                        long step = timeCurrent - timePrevios;
                        if (step >= 100) {
                            time = (int) (timeCurrent / 100);
                            hibitian.update(time);
                            timePrevios = timeCurrent;
                            seconds = time - minutes * 60;
                            //hibitian.update(seconds);
                            if (seconds % 60 == 0) {
                                minutes++;
                                seconds = 0;
                            }
                        }
                    }
                }
            };
        }
        */

        private boolean isStart;
        long timeStart;
        int time = 0;
        long timePrevios = 0;

        private int seconds = 0;
        private int minutes = 0;

        public AppController(){
            this.isStart = false;
            timer = new StopWatch();
        }

        public void appStart() throws Exception {
            //System.out.println(Rabbit.countsAllRabbits);
            this.isStart = true;
            hibitian = new Habitat();
            timeStart = System.currentTimeMillis();;
            time = 0;
            timePrevios = 0;
            timer.start(controller.getFieldTime(),hibitian);

        }

        public void appPause(){
            timer.pause();
        }

        public void appStop() throws Exception {
            this.isStart = false;
            this.seconds = 0;
            this.minutes = 0;
            Rabbit.countsAllRabbits = 0;
            AlbinosRabbit.countAlbinosRabbit = 0;
            OdinaryRabbit.countOdinaryRabbit = 0;
            timer.stop();
        }

        void printTime(){
            String message = new String( minutes + " : " + seconds);
            controller.setFieldTimeText(message);
        }
    }

}

