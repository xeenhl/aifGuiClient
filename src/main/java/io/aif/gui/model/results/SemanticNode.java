package io.aif.gui.model.results;

import io.aif.language.semantic.ISemanticNode;
import io.aif.language.word.IWord;

public class SemanticNode {

    private final ISemanticNode<IWord> node;

    public SemanticNode(ISemanticNode<IWord> node) {
        this.node = node;
    }

//    public ISemanticNode<IWord> getNode() {
//        return node;
//    }

    public String getDetailes() {

        final String begin = "Root node: ";


        final StringBuilder sbuf = new StringBuilder();
        sbuf.append(begin);
        sbuf.append(node.item().getRootToken());
        sbuf.append(" , with weight: ");
        sbuf.append(node.weight());
        sbuf.append("\n");
        sbuf.append("Conected words:\n");

        node.connectedItems().stream().forEach(s -> {
            sbuf.append(s.item().getRootToken());
            sbuf.append(" , with weight: ");
            sbuf.append(s.weight());
            sbuf.append("\n");
        });

        return sbuf.toString();
    }

    @Override
    public String toString() {
        return node.item().getRootToken();
    }



}
