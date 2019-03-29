package sample.Classes.Windows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WindowError extends Window  {
    private Button okButton = new Button("Ок");

    public WindowError(String message){
        super("Error!");
        initActtionsButtons();
        Scene scene = new Scene(createInterfaceWindow(message));
        window.setScene(scene);
        window.show();
    }

    private void initActtionsButtons(){
        okButton.setOnAction(event -> {
            window.close();
        });
    }
    private VBox createInterfaceWindow(String message){
        TextArea textArea = new TextArea(message);
        textArea.setPrefColumnCount(15);
        textArea.setPrefRowCount(5);
        textArea.setEditable(false);

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15,15, 10,10));
        hBox.setSpacing(120);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(okButton);

        vBox.getChildren().addAll(textArea,hBox);
        return vBox;
    }
}

