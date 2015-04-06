package io.aif.gui.model.results;

import io.aif.gui.model.ResultTab;
import io.aif.language.semantic.ISemanticNode;
import io.aif.language.word.IWord;

import java.util.List;

public class SBuildResult extends BasicResult<List<ISemanticNode<IWord>>> {

    private final ResultTab resultId = ResultTab.SBUILD;

    public SBuildResult(List<ISemanticNode<IWord>> iSemanticNodes) {
        super(iSemanticNodes);
    }

    @Override
    public ResultTab getResultId() {
        return resultId;
    }
}
