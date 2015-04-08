package io.aif.gui.helpers;


import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

/**
 * Created by olehkozlovskyi on 07.04.15.
 */
public class LoadHelper {

    private static ProgressIndicator indicator;
    private static Label label;

    public static void addElements(ProgressIndicator ind, Label l) {
        indicator = ind;
        label = l;
    }

    public static ProgressIndicator getIndicator() { return indicator; }

}
