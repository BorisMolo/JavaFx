package sample;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import sample.Classes.Windows.WindowError;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainStage;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private TextField fieldTime;

    @FXML
    private TextField timeBornRabbitOdinaty;

    @FXML
    private TextField timeBornRabbitAlbinos;

    @FXML
    private Pane mainPane;

    @FXML
    private TextField variationRabbitAlbinos;

    @FXML
    private Button pauseButton;

    private Scene mainScene;

    @FXML
    void initialize(AppManager appManager) {
        try {
            initListeners(appManager);
        }
        catch (Exception e ){
            WindowError windowError = new WindowError(e.toString());
        }
    }

    private void initListeners(AppManager appManager){
        startButton.setOnAction(event ->
        {
            if (checkBook())
            {
                try {
                    fieldTime.setText("");
                    appManager.appStart();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        stopButton.setOnAction(event ->
        {
            try {
                appManager.appStop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        pauseButton.setOnAction(event ->
        {
            try {
                appManager.appPause();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        mainStage.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                try {
                    writeKeyCode(event.getCode(),appManager);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean isIntegerTextField(TextField textField){
        try{
            Integer.parseInt(textField.getText());
            return true;
        }
        catch (NumberFormatException e){
            showDialogError(textField);
            return false;
        }
    }

    private boolean showDialogError(TextField textField) {
        String erroeMessage;
        if(textField.getId() == timeBornRabbitOdinaty.getId()){
            erroeMessage =
                    "Значание в поле: \n"
                            + "\"Время рождения кролика Обыкновенного\" \n"
                            + "должно быть не пустим и целочисленным";
            WindowError windowError = new WindowError(erroeMessage);
            textField.requestFocus();
            return false;
        }
        if(textField.getId() == timeBornRabbitAlbinos.getId()){
            erroeMessage =
                    "Значание в поле: \n"
                            + "\"Время рождения кролика Альбиноса\" \n"
                            + "должно быть не пустим и целочисленным";
            textField.requestFocus();
            WindowError windowError = new WindowError(erroeMessage);
            return false;
        }
        if (textField.getId() == variationRabbitAlbinos.getId()){
            erroeMessage =
                    "Значание в поле: \n"
                            + "\"Вероятность рождениякролика Альбиносао\" \n"
                            + "должно быть не пустим и целочисленным";
            WindowError windowError = new WindowError(erroeMessage);
            textField.requestFocus();
            return false;
        }
        return false;
    }

    private void writeKeyCode(KeyCode key, AppManager appManager) throws Exception {
        switch (key) {
            // Время симуляции должно отображаться текстом в области визуализации
            // и скрываться/показываться по клавише T
            case T: {
                System.out.print("Typed the key: T; ");
                if(appManager.getShowLog() == false)
                {
                    System.out.println("Logs has been activated");
                    appManager.setShowLog(true);
                    fieldTime.setVisible(true);
                }
                else
                {
                    System.out.println("Logs has been diactivated");
                    appManager.setShowLog(false);
                    fieldTime.setVisible(false);
                }
            }break;
            //Симуляция должна запускаться по клавише B
            case B: {
                System.out.println("Typed the key: B; ");

                if (checkBook())
                {
                    try {
                        fieldTime.setText("");
                        appManager.appStart();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }break;
            //останавливаться по клавише E
            case E: {
                System.out.println("Typed the key: E; ");
                appManager.appStop();
            }break;
        }
    }

    private Boolean checkBook(){
        if(
            isIntegerTextField(timeBornRabbitOdinaty) &&
            isIntegerTextField(timeBornRabbitAlbinos) &&
            isIntegerTextField(variationRabbitAlbinos)
            )
        {
            return true;
        }
        return false;
    }

    public TextField getFieldTime() {
        return fieldTime;
    }
    public Button getStartButton(){return startButton;}
    public Button getStopButton(){return stopButton;}
    public Button getPauseButton(){return pauseButton;}
    public Pane getMainPane() {
        return mainPane;
    }
}