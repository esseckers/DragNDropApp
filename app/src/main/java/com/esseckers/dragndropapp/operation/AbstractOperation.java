package com.esseckers.dragndropapp.operation;


import com.redmadrobot.chronos.ChronosOperation;

public abstract class AbstractOperation<T> extends ChronosOperation<T> {

    @Override
    public T run() {
        return executeRoutine();
    }

    protected T executeRoutine() {
        return null;
    }
}
