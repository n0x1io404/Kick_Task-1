package me.n0x1.task.service.impl;

import me.n0x1.task.entity.CustomEntityIntArray;
import me.n0x1.task.service.IntArrayAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class IntArrayActionImpl implements IntArrayAction {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public OptionalInt findMin(int[] intArray) {
        if (intArray == null || intArray.length == 0) {
            logger.warn("Provided array entity is null or empty");
            return OptionalInt.empty();
        }
        return Arrays.stream(intArray).min();
    }

    @Override
    public OptionalInt findMax(int[] intArray) {
        if (intArray == null || intArray.length == 0) {
            return OptionalInt.empty();
        }
        return Arrays.stream(intArray).max();
    }

    public OptionalLong calculateSum(int[]  intArray) {
        if (intArray == null || intArray.length == 0) {
            return OptionalLong.empty();
        }
        return OptionalLong.of(Arrays.stream(intArray).asLongStream().sum());
    }

    @Override
    public OptionalDouble calculateAverage(int[]  intArray) {
        if (intArray == null || intArray.length == 0) {
            return OptionalDouble.empty();
        }
        return Arrays.stream(intArray).average();
    }
}