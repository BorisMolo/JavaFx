package sample;

import javafx.animation.AnimationTimer;
import sample.Classes.Habitat;
import sample.Classes.Rabbits.AlbinosRabbit;
import sample.Classes.Rabbits.OdinaryRabbit;
import sample.Classes.Rabbits.Rabbit;
import sample.Classes.StopWatch;

public class AppController{
    private Habitat habitat;
    private StopWatch stopWatch;
    private boolean showLog = true;

    private AnimationTimer timer;
    {
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //printTime();
                //if(bSimulation)
                {
                    long timeCurrent = System.currentTimeMillis() - timeStart;
                    long step = timeCurrent - timePrevios;
                    if (step >= 100) {
                        time = (int) (timeCurrent / 100);
                        habitat.update(time);
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
        habitat = new Habitat(Main.controller.getMainPane());
    }

    public void appStart() throws Exception {
        if(stopWatch.getStateOfTimer() == stopWatch.STOP) habitat.removeAll();
        time = 0;
        timePrevios = 0;
        stopWatch.start(Main.controller.getFieldTime(), habitat);
        disableButtons(stopWatch.getStateOfTimer());
    }

    public void appPause(){
        stopWatch.pause();
        disableButtons(stopWatch.getStateOfTimer());
    }

    public void appStop() throws Exception {
        this.seconds = 0;
        this.minutes = 0;
        stopWatch.stop();
        System.out.printf(makeResultLog());
        habitat.removeAll();
        disableButtons(stopWatch.getStateOfTimer());
    }

    public void setShowLog(boolean showLog) {
        this.showLog = showLog;
    }

    public boolean getShowLog(){
        return this.showLog;
    }

    private String makeResultLog(){
        return new String(
                "Total Rabbits: " + Rabbit.countsAllRabbits +
                        ";"+ '\n' +"Odinary Rabbits: " + OdinaryRabbit.countOdinaryRabbit +
                        ";"+ '\n' +"ALbinos Rabbits: " + AlbinosRabbit.countAlbinosRabbit +
                        ";"+ '\n' +"Time of simulation Min:" + stopWatch.getMinutes() + " Sec: " +stopWatch.getSeconds()
        );
    }

    private void disableButtons(int stateOfTimer){
        switch (stateOfTimer) {
            case StopWatch.RUNNING: {
                Main.controller.getStartButton().setDisable(true);
                Main.controller.getPauseButton().setDisable(false);
                Main.controller.getStopButton().setDisable(false);
            }
            break;
            case StopWatch.PAUSE: {
                Main.controller.getStartButton().setDisable(false);
                Main.controller.getPauseButton().setDisable(true);
                Main.controller.getStopButton().setDisable(false);
            }
            break;
            case StopWatch.STOP: {
                Main.controller.getStartButton().setDisable(false);
                Main.controller.getPauseButton().setDisable(true);
                Main.controller.getStopButton().setDisable(true);
            }
            break;
        }
    }
}

