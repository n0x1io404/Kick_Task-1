package me.n0x1.task.factory.impl;

import me.n0x1.task.entity.CustomEntityIntArray;
import me.n0x1.task.exception.CustomArrayException;
import me.n0x1.task.factory.ArrayFactory;
import me.n0x1.task.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayFactoryImpl implements ArrayFactory {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CustomEntityIntArray createArray(int size) throws CustomArrayException {
        if (size <= 0) { throw new CustomArrayException("Invalid size"); }
        var id = IdGenerator.generateId();

        return new CustomEntityIntArray(id, size);
    }

    @Override
    public CustomEntityIntArray createArray(int... incomingData) throws CustomArrayException {
        if (incomingData == null) { throw new CustomArrayException("Array reference is null"); }
        if (incomingData.length == 0) { throw new CustomArrayException("Array is empty (length 0)"); }
        var id = IdGenerator.generateId();

        return new CustomEntityIntArray(id, incomingData);
    }

    @Override
    public CustomEntityIntArray createArray(CustomEntityIntArray entityArray) throws CustomArrayException {
        if (entityArray == null) { throw new CustomArrayException("Entity array reference is null"); }
        var id = IdGenerator.generateId();

        return new CustomEntityIntArray(id, entityArray);
    }
}
