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

    public static Controller controller;
    public static AppController primeAppController;


    @Override
    public void start(Stage primaryStage) throws Exception{
        // load FXML file and set it to root
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("FirstLab");
        Scene mainScene = new Scene(root, 600, 600);
        primaryStage.setScene(mainScene);
        primaryStage.show();

        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                try {
                    writeKeyCode(event.getCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        // get controller
        controller = (Controller)loader.getController();
        primeAppController = new AppController();
    }

    private void writeKeyCode(KeyCode key) throws Exception {
        switch (key) {
            // Время симуляции должно отображаться текстом в области визуализации
            // и скрываться/показываться по клавише T
            case T: {
                System.out.print("Typed the key: T; ");
                if(primeAppController.getShowLog() == false)
                {
                    System.out.println("Logs has been activated");
                    primeAppController.setShowLog(true);
                    controller.getFieldTime().setVisible(true);
                }
                else
                {
                    System.out.println("Logs has been diactivated");
                    primeAppController.setShowLog(false);
                    controller.getFieldTime().setVisible(false);
                }
            }break;
            //Симуляция должна запускаться по клавише B
            case B: {
                System.out.println("Typed the key: B; ");
                primeAppController.appStart();
            }break;
            //останавливаться по клавише E
            case E: {
                System.out.println("Typed the key: E; ");
                primeAppController.appStop();
            }break;
        }

    }


}

