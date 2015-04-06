package io.aif.gui.controllers;

import io.aif.gui.helpers.aifLibHelper;
import io.aif.gui.elements.ResultView;
import io.aif.gui.model.File;
import io.aif.gui.model.ModelHendler;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class MainWindowController {

    private TabPane currentResult;

    @FXML
    private Label info;

    @FXML
    private ListView filesList;

    @FXML
    private HBox resultPlace;



    {
        ModelHendler.getFilesList().addListener(new ListChangeListener<File>() {
            @Override
            public void onChanged(Change<? extends File> c) {
                filesList.setItems(ModelHendler.getFilesList());
            }
        });
    }

    public void aifItClicked() {



        if(currentResult != null ) {
            resultPlace.getChildren().remove(currentResult);
            currentResult = null;
        }

        final File file = (File) filesList.getSelectionModel().getSelectedItems().get(0);

        info.setText("Ollolo button \"AIF it\" clicked for the file: " + file.getName());

        aifLibHelper aifLibHelper = new aifLibHelper(file);
        aifLibHelper.SSplit();
        aifLibHelper.Ess();
        aifLibHelper.DBuild();
        aifLibHelper.TSplit();
        aifLibHelper.Est();
        aifLibHelper.SBuild();

        currentResult = new ResultView().getPane();

        resultPlace.getChildren().add(currentResult);

    }

    public void openFile() {
        final File f = new File(new FileChooser().showOpenDialog(ModelHendler.getMainStage()).toURI());
        ModelHendler.addFileToList(f);
    }


}
