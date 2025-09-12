package com.library.patterns.observer;

import java.util.List;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
    List<Observer> getObservers();
}