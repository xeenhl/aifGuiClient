package io.aif.gui.model;

import io.aif.gui.model.results.IResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class Model {

    private final CopyOnWriteArrayList<IResult<?>> results = new CopyOnWriteArrayList<>();

    private final ObservableList<File> files = FXCollections.observableArrayList();

    private Stage mainStage = null;

    public List<IResult<?>> getResults() {
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
