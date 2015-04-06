package io.aif.gui.model.results;

import io.aif.gui.model.ResultTab;

import java.util.List;
import java.util.Map;

public class DBuildResult extends BasicResult<Map<String, List<String>>> {

    private ResultTab resultId = ResultTab.DBUILD;

    public DBuildResult(Map<String, List<String>> stringListMap) {
        super(stringListMap);
    }

    @Override
    public ResultTab getResultId() {
        return resultId;
    }
}
