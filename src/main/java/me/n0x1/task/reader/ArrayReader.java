package me.n0x1.task.reader;

import me.n0x1.task.exception.CustomArrayException;

import java.util.List;

public interface ArrayReader {
    List<String> readLinesFromFile(String filePath) throws CustomArrayException;
}