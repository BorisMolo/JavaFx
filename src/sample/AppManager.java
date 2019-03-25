package sample;


import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Classes.Habitat;
import sample.Classes.Rabbits.AlbinosRabbit;
import sample.Classes.Rabbits.OdinaryRabbit;
import sample.Classes.Rabbits.Rabbit;
import sample.Classes.StopWatch;
import sample.Classes.Windows.WindowInformation;

import java.io.IOException;

public class AppManager{
    private Habitat habitat;
    private boolean showLog = true;
    private Controller controller;


    private Runnable runnable = new Runnable()
    {
        @Override
        public  void run()
        {
            if (stateOfTimer == RUNNING)
            {
                while (true) {
                    try {
                        Thread.sleep(speedSimulation);
                        seconds++;
                        if (seconds == 60) {
                            minutes++;
                            seconds = 0;
                        }
                        //TODO: Релизовать синхронизацию ПОТОКОВ!!!
                        // Use runLater to update object PANE
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                updateAppPerSecond();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    };

    private void updateAppPerSecond(){
        controller.getFieldTime().setText(minutes + ":" +seconds);
        habitat.update(seconds,controller.getMainPane());
    }

    private int seconds = 0;
    private int minutes = 0;
    private int speedSimulation = 100;
    private Thread thread;

    // The timer has 3 state: Run;Pause;Stop;
    public static final int  RUNNING = 1;
    public static final int  PAUSE = 2;
    public static final int  STOP = 3;

    // default settings
    private int stateOfTimer = -1;

    public AppManager(Stage primaryStage) throws Exception {
        initprimaryStage(primaryStage);
        thread = new Thread(runnable);
        habitat = new Habitat();

        controller.initialize(this);
        controller.getMainPane().getChildren().addAll(habitat.getImageViewBackground());
        disableButtons(stateOfTimer);
    }

    private void initprimaryStage(Stage primaryStage) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = mainLoader.load();
        primaryStage.setTitle("FirstLab");
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        this.controller = (Controller)mainLoader.getController();
    }

    public void appStart() throws Exception {
        setParametrsBornRabbit();
        if(stateOfTimer == STOP) {
            habitat.removeAll();
            controller.getMainPane().getChildren().addAll(habitat.getImageViewBackground());
        };

        switch (stateOfTimer){
            case PAUSE:{
                stateOfTimer = RUNNING;
                this.thread.resume();
            } break;
            case STOP:{
                this.seconds = 0;
                this.minutes = 0;
                stateOfTimer = RUNNING;
                this.thread.resume();
            } break;
            default:
                stateOfTimer = RUNNING;
                this.thread.start();
                this.seconds = 0;
                this.minutes = 0;
        }
        disableButtons(stateOfTimer);
    }

    private void setParametrsBornRabbit(){
        int N1 = controller.getValueTimeBornRabbitOdinaty();
        int P1 = controller.getValueSliderVariationBornRabbitOdinary();
        int N2 = controller.getValueTimeBornRabbitAlbinos();
        int K2 = controller.getValueSliderVariationBornRabbitAlbinos();

        habitat.setN1(N1);
        habitat.setP1(P1);
        habitat.setN2(N2);
        habitat.setK2(K2);
    }

    public void appPause(){
        if (stateOfTimer == RUNNING)
        {
            stateOfTimer = PAUSE;
            this.thread.suspend();
        }
        disableButtons(stateOfTimer);
    }

    public void appStop() throws Exception {
        if (stateOfTimer == RUNNING || stateOfTimer == PAUSE){
            stateOfTimer = PAUSE;
            this.thread.suspend();
            WindowInformation windows = new WindowInformation("Модальеное окно",makeResultLog(),this);
        }
    }

    private String makeResultLog(){
        return new String(
                "Total Rabbits: " + Rabbit.countsAllRabbits +
                        ";"+ '\n' +"Odinary Rabbits: " + OdinaryRabbit.countOdinaryRabbit +
                        ";"+ '\n' +"ALbinos Rabbits: " + AlbinosRabbit.countAlbinosRabbit +
                        ";"+ '\n' +"Time of simulation Min:" + this.minutes + " Sec: " +this.seconds
        );
    }

    public void disableButtons(int stateOfTimer){
        switch (stateOfTimer) {
            case StopWatch.RUNNING: {
                controller.getStartButton().setDisable(true);
                controller.getPauseButton().setDisable(false);
                controller.getStopButton().setDisable(false);
            }
            break;
            case StopWatch.PAUSE: {
                controller.getStartButton().setDisable(false);
                controller.getPauseButton().setDisable(true);
                controller.getStopButton().setDisable(false);
            }
            break;
            case StopWatch.STOP: {
                controller.getStartButton().setDisable(false);
                controller.getPauseButton().setDisable(true);
                controller.getStopButton().setDisable(true);
            }
            break;
            default:
                controller.getStartButton().setDisable(false);
                controller.getPauseButton().setDisable(true);
                controller.getStopButton().setDisable(true);
        }
    }

    public void setShowLog(boolean showLog) {
        this.showLog = showLog;
    }
    public boolean getShowLog(){
        return this.showLog;
    }

    public int getStateOfTimer() {
        return stateOfTimer;
    }
    public void setStateOfTimer(int _stateOfTimer) {
        this.stateOfTimer = _stateOfTimer;
    }

    public Habitat getHabitat() {
        return habitat;
    }
}

