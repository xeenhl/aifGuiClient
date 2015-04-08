package io.aif.gui;

import io.aif.gui.model.ModelHendler;
import io.aif.gui.resources.ResourceHelper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(ResourceHelper.getResourceURL("mainWindow.fxml"));
        ModelHendler.setMainStage(primaryStage);
        primaryStage.setTitle("AIF Client");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
