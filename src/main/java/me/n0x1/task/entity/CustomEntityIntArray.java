package me.n0x1.task.entity;

import me.n0x1.task.exception.CustomArrayException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;

public class CustomEntityIntArray {
    private int[] data;
    private static final Logger logger = LogManager.getLogger("LogFile");

    public CustomEntityIntArray(int size) throws CustomArrayException{
        if (size <= 0) { throw new CustomArrayException("Invalid size"); }

        this.data = new int[size];

        logger.log(Level.INFO,"Entity of CustomEntityIntArray was created by size ({}).", size);
    }

    public CustomEntityIntArray(int... incomingData) throws CustomArrayException{
        if (incomingData == null) { throw new CustomArrayException("Array reference is null"); }
        if (incomingData.length == 0) { throw new CustomArrayException("Array is empty (length 0)"); }

        this.data = incomingData.clone();
        logger.log(Level.INFO,"Entity of CustomEntityIntArray was created by incoming int-array.");
    }

    public CustomEntityIntArray(CustomEntityIntArray entityArray) throws CustomArrayException{
        if (entityArray == null) { throw new CustomArrayException("Entity array reference is null"); }

        this.data = entityArray.data.clone();
        logger.log(Level.INFO,"Entity of CustomEntityIntArray was created by cloning another one.");
    }

    public int getLength () {
        return data.length;
    }

    @Override
    public String toString() {
        return "CustomArray: " + Arrays.toString(data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        CustomEntityIntArray that = (CustomEntityIntArray) o;
        return Arrays.equals(data, that.data);
    }

    public int[] getData() {
        return data.clone();
    }

    public int getElement(int index) throws CustomArrayException {
        if (index < 0) { throw new CustomArrayException("Index cannot be negative: " + index); }
        if (index >= data.length) { throw new CustomArrayException("Index " + index + " is out of bounds for length " + data.length); }

        return this.data[index];
    }

    public void setElement(int index, int value) throws CustomArrayException  {
        if (index < 0) { throw new CustomArrayException("Index cannot be negative: " + index); }
        if (index >= data.length) { throw new CustomArrayException("Index " + index + " is out of bounds for length " + data.length); }

        this.data[index] = value;
    }

    public void setElements(int[] data) throws CustomArrayException  {
        if (data == null) { throw new CustomArrayException("Null pointer data"); }

        this.data = data.clone();
    }
}
