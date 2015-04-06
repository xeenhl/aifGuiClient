package io.aif.gui.model.results;

import io.aif.gui.model.ResultTab;

import java.util.List;

public class ESTResult extends BasicResult<List<Character>> {

    private final ResultTab resultId = ResultTab.EST;

    public ESTResult(List<Character> characters) {
        super(characters);
    }

    @Override
    public ResultTab getResultId() {
        return resultId;
    }
}
