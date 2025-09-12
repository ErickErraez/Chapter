package com.library.patterns.chainofresponsibility;

public abstract class ValidationHandler {
    protected ValidationHandler nextHandler;

    public void setNextHandler(ValidationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(com.library.model.Book book);
}