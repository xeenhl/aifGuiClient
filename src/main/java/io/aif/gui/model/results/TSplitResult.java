package io.aif.gui.model.results;

import io.aif.gui.model.ResultTab;

import java.util.List;

public class TSplitResult extends BasicResult<List<String>> {

    private final ResultTab resuldId = ResultTab.TSPLIT;

    public TSplitResult(List<String> strings) {
        super(strings);
    }

    @Override
    public ResultTab getResultId() {
        return resuldId;
    }
}
