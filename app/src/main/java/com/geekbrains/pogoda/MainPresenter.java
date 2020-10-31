package com.geekbrains.pogoda;


//Singleton для каких либо данных

public class MainPresenter {

    private static MainPresenter instance;

    private int counter;

    public synchronized void incrementCounter(){
        counter++;
    }

    public synchronized int getCounter() {
        return counter;
    }

    private MainPresenter(){
        counter = 0;
    }

    public static MainPresenter getInstance() {
        synchronized (MainPresenter.class) {
            if (instance == null) {
                instance = new MainPresenter();
            }
            return instance;
        }
    }
}
