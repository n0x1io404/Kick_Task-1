package me.n0x1.task.observer;

public interface Observable {
    void attach(EntityObserver observer);
    void detach(EntityObserver observer);
    void notifyObservers();
}
