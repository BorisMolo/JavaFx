package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Classes.Habitat;
import sample.Classes.Rabbits.AlbinosRabbit;
import sample.Classes.Rabbits.OdinaryRabbit;
import sample.Classes.Rabbits.Rabbit;
import sample.Classes.StopWatch;
import sample.Classes.Windows.WindowInformation;

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
                    long timeCurrent = System.currentTimeMillis() - timeStartAnimationTimer;
                    long step = timeCurrent - timePreviosAnimationTimer;
                    if (step >= 100) {
                        timeAnimationTimer = (int) (timeCurrent / 100);
                        habitat.update(timeAnimationTimer);
                        timePreviosAnimationTimer = timeCurrent;
                        secondsAnimationTimer = timeAnimationTimer - minutesAnimationTimer * 60;
                        //hibitian.update(seconds);
                        if (secondsAnimationTimer % 60 == 0) {
                            minutesAnimationTimer++;
                            secondsAnimationTimer = 0;
                        }
                    }
                }
            }
        };
    }
    int timeStartAnimationTimer = 0;
    int timeAnimationTimer = 0;
    long timePreviosAnimationTimer = 0;
    private int secondsAnimationTimer = 0;
    private int minutesAnimationTimer = 0;

    public AppController() throws Exception {
        stopWatch = new StopWatch();
        habitat = new Habitat(Main.controller.getMainPane());
        disableButtons(stopWatch.getStateOfTimer());
    }

    public void appStart() throws Exception {
        if(stopWatch.getStateOfTimer() == stopWatch.STOP) habitat.removeAll();
        stopWatch.start(Main.controller.getFieldTime(), habitat);
        disableButtons(stopWatch.getStateOfTimer());
        //System.out.println(stopWatch.updateTime());
    }

    public void appPause(){
        stopWatch.pause();
        disableButtons(stopWatch.getStateOfTimer());
    }

    public void appStop() throws Exception {
        stopWatch.pause();
        //showInformationDialog(makeResultLog());
        WindowInformation windows = new WindowInformation("Модальеное окно",makeResultLog(),this);
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

    public void disableButtons(int stateOfTimer){
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

    /*При остановке симуляции должно появляться модальное диалоговое окно (при условии, что оно разрешено)
    с информацией о количестве и типе сгенерированных объектов, а также времени симуляции. Вся информация
    выводится в элементе TextArea, недоступном для редактирования. В диалоговом окне должно быть 2 кнопки:
    «ОК» и «Отмена». При нажатии на «ОК» симуляции полностью останавливается, а при нажатии на «Отмена»,
    соответственно продолжается;
    */
    private void showInformationDialog(String mesageTextArea){
        Button okButton = new Button("Ок");
        Button cancelButton = new Button("Отмена");
        Stage window = new Stage();

        // Events for buttonClose
        cancelButton.setOnAction(event -> {
            stopWatch.start();
            disableButtons(stopWatch.getStateOfTimer());
            window.close();
        });
        okButton.setOnAction(event -> {
            stopWatch.stop();
            habitat.removeAll();
            disableButtons(stopWatch.getStateOfTimer());
            window.close();
        });

        window.initModality(Modality.APPLICATION_MODAL);
        window.setWidth(350);
        window.setHeight(350);
        window.setTitle("Модальное диалоговое окно ");

        TextArea textArea = new TextArea(mesageTextArea);
        textArea.setPrefColumnCount(15);
        textArea.setPrefRowCount(5);

        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, textArea, cancelButton,okButton);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root);

        window.setScene(scene);
        window.show();
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public Habitat getHabitat() {
        return habitat;
    }
}

