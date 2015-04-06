package io.aif.gui.model.results;

public abstract class BasicResult <T> implements IResult<T>{

    private final T t;

    public BasicResult(T t) {
        this.t = t;
    }

    public T getResult() {
        return t;
    }

}
