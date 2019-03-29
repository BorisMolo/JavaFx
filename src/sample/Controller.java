package sample;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    private Button pauseButton;

    @FXML
    private Slider sliderVariationBornRabbitAlbinos;

    @FXML
    private Slider sliderVariationBornRabbitOdinary;

    @FXML
    private CheckBox checkBoxShowDialog;

    @FXML
    private CheckBox checkBoxShowTime;

    @FXML
    private Label labelTextTIMER;

    @FXML
    private TextField timeLifeRabbitOdinaty;

    @FXML
    private TextField timeLifeRabbitAlbinos;

    private Boolean showLog = true;

    @FXML
    void initialize(AppManager appManager) {
        try {
            initSliders();
            initCheckBoxes();
            initListeners(appManager);
            showLog = checkBoxShowTime.isSelected();
        }
        catch (Exception e ){
            WindowError windowError = new WindowError(e.toString());
        }
    }

    private void initListeners(AppManager appManager){
        startButton.setOnAction(event ->
        {
            if (checkTextBooxsSucces())
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

        checkBoxShowTime.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue ov, Boolean old_val, Boolean new_val) {
                showTimer();
            }
        });

    }

    private void initCheckBoxes(){
        checkBoxShowTime.setSelected(true);
        checkBoxShowDialog.setSelected(true);
    }

    private void initSliders(){
        sliderVariationBornRabbitAlbinos.setMin(0);
        sliderVariationBornRabbitAlbinos.setMax(100);
        sliderVariationBornRabbitAlbinos.setBlockIncrement(10);
        sliderVariationBornRabbitAlbinos.setMajorTickUnit(10);
        sliderVariationBornRabbitAlbinos.setShowTickLabels(true);
        sliderVariationBornRabbitAlbinos.setShowTickMarks(true);
        sliderVariationBornRabbitAlbinos.setValue(50);

        sliderVariationBornRabbitOdinary.setMin(0);
        sliderVariationBornRabbitOdinary.setMax(100);
        sliderVariationBornRabbitOdinary.setBlockIncrement(10);
        sliderVariationBornRabbitOdinary.setMajorTickUnit(10);
        sliderVariationBornRabbitOdinary.setShowTickLabels(true);
        sliderVariationBornRabbitOdinary.setShowTickMarks(true);
        sliderVariationBornRabbitOdinary.setValue(50);
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

    private void showDialogError(TextField textField) {
        String erroeMessage;
        if(textField.getId() == timeBornRabbitOdinaty.getId()){
            erroeMessage =
                    "Значание в поле: \n"
                            + "\"Время рождения кролика Обыкновенного\" \n"
                            + "должно быть не пустим и целочисленным";
            WindowError windowError = new WindowError(erroeMessage);
            textField.requestFocus();
        }
        if(textField.getId() == timeBornRabbitAlbinos.getId()){
            erroeMessage =
                    "Значание в поле: \n"
                            + "\"Время рождения кролика Альбиноса\" \n"
                            + "должно быть не пустим и целочисленным";
            textField.requestFocus();
            WindowError windowError = new WindowError(erroeMessage);
        }
    }

    private void writeKeyCode(KeyCode key, AppManager appManager) throws Exception {

        if(key == KeyCode.T) {
            showTimer();
        }
        if (key == KeyCode.B){
            if (checkTextBooxsSucces() == true)
            {
                try {
                    fieldTime.setText("");
                    appManager.appStart();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (key == KeyCode.B){
            appManager.appStop();
        }
    }

    private void showTimer(){
        if(showLog == false)
        {
            showLog = true;
            fieldTime.setVisible(true);
            labelTextTIMER.setVisible(true);
        }
        else
        {
            showLog = false;
            fieldTime.setVisible(false);
            labelTextTIMER.setVisible(false);
        }
    }

    private Boolean checkTextBooxsSucces(){
        if( isIntegerTextField(timeBornRabbitOdinaty) &&
            isIntegerTextField(timeBornRabbitAlbinos))
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


    public int getValueSliderVariationBornRabbitAlbinos() {
        return (int)sliderVariationBornRabbitAlbinos.getValue();
    }

    public int getValueSliderVariationBornRabbitOdinary() {
        return (int)sliderVariationBornRabbitOdinary.getValue();
    }

    public int getValueTimeBornRabbitOdinaty() {
        return Integer.parseInt(timeBornRabbitOdinaty.getText());
    }

    public int getValueTimeBornRabbitAlbinos() {
        return Integer.parseInt(timeBornRabbitAlbinos.getText());
    }

    public int getValuetTimeLifeRabbitOdinaty() {
        return Integer.parseInt(timeLifeRabbitOdinaty.getText());
    }

    public int getValueTimeLifeRabbitAlbinos() {
        return Integer.parseInt(timeBornRabbitAlbinos.getText());
    }

    public Boolean getValueCheckBoxShowDialog() {
        return checkBoxShowDialog.isSelected();
    }

    public Boolean getValueCheckBoxShowTime() {
        return checkBoxShowTime.isSelected();
    }
}