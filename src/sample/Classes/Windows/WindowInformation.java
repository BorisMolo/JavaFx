package sample.Classes.Windows;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import sample.AppController;




public class WindowInformation extends Window {
    private Button okButton = new Button("Ок");
    private Button cancelButton = new Button("Отмена");

    public WindowInformation(String titleOfWindow, String mesageTextArea, AppController primeAppController) {
        super(titleOfWindow);
        initActtionsButtons(primeAppController);
        Scene scene = new Scene(createInterfaceWindow(mesageTextArea));
        window.setScene(scene);
        window.show();
    }

    private void initActtionsButtons(AppController primeAppController){
        cancelButton.setOnAction(event -> {
            try {
                //primeAppController.setStateOfTimer(primeAppController.PAUSE);
                primeAppController.appStart();
                primeAppController.disableButtons(primeAppController.getStateOfTimer());
                window.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        okButton.setOnAction(event -> {
            try {
                primeAppController.setStateOfTimer(AppController.STOP);
                primeAppController.appStop();
                primeAppController.getHabitat().removeAll();
                primeAppController.disableButtons(primeAppController.getStateOfTimer());
                window.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        window.setOnCloseRequest(event -> {
            try {
                primeAppController.appStart();
                primeAppController.disableButtons(primeAppController.getStateOfTimer());
                window.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
    private VBox createInterfaceWindow(String mesageTextArea){
        TextArea textArea = new TextArea(mesageTextArea);
        textArea.setPrefColumnCount(15);
        textArea.setPrefRowCount(5);
        textArea.setEditable(false);

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15,15, 10,10));
        hBox.setSpacing(120);
        hBox.getChildren().addAll(okButton,cancelButton);

        vBox.getChildren().addAll(textArea,hBox);
        return vBox;
    }
}
