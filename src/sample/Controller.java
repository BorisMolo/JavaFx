package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    @FXML
    void initialize() {
        try {
            startButton.setOnAction(event ->
            {
                if (isIntegerTextField(fieldTimeRabbitOdinaty) &&  isIntegerTextField(fieldTimeRabbitAlbinos) && isIntegerTextField(fieldVariationRabbitAlbinos))
                {
                    try {
                        fieldTime.setText("");
                        Main.primeAppController.appStart();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            stopButton.setOnAction(event ->
            {
                try {
                    Main.primeAppController.appStop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            pauseButton.setOnAction(event ->
            {
                try {
                    Main.primeAppController.appPause();
                } catch (Exception e) {
                    e.printStackTrace();
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

    void updateFieldTime(String message){
        this.fieldTime.setText(message);
    }
}