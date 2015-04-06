package io.aif.gui.elements;

import io.aif.gui.resources.ResourceHelper;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class ResultView {

    private TabPane pane;

    public ResultView() {
        try {
            pane = FXMLLoader.load(ResourceHelper.getResourceURL("ResultPane.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TabPane getPane() {
        return pane;
    }
}
