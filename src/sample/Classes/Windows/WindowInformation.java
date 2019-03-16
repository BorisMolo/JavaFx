package sample.Classes.Windows;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import sample.AppController;
import sample.Main;


import java.nio.channels.FileLock;

public class WindowInformation extends Window {
    public WindowInformation(String titleOfWindow, String mesageTextArea, AppController primeAppController) {
        super(titleOfWindow);
        Button okButton = new Button("Ок");
        Button cancelButton = new Button("Отмена");

        cancelButton.setOnAction(event -> {
            primeAppController.getStopWatch().start();
            Main.primeAppController.disableButtons(Main.primeAppController.getStopWatch().getStateOfTimer());
            window.close();
        });
        okButton.setOnAction(event -> {
            primeAppController.getStopWatch().stop();

            primeAppController.getHabitat().removeAll();
            primeAppController.disableButtons(Main.primeAppController.getStopWatch().getStateOfTimer());
            window.close();
        });

        window.setOnCloseRequest(event -> {
            primeAppController.getStopWatch().start();
            Main.primeAppController.disableButtons(Main.primeAppController.getStopWatch().getStateOfTimer());
            window.close();
        });

        TextArea textArea = new TextArea(mesageTextArea);
        textArea.setPrefColumnCount(15);
        textArea.setPrefRowCount(5);
        textArea.setEditable(false);

        VBox root = new VBox();
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15,15, 10,10));
        hBox.setSpacing(120);
        hBox.getChildren().addAll(okButton,cancelButton);

        root.getChildren().addAll(textArea,hBox);

        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}
