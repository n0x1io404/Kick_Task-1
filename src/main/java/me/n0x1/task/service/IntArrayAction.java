package me.n0x1.task.service;

import me.n0x1.task.entity.CustomEntityIntArray;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public interface IntArrayAction {
    OptionalInt min(CustomEntityIntArray intArray);

    OptionalInt max(CustomEntityIntArray intArray);

    OptionalInt sum(CustomEntityIntArray intArray);

    OptionalDouble average(CustomEntityIntArray intArray);
}
