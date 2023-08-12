package com.example.modelviewcontroller.model;

import com.example.modelviewcontroller.view.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    private final List<Observer> observers = new ArrayList<>();

    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update();
        }
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}
