package me.n0x1.task.service.impl;

import me.n0x1.task.entity.CustomEntityIntArray;
import me.n0x1.task.service.IntArrayAction;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class IntArrayActionImpl implements IntArrayAction {

    @Override
    public OptionalInt min(CustomEntityIntArray intArray) {
        return Arrays.stream(intArray.getData()).min();
    }

    @Override
    public OptionalInt max(CustomEntityIntArray intArray) {
        return Arrays.stream(intArray.getData()).max();
    }

    @Override
    public OptionalInt sum(CustomEntityIntArray intArray) {
        return Arrays.stream(intArray.getData()).reduce(Integer::sum) ;
    }

    @Override
    public OptionalDouble average(CustomEntityIntArray intArray) {
        return Arrays.stream(intArray.getData()).average();
    }
}