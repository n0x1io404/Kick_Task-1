package me.n0x1.task.service;

import me.n0x1.task.entity.CustomEntityIntArray;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public interface IntArrayAction {

    OptionalInt findMin(int[] intArray);

    OptionalInt findMax(int[] intArray);

    OptionalLong calculateSum(int[] intArray);

    OptionalDouble calculateAverage(int[] intArray);
}