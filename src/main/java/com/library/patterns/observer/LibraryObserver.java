package com.library.patterns.observer;

public class LibraryObserver implements Observer {
    private String observerName;

    public LibraryObserver(String name) {
        this.observerName = name;
    }

    @Override
    public void update(String message) {
        System.out.println(observerName + " received notification: " + message);
    }

    public String getObserverName() {
        return observerName;
    }
}