package me.n0x1.task.factory;

import me.n0x1.task.entity.CustomEntityIntArray;
import me.n0x1.task.exception.CustomArrayException;

public interface ArrayFactory {
    CustomEntityIntArray createArray(int size) throws CustomArrayException;

    CustomEntityIntArray createArray(int... incomingData) throws CustomArrayException;

    CustomEntityIntArray createArray(CustomEntityIntArray entityArray) throws CustomArrayException;
}