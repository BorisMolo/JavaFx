package sample.Classes;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.Service;
import javafx.scene.control.TextField;

public class StopWatch {
    Runnable runnable = new Runnable()
    {
        @Override
        public  void run()
        {

            System.out.println("StopWatch.Runnable;Run");

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
                        // Use runLater to update object PANE
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                //System.out.println("Min: " + minutes + " Second: " + seconds);
                                if (textField != null) textField.setText(minutes + ":" +seconds);
                                updateObject(seconds);
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
    private boolean isStopped;
    private boolean isHasBeenRan;
    private int speedSimulation = 100;
    private Thread thread;
    private TextField textField;
    private Object object;

    // The timer has 3 state: Run;Pause;Stop;
    private static final int  RUNNING = 1;
    private static final int  PAUSE = 2;
    private static final int  STOP = 3;

    // defaul settings
    private int stateOfTimer = -1;

    public StopWatch(){
        this.seconds = 0;
        this.minutes = 0;
        this.isHasBeenRan = false;
        this.isStopped = false;
        this.textField = null;
        thread = new Thread(runnable);
    }

    public void start() {
        System.out.println("stop watch is starting!");

        if (stateOfTimer == STOP)
        {
            stateOfTimer = RUNNING;
            this.thread.start();
        }
        else thread.resume();
        this.seconds = 0;
        this.minutes = 0;
    }

    public void start(TextField _textField, Object _object) {
        //System.out.println("Stop watch is starting!");
        switch (stateOfTimer){
            case RUNNING: {

            } break;
            case PAUSE:{
                stateOfTimer = RUNNING;
                this.thread.resume();
            } break;
            case STOP:{
                //stateOfTimer = RUNNING;
                //this.thread.resume();
            } break;
            default:
                stateOfTimer = RUNNING;
                this.thread.start();
                this.textField = _textField;
                this.object = _object;
        }
    }

    public void pause(){
        stateOfTimer = PAUSE;
        this.thread.suspend();
        //this.thread.stop();
    }

    public void stop(){
        stateOfTimer = STOP;
        this.thread.suspend();
        //this.thread.stop();
        //this.seconds = 0;
        //this.minutes = 0;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public boolean isiStopped() {
        return isStopped;
    }

    public String secondToString(){

        return  Integer.toString(getSeconds());
    }

    public String minuteToString(){
        return  Integer.toString(getMinutes());
    }

    // update this.object per time
    private void updateObject(int time){
        if(this.object instanceof Habitat) {
            Habitat habitat = (Habitat)this.object;
            habitat.update(time);
        }
    }

    public void setSpeedSimulation(int speedSimulation) {
        this.speedSimulation = speedSimulation;
    }

    public int getSpeedSimulation() {
        return speedSimulation;
    }
}
