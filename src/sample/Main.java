package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;



public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    public static AppController primeAppController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // load
        // FXML file and set it to root
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = mainLoader.load();
        primaryStage.setTitle("FirstLab");

        primeAppController = new AppController(mainLoader);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

