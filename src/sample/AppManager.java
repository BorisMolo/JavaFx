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
            if (stateOfSimulation == RUNNING)
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

    // The simulation has 3 state: Run;Pause;Stop;
    public static final int  RUNNING = 1;
    public static final int  PAUSE = 2;
    public static final int  STOP = 3;

    // default settings
    private int stateOfSimulation = -1;

    private void updateAppPerSecond(){
        controller.getFieldTime().setText(minutes + ":" +seconds);
        habitat.update(seconds,controller.getMainPane());
    }

    public AppManager(Stage primaryStage) throws Exception {
        initprimaryStage(primaryStage);
        thread = new Thread(runnable);
        habitat = new Habitat();

        controller.initialize(this);
        controller.getMainPane().getChildren().addAll(habitat.getImageViewBackground());
        disableButtons(stateOfSimulation);
    }

    private void initprimaryStage(Stage primaryStage) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = mainLoader.load();
        primaryStage.setTitle("FirstLab");

        Scene scene = new Scene(root, 600, 720);
        primaryStage.setScene(scene);
        primaryStage.show();

        this.controller = (Controller)mainLoader.getController();
    }

    public void appStart() throws Exception {
        setConditionsBornAndDeadRabbit();
        if(stateOfSimulation == STOP) {
            habitat.removeAll();
            controller.getMainPane().getChildren().addAll(habitat.getImageViewBackground());
        }

        switch (stateOfSimulation){
            case PAUSE:{
                stateOfSimulation = RUNNING;
                this.thread.resume();
            } break;
            case STOP:{
                this.seconds = 0;
                this.minutes = 0;
                stateOfSimulation = RUNNING;
                this.thread.resume();
            } break;
            default:
                stateOfSimulation = RUNNING;
                this.thread.start();
                this.seconds = 0;
                this.minutes = 0;
        }
        disableButtons(stateOfSimulation);
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
        if (stateOfSimulation == RUNNING)
        {
            stateOfSimulation = PAUSE;
            this.thread.suspend();
        }
        disableButtons(stateOfSimulation);
    }

    public void appStop() {
        if(controller.getValueCheckBoxShowDialog() == true) {
            WindowInformation windows = new WindowInformation("Modal Window", makeResultLog(), this);
            stateOfSimulation = PAUSE;
            thread.suspend();
        }
        else
        if (stateOfSimulation == RUNNING || stateOfSimulation == PAUSE )
        {
            stateOfSimulation = STOP;
            thread.suspend();
            removeAllHabitat();
            disableButtons(stateOfSimulation);
        }
    }

    public void removeAllHabitat(){
        habitat.removeAll();
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

    public void disableButtons(int stateOfTimer){
        switch (stateOfTimer) {
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

    public int getStateOfTimer() {
        return stateOfSimulation;
    }
    public void setStateOfTimer(int stateOfSimulation) {
        this.stateOfSimulation = stateOfSimulation;
    }

}

