package me.n0x1.task.entity;

import me.n0x1.task.exception.CustomArrayException;
import me.n0x1.task.observer.EntityObserver;
import me.n0x1.task.observer.Observable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CustomEntityIntArray implements Observable {

    private static final Logger logger = LogManager.getLogger();

    private final long id;
    private int[] data;

    private final List<EntityObserver> observers = new ArrayList<>();

    public CustomEntityIntArray(long id, int size) {
        this.data = new int[size];
        this.id = id;
        logger.log(Level.INFO,"Entity of CustomEntityIntArray was created by size ({}).", size);
    }

    public CustomEntityIntArray(long id, int... incomingData) {
        this.data = incomingData.clone();
        this.id = id;
        logger.log(Level.INFO,"Entity of CustomEntityIntArray was created by incoming int-array.");
    }

    public CustomEntityIntArray(long id, CustomEntityIntArray entityArray) {
        this.data = entityArray.data.clone();
        this.id = id;
        logger.log(Level.INFO,"Entity of CustomEntityIntArray was created by cloning another one.");
    }

    public int getLength () {
        return data.length;
    }

    public int[] getData() {
        return data.clone();
    }

    public long getId() {
        return id;
    }

    public int getElement(int index) throws CustomArrayException {
        if (index < 0 || index >= data.length) {
            throw new CustomArrayException(String.format("Index %d is out of bounds for length %d", index, data.length));
        }
        return this.data[index];
    }

    public void setElement(int index, int value) throws CustomArrayException  {
        if (index < 0 || index >= data.length) {
            throw new CustomArrayException(String.format("Index %d is out of bounds for length %d", index, data.length));
        }
        this.data[index] = value;
        notifyObservers();
    }

    public void setElements(int[] data) throws CustomArrayException  {
        if (data == null) {
            throw new CustomArrayException("Null pointer data");
        }
        this.data = data.clone();
        notifyObservers();
    }

    @Override
    public void attach(EntityObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void detach(EntityObserver observer) {
        if (observer != null) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (EntityObserver observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CustomArray{");
        sb.append("id=").append(id);
        sb.append(", data=").append(Arrays.toString(data));
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id) + Arrays.hashCode(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomEntityIntArray that = (CustomEntityIntArray) o;
        return id == that.id && Arrays.equals(data, that.data);
    }
}