package io.aif.gui.controllers;

import io.aif.gui.model.ModelHendler;
import io.aif.gui.model.ResultTab;
import io.aif.gui.model.results.IResult;
import io.aif.gui.model.results.SemanticNode;
import io.aif.language.semantic.ISemanticNode;
import io.aif.language.word.IWord;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ResultPaneController implements Initializable{


    @FXML
    private TabPane tabs;

    @FXML
    private Tab ssplit;
    @FXML
    private Tab ess;
    @FXML
    private Tab dbuild;
    @FXML
    private Tab tsplit;
    @FXML
    private Tab est;
    @FXML
    private Tab sbuild;

    //SSPLIT tab controls elements
    @FXML
    private TextArea ssplitResult;

    //ESS tab controls elements
    @FXML
    private ListView essGroup;
    @FXML
    private TextArea essCharacters;

    //DBUILD tab controls
    @FXML
    private ListView dbuildBasicTokens;
    @FXML
    private TextArea dbuildTokens;

    //TSPLIT controls
    @FXML
    private TextArea tsplitTokens;

    //EST tab controls
    @FXML
    private TextArea estTokenSeparators;

    //SBUILD tab controls
    @FXML
    private ListView sbuildNodes;
    @FXML
    private TextArea sbuildDetailes;



    private final Map<ResultTab, Tab> tabsList = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<IResult<?>> results = ModelHendler.getResults();



        tabsList.put(ResultTab.SSPLIT, ssplit);
        tabsList.put(ResultTab.ESS, ess);
        tabsList.put(ResultTab.DBUILD, dbuild);
        tabsList.put(ResultTab.TSPLIT, tsplit);
        tabsList.put(ResultTab.EST, est);
        tabsList.put(ResultTab.SBUILD, sbuild);

        results.stream().forEach(this::initializeResults);

        tabsList.entrySet().stream().forEach(s -> tabs.getTabs().remove(s.getValue()));

    }

    private void initializeResults(IResult<?> result) {

        switch(result.getResultId()) {
            case SSPLIT:
                initSSptitTab(result);
                tabsList.remove(ResultTab.SSPLIT);
                break;
            case ESS:
                initESSTab(result);
                tabsList.remove(ResultTab.ESS);
                break;
            case DBUILD:
                initDBUILDTab(result);
                tabsList.remove(ResultTab.DBUILD);
                break;
            case TSPLIT:
                initTSPLITTab(result);
                tabsList.remove(ResultTab.TSPLIT);
                break;
            case EST:
                initESTTab(result);
                tabsList.remove(ResultTab.EST);
                break;
            case SBUILD:
                initSBUILDTab(result);
                tabsList.remove(ResultTab.SBUILD);
                break;
            default:
                break;

        }

    }

    private void initSSptitTab(IResult<?> resutl) {

        ssplitResult.setText((String)resutl.getResult());

    }

    private void initESSTab(IResult<?> result) {

        Map<String, String> results = (Map<String, String>)result.getResult();

        final ObservableList<String> groups = FXCollections.observableArrayList();
        groups.addListener((ListChangeListener<String>) c -> {
            essGroup.setItems(groups);

        });

        essGroup.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            essCharacters.setText(results.get(newValue));
        });

        results.keySet().stream().forEach(groups::add);

    }

    private void initDBUILDTab(IResult<?> result) {

        final Map<String, List<String>> results = (Map<String, List<String>>) result.getResult();

        final ObservableList<String> basicTokens = FXCollections.observableArrayList();
        basicTokens.addListener((ListChangeListener<String>) c -> {
            dbuildBasicTokens.setItems(basicTokens);
        });

        dbuildBasicTokens.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            List<String> tokens = results.get(newValue);
            StringBuffer sbuf = new StringBuffer();

            tokens.stream().forEach(s -> sbuf.append(s+"\n"));

            dbuildTokens.setText(sbuf.toString());
        });

        results.keySet().stream().forEach(basicTokens::add);


    }

    private void initTSPLITTab(IResult<?> result) {
        final List<String> results = (List<String>) result.getResult();
        tsplitTokens.setText( results.stream().collect(Collectors.joining("\n")));
    }

    private void initESTTab(IResult<?> result) {
        final List<Character> results = (List<Character>) result.getResult();
        final List<String> separators = new ArrayList<>();
        results.stream().forEach(s -> {
            separators.add("\"" + s.toString() + "\"");
        });
        estTokenSeparators.setText(separators.stream().collect(Collectors.joining("\n")));
    }

    private void initSBUILDTab(IResult<?> result) {

        List<ISemanticNode<IWord>> results = (List<ISemanticNode<IWord>>) result.getResult();

        ObservableList<SemanticNode> nodes  = FXCollections.observableArrayList();

        nodes.addListener((ListChangeListener<SemanticNode>) c -> {
            sbuildNodes.setItems(nodes);
        });

        sbuildNodes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            sbuildDetailes.setText(((SemanticNode)newValue).getDetailes());
        });

        results.stream().forEach(s -> nodes.add(new SemanticNode(s)));


    }

}
