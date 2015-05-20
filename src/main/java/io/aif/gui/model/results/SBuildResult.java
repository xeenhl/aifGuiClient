package io.aif.gui.model.results;

import io.aif.gui.model.ResultTab;

public class SBuildResult extends BasicResult</*List<ISemanticNode<IWord>>*/String> {

    private final ResultTab resultId = ResultTab.SBUILD;

    public SBuildResult(String iSemanticNodes) {
        super(iSemanticNodes);
    }

    @Override
    public ResultTab getResultId() {
        return resultId;
    }
}
