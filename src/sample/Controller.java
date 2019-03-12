package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;


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

    private String erroeMessage;

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

    public void setFieldTimeText(String message) {
        TextField textField = this.getFieldTime();
        textField.setText(message);
    }

    public Pane getMainPane() {
        return mainPane;
    }

    private boolean isIntegerTextField(TextField textField){
        try{
            int number = Integer.parseInt(textField.getText());
            return true;
        }
        catch (NumberFormatException e){
            erroeMessage = "You can not enter " + e.getMessage()+" as a integer number; " + textField.toString();
            System.out.println(erroeMessage);
            return false;
        }

    }

    void updateFieldTime(String message){
        this.fieldTime.setText(message);
    }
}