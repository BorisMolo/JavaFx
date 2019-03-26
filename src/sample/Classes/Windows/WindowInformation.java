package sample.Classes.Windows;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import sample.AppManager;




public class WindowInformation extends Window {
    private Button okButton = new Button("Ок");
    private Button cancelButton = new Button("Отмена");

    public WindowInformation(String titleOfWindow, String mesageTextArea, AppManager appManager) {
        super(titleOfWindow);
        initActtionsButtons(appManager);
        Scene scene = new Scene(createInterfaceWindow(mesageTextArea));
        window.setScene(scene);
        window.show();
    }

    private void initActtionsButtons(AppManager appManager){
        cancelButton.setOnAction(event -> {
            try {
                //primeAppController.setStateOfTimer(primeAppController.PAUSE);
                appManager.appStart();
                appManager.disableButtons(appManager.getStateOfTimer());
                window.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        okButton.setOnAction(event -> {
            try {
                appManager.setStateOfTimer(appManager.STOP);
                appManager.disableButtons(appManager.STOP);
                appManager.removeAllHabitat();
                window.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        window.setOnCloseRequest(event -> {
            try {
                appManager.appStart();
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
