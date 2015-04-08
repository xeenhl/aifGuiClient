package io.aif.gui.elements;

import io.aif.gui.resources.ResourceHelper;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by olehkozlovskyi on 07.04.15.
 */
public class LoadBar {

    private AnchorPane pane;

    public LoadBar() {

        try {
            pane = FXMLLoader.load(ResourceHelper.getResourceURL("loadingPopUp.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public AnchorPane getPane() { return pane; }
}
