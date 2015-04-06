package io.aif.gui.model.results;

import io.aif.gui.model.ResultTab;

public class SSplitResult extends BasicResult<String> {

    private final ResultTab resultId = ResultTab.SSPLIT;


    public SSplitResult(String result) {
        super(result);
    }


    @Override
    public ResultTab getResultId() {
        return resultId;
    }
}
