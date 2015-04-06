package io.aif.gui.model.results;

import io.aif.gui.model.ResultTab;

import java.util.Map;

public class ESSResult extends BasicResult<Map<String, String>> {

    private final ResultTab resultId = ResultTab.ESS;

    public ESSResult(Map<String, String> stringStringMap) {
        super(stringStringMap);
    }

    @Override
    public ResultTab getResultId() {
        return resultId;
    }
}
