package sample.Classes.Windows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Main;

public class WindowError extends Window  {
    public WindowError(String titleOfWindow, String message){
        super(titleOfWindow);
        Button okButton = new Button("Ок");

        okButton.setOnAction(event -> {
            window.close();
        });

        TextArea textArea = new TextArea(message);
        textArea.setPrefColumnCount(15);
        textArea.setPrefRowCount(5);
        textArea.setEditable(false);

        VBox root = new VBox();
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15,15, 10,10));
        hBox.setSpacing(120);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(okButton);

        root.getChildren().addAll(textArea,hBox);

        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}

