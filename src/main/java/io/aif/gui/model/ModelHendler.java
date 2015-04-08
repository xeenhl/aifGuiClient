package io.aif.gui.model;

import io.aif.gui.model.results.IResult;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.List;
import java.util.Set;

public class ModelHendler {

    private final static Model model = new Model();

    public static void addResult(IResult<?> result) {
        model.getResults().add(result);
    }

    public static List<IResult<?>> getResults() {
        return model.getResults();
    }

    public static ObservableList<File> getFilesList() {
        return model.getFilesList();
    }

    public static void addFileToList(File file) {
        model.getFilesList().add(file);
    }

    public static void setMainStage(Stage stage) {
        model.setMainStage(stage);
    }

    public static Stage getMainStage() {
        return model.getMainStage();
    }

}
