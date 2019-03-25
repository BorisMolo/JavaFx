package sample.Classes.Windows;

import javafx.stage.Modality;
import javafx.stage.Stage;

abstract public class Window {
    Stage window;
    public Window(String titleOfWindow){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setWidth(300);
        window.setHeight(200);
        window.setTitle(titleOfWindow);
        window.setResizable(false);
    }
}
