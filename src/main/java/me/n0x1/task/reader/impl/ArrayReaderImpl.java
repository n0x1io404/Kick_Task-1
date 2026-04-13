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
        if (filePath == null || filePath.trim().isEmpty()) {
            logger.log(Level.ERROR, "Provided file path is null or empty");
            throw new CustomArrayException("File path cannot be null or empty");
        }

        Path path = Paths.get(filePath);
        if (!Files.exists(path) || Files.isDirectory(path)) {
            logger.log(Level.ERROR, "File does not exist or is a directory: {}", filePath);
            throw new CustomArrayException("Invalid file: " + filePath);
        }

        try (Stream<String> lines = Files.lines(path)) {
            List<String> result = lines
                    .filter(line -> !line.trim().isEmpty())
                    .collect(Collectors.toList());

            logger.log(Level.INFO, "Successfully read {} non-empty lines from file: {}", result.size(), filePath);
            return result;
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error reading file: {}", filePath, e);
            throw new CustomArrayException("Error reading file: " + e.getMessage());
        }
    }
}