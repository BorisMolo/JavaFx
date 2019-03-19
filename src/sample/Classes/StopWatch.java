package sample.Classes;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.Service;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import sample.Main;

public class StopWatch {
    Runnable runnable = new Runnable()
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
                                //Main.controller.getFieldTime().setText(minutes + ":" +seconds);
                                //Habitat.update(seconds);
                                updateTime();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    };

    private int seconds;
    private int minutes;
    private int speedSimulation = 100;
    private Thread thread;

    // The timer has 3 state: Run;Pause;Stop;
    public static final int  RUNNING = 1;
    public static final int  PAUSE = 2;
    public static final int  STOP = 3;

    // defaul settings
    private int stateOfTimer = -1;

    public StopWatch(){
        this.seconds = 0;
        this.minutes = 0;
        thread = new Thread(runnable);
    }

    public void start() {
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
    }

    public void start(TextField _textField, Object _object) {
        switch (stateOfTimer){
            case RUNNING: break;
            case PAUSE:{
                stateOfTimer = RUNNING;
                this.thread.resume();
            } break;
            case STOP:{
                stateOfTimer = RUNNING;
                this.seconds = 0;
                this.minutes = 0;
                this.thread.resume();
            } break;
            default:
                stateOfTimer = RUNNING;
                this.seconds = 0;
                this.minutes = 0;
                this.thread.start();
        }
    }

    public void pause() {
        if (stateOfTimer == RUNNING)
        {
            stateOfTimer = PAUSE;
            this.thread.suspend();
        }
    }

    public void stop(){
        if (stateOfTimer == RUNNING || stateOfTimer == PAUSE){
            stateOfTimer = STOP;
            this.thread.suspend();
        }

    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public String secondToString(){

        return  Integer.toString(getSeconds());
    }

    public String minuteToString(){
        return  Integer.toString(getMinutes());
    }

    public void setSpeedSimulation(int speedSimulation) {
        this.speedSimulation = speedSimulation;
    }

    public int getSpeedSimulation() {
        return speedSimulation;
    }

    public int getStateOfTimer() {
        return stateOfTimer;
    }

    public int updateTime( ) {return this.seconds;}
}
