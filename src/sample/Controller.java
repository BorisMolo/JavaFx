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
    private TextField fieldTimeRabbitOdinaty;

    @FXML
    private TextField fieldTimeRabbitAlbinos;

    @FXML
    private Pane mainPane;

    @FXML
    private TextField fieldVariationRabbitAlbinos;

    @FXML
    private Button pauseButton;

    private Scene mainScene;

    @FXML
    void initialize(AppController primeAppController) {
        try {

            startButton.setOnAction(event ->
            {
                if (isIntegerTextField(fieldTimeRabbitOdinaty) &&  isIntegerTextField(fieldTimeRabbitAlbinos) && isIntegerTextField(fieldVariationRabbitAlbinos))
                {
                    try {
                        fieldTime.setText("");
                        primeAppController.appStart();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            stopButton.setOnAction(event ->
            {
                try {
                    primeAppController.appStop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            pauseButton.setOnAction(event ->
            {
                try {
                    primeAppController.appPause();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            mainStage.setOnKeyPressed(new EventHandler<KeyEvent>(){
                @Override
                public void handle(KeyEvent event) {
                    try {
                        writeKeyCode(event.getCode());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
        }
        catch (Exception e ){
            System.out.println(e.toString());
        }
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

    public TextField getFieldTimeRabbitAlbinos() {
        return fieldTimeRabbitAlbinos;
    }

    public TextField getFieldTimeRabbitOdinaty() {
        return fieldTimeRabbitOdinaty;
    }

    public TextField getFieldVariationRabbitAlbinos() {
        return fieldVariationRabbitAlbinos;
    }

    public int getTextTextFieldValue(TextField textField){
        return Integer.parseInt(textField.getText());
    }

    private boolean isIntegerTextField(TextField textField){
        String erroeMessage;
        try{
            int number = Integer.parseInt(textField.getText());
            return true;
        }
        catch (NumberFormatException e){
            //System.out.println(erroeMessage);
            //System.out.println(textField.getId());
            switch (textField.getId()){
                case "fieldTimeRabbitOdinaty":
                    {
                        erroeMessage =
                                "Значание в поле: \n"
                                + "\"Время рождения кролика Обыкновенного\" \n"
                                + "должно быть не пустим и целочисленным";
                        WindowError windowError = new WindowError("Ошибка!", erroeMessage);
                    } break;
                case "fieldTimeRabbitAlbinos":
                {
                    erroeMessage =
                            "Значание в поле: \n"
                            + "\"Время рождения кролика Альбиноса\" \n"
                            + "должно быть не пустим и целочисленным";
                    WindowError windowError = new WindowError("Ошибка!", erroeMessage);
                } break;
                case "fieldVariationRabbitAlbinos":
                {
                    erroeMessage =
                            "Значание в поле: \n"
                            + "\"Вероятность рождениякролика Альбиносао\" \n"
                            + "должно быть не пустим и целочисленным";
                    WindowError windowError = new WindowError("Ошибка!", erroeMessage);
                } break;
                default: break;
            }


            return false;
        }

    }

    private void writeKeyCode(KeyCode key) throws Exception {
        switch (key) {
            // Время симуляции должно отображаться текстом в области визуализации
            // и скрываться/показываться по клавише T
            case T: {
                System.out.print("Typed the key: T; ");
                if(Main.primeAppController.getShowLog() == false)
                {
                    System.out.println("Logs has been activated");
                    Main.primeAppController.setShowLog(true);
                    //Main.controller.getFieldTime().setVisible(true);
                }
                else
                {
                    System.out.println("Logs has been diactivated");
                    Main.primeAppController.setShowLog(false);
                    //Main.controller.getFieldTime().setVisible(false);
                }
            }break;
            //Симуляция должна запускаться по клавише B
            case B: {
                System.out.println("Typed the key: B; ");

                if (isIntegerTextField(fieldTimeRabbitOdinaty) &&  isIntegerTextField(fieldTimeRabbitAlbinos) && isIntegerTextField(fieldVariationRabbitAlbinos))
                {
                    try {
                        fieldTime.setText("");
                        Main.primeAppController.appStart();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }break;
            //останавливаться по клавише E
            case E: {
                System.out.println("Typed the key: E; ");
                Main.primeAppController.appStop();
            }break;
        }
    }
}