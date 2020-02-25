package com.robot.poc.dto;

/*
@author Siddharth Gelda
 */
public interface Subject {
    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public String notifyObservers(Observer ob, String msg, boolean lb, boolean ow, boolean barcodereaderIndicator, Double price);
}
