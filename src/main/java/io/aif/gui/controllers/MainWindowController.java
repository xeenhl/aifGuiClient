package io.aif.gui.controllers;

import io.aif.gui.elements.ResultView;
import io.aif.gui.helpers.LoadHelper;
import io.aif.gui.helpers.aifLibHelper;
import io.aif.gui.model.File;
import io.aif.gui.model.ModelHendler;
import io.aif.gui.resources.ResourceHelper;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {

    private TabPane currentResult;
    private Stage st = new Stage();

    @FXML
    private Label info;

    @FXML
    private ListView filesList;

    @FXML
    private HBox resultPlace;



    {
        ModelHendler.getFilesList().addListener((ListChangeListener<File>) c -> {
            filesList.setItems(ModelHendler.getFilesList());
        });
    }

    public void aifItClicked() {


        showLoadPopUp();

        if(currentResult != null ) {
            resultPlace.getChildren().remove(currentResult);
            currentResult = null;
        }

        final File file = (File) filesList.getSelectionModel().getSelectedItems().get(0);

        info.setText("Ollolo button \"AIF it\" clicked for the file: " + file.getName());

        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                aifLibHelper aifLibHelper = new aifLibHelper(file);
                aifLibHelper.SSplit();
                updateProgress(0.1, 1.0);
                aifLibHelper.Ess();
                updateProgress(0.3, 1.0);
                aifLibHelper.DBuild();
                updateProgress(0.5, 1.0);
                aifLibHelper.TSplit();
                updateProgress(0.7, 1.0);
                aifLibHelper.Est();
                updateProgress(0.8, 1.0);
                aifLibHelper.SBuild();
                updateProgress(1.0, 1.0);

                return null;
            }
        };

        task.setOnSucceeded(event -> {
            currentResult = new ResultView().getPane();
            resultPlace.getChildren().add(currentResult);
            st.hide();
        });

        Thread t = new Thread(task);
        LoadHelper.getIndicator().progressProperty().bind(task.progressProperty());
        t.start();

    }

    public void openFile() {
        final File f = new File(new FileChooser().showOpenDialog(ModelHendler.getMainStage()).toURI());
        if(f != null)
            ModelHendler.addFileToList(f);
    }


    private void showLoadPopUp() {

        try {
            AnchorPane p = FXMLLoader.load(ResourceHelper.getResourceURL("loadingPopUp.fxml"));
            st.setScene(new Scene(p));
            st.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
