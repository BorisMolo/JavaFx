package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.Classes.Habitat;
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

    private void writeKeyCode(KeyCode key) throws Exception {
        switch (key) {
            // Время симуляции должно отображаться текстом в области визуализации
            // и скрываться/показываться по клавише T
            case T: {
                System.out.println('T');

            }break;
            //Симуляция должна запускаться по клавише B
            case B: {
                System.out.println('B');
                primeAppController.appStart();
            }break;
            //останавливаться по клавише E
            case E: {
                System.out.println('E');
                primeAppController.appStop();
            }break;
        }

    }

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

        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                try {
                    writeKeyCode(event.getCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        // get controller
        controller = (Controller)loader.getController();
        this.pane = controller.getMainPane();
        primeAppController = new AppController();
    }

    public AppController getAppController() {
        return primeAppController;
    }

    class AppController{
        private Habitat hibitian;
        private StopWatch stopWatch;

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

        int timeStart = 0;
        int time = 0;
        long timePrevios = 0;

        private int seconds = 0;
        private int minutes = 0;

        public AppController() throws Exception {
            stopWatch = new StopWatch();
            hibitian = new Habitat(Main.pane);
        }

        public void appStart() throws Exception {
            if(stopWatch.getStateOfTimer() == stopWatch.STOP) hibitian.removeAll();
            time = 0;
            timePrevios = 0;
            stopWatch.start(controller.getFieldTime(), hibitian);
        }

        public void appPause(){
            stopWatch.pause();
        }

        public void appStop() throws Exception {
            this.seconds = 0;
            this.minutes = 0;
            stopWatch.stop();
        }

        void printTime(){
            String message = new String( minutes + " : " + seconds);
            controller.setFieldTimeText(message);
        }
    }

}

