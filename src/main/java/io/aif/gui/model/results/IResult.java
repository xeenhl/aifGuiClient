package io.aif.gui.model.results;

import io.aif.gui.model.ResultTab;

public interface IResult <T> {

    public T getResult();
    public ResultTab getResultId();
}
