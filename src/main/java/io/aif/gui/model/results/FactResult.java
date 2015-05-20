package io.aif.gui.model.results;

import io.aif.gui.model.ResultTab;

import java.util.List;

/**
 * Created by olehkozlovskyi on 20.05.15.
 */
public class FactResult extends BasicResult<String> {

    private final ResultTab resultId = ResultTab.FACT;

    public FactResult(String s) {
        super(s);
    }

    @Override
    public ResultTab getResultId() {
        return resultId;
    }
}
