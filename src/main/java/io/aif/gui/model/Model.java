package io.aif.gui.model;

import io.aif.gui.model.results.IResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class Model {

    private final Set<IResult<?>> results = new HashSet<>();

    private final ObservableList<File> files = FXCollections.observableArrayList();

    private Stage mainStage = null;

    public Set<IResult<?>> getResultsMap() {
        return results;
    }

    public ObservableList<File> getFilesList() {
        return files;
    }

    public void setMainStage(Stage stage) {
        if(mainStage == null)
            mainStage = stage;
    }

    public Stage getMainStage() {
        return mainStage;
    }

}
