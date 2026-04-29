package me.n0x1.task.reader.impl;

import me.n0x1.task.exception.CustomArrayException;
import me.n0x1.task.reader.ArrayReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayReaderImpl implements ArrayReader {

    private static final Logger logger = LogManager.getLogger("LogFile");

    @Override
    public List<String> readLinesFromFile(String filePath) throws CustomArrayException {
        if (filePath == null || filePath.isBlank()) {
            throw new CustomArrayException("File path cannot be null or empty");
        }

        Path path = Paths.get(filePath);
        if (!Files.exists(path) || Files.isDirectory(path)) {
            throw new CustomArrayException("Invalid file: " + filePath);
        }

        try (Stream<String> lines = Files.lines(path)) {
            List<String> result = lines
                    .filter(line -> !line.isBlank())
                    .collect(Collectors.toList());

            return result;
        } catch (IOException e) {
            throw new CustomArrayException("Error reading file: " + e.getMessage());
        }
    }
}