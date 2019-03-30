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
import sample.Classes.Windows.WindowInformation;

import java.io.IOException;

public class AppManager{
    private Habitat habitat;
    private Controller controller;

    private Runnable runnable = new Runnable()
    {
        @Override
        public  void run()
        {
            if (stateSimulation == RUNNING)
            {
                while (true) {
                    try {
                        Thread.sleep(speedSimulation);
                        seconds++;
                        if (seconds == 60) {
                            minutes++;
                            seconds = 0;
                        }

                        // Use runLater to update object PANE
                        Platform.runLater(new Runnable(){
                            @Override
                            public synchronized void run() {
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

    private int seconds = 0;
    private int minutes = 0;
    private int speedSimulation = 700;
    private Thread thread;

    // Define state of the simulation
    public static final int  RUNNING = 1;
    public static final int  PAUSE = 2;
    public static final int  STOP = 3;

    // init state
    private int stateSimulation = -1;

    private void updateAppPerSecond(){
        controller.getFieldTime().setText(minutes + ":" +seconds);
        habitat.update(seconds,controller.getMainPane());
    }

    public AppManager(Stage primaryStage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        thread = new Thread(runnable);
        habitat = new Habitat();
        initPrimaryStageAndController(primaryStage,mainLoader);
        disableButtons(stateSimulation);
    }

    private void initPrimaryStageAndController(Stage primaryStage,FXMLLoader mainLoader) throws IOException {
        Parent root = mainLoader.load();
        this.controller = (Controller)mainLoader.getController();
        controller.initialize(this);
        controller.getMainPane().getChildren().addAll(habitat.getImageViewBackground());

        double width = controller.getMainStage().getPrefWidth();
        double height = controller.getMainStage().getPrefHeight();

        Scene scene = new Scene(root,width,height);
        primaryStage.setTitle("Lab 3");
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void appStart() {
        setConditionsBornAndDeadRabbit();
        switch (stateSimulation){
            case PAUSE:{
                stateSimulation = RUNNING;
                this.thread.resume();
            } break;
            case STOP:{
                this.seconds = 0;
                this.minutes = 0;
                stateSimulation = RUNNING;
                controller.getMainPane().getChildren().addAll(habitat.getImageViewBackground());
                this.thread.resume();
            } break;
            default: {
                stateSimulation = RUNNING;
                this.thread.start();
                this.seconds = 0;
                this.minutes = 0;
            }
        }
        disableButtons(stateSimulation);
    }

    private void setConditionsBornAndDeadRabbit(){
        int N1 = controller.getValueTimeBornRabbitOdinaty();
        int P1 = controller.getValueSliderVariationBornRabbitOdinary();

        int N2 = controller.getValueTimeBornRabbitAlbinos();
        int K2 = controller.getValueSliderVariationBornRabbitAlbinos();

        int timeLifeAlbinosRaabit = controller.getValueTimeLifeRabbitAlbinos();
        int timeLifeOdinaryRabbit = controller.getValuetTimeLifeRabbitOdinaty();

        habitat.setConditionsBornRabbit(N1,P1,N2,K2);
        habitat.setConditionsTimeLifeRabbit(timeLifeAlbinosRaabit,timeLifeOdinaryRabbit);
    }

    public void appPause(){
        if (stateSimulation == RUNNING)
        {
            stateSimulation = PAUSE;
            this.thread.suspend();
        }
        disableButtons(stateSimulation);
    }

    public void appStop() {
        if(controller.getValueCheckBoxShowDialog() == true) {
            WindowInformation windows = new WindowInformation("Modal Window", makeResultLog(), this);
            stateSimulation = PAUSE;
            thread.suspend();
        }
        else
        if (stateSimulation == RUNNING || stateSimulation == PAUSE )
        {
            stateSimulation = STOP;
            thread.suspend();
            removeAllHabitat();
            disableButtons(stateSimulation);
        }
    }

    public void disableButtons(int stateTimer){
        switch (stateTimer) {
            case RUNNING: {
                controller.getStartButton().setDisable(true);
                controller.getPauseButton().setDisable(false);
                controller.getStopButton().setDisable(false);
            }
            break;
            case PAUSE: {
                controller.getStartButton().setDisable(false);
                controller.getPauseButton().setDisable(true);
                controller.getStopButton().setDisable(false);
            }
            break;
            case STOP: {
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

    public void removeAllHabitat(){
        habitat.clear();
        controller.getMainPane().getChildren().addAll(habitat.getImageViewBackground());
    }

    private String makeResultLog(){
        return new String(
                "Total Rabbits: " + Rabbit.countsAllRabbits +
                        ";"+ '\n' +"Odinary Rabbits: " + OdinaryRabbit.countOdinaryRabbit +
                        ";"+ '\n' +"ALbinos Rabbits: " + AlbinosRabbit.countAlbinosRabbit +
                        ";"+ '\n' +"Time of simulation Min:" + this.minutes + " Sec: " +this.seconds
        );
    }

    public void showWindowCollectionsInformatos(){
        String message = habitat.getInfoAliveRabbits();
        WindowInformation windows = new WindowInformation(
                                                            "Information about collections",
                                                            500,
                                                            500,
                                                            message,
                                                            this);
        stateSimulation = PAUSE;
        thread.suspend();
    }

    public int getStateOfTimer() {
        return stateSimulation;
    }
    public void setStateOfTimer(int stateOfSimulation) {
        this.stateSimulation = stateOfSimulation;
    }

}

