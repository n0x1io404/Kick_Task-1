package me.n0x1.task.service;

import me.n0x1.task.entity.CustomEntityIntArray;
import me.n0x1.task.exception.CustomArrayException;

public interface IntArraySort {
    void bubbleSort(CustomEntityIntArray entity) throws CustomArrayException;
    void smoothSort(CustomEntityIntArray entity) throws CustomArrayException;
}
