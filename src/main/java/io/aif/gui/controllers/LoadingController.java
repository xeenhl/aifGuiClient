package io.aif.gui.controllers;

import io.aif.gui.helpers.LoadHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by olehkozlovskyi on 07.04.15.
 */
public class LoadingController implements Initializable{

    @FXML
    private ProgressIndicator loadingProgress;

    @FXML
    private Label lodingInfoText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadingProgress.setProgress(0.0);
        lodingInfoText.setText("Aif is parcing text, \n please wait");
        LoadHelper.addElements(loadingProgress, lodingInfoText);
    }
}
