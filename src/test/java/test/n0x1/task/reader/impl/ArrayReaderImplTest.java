package test.n0x1.task.reader.impl;

import me.n0x1.task.exception.CustomArrayException;
import me.n0x1.task.reader.ArrayReader;
import me.n0x1.task.reader.impl.ArrayReaderImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class ArrayReaderImplTest {

    private ArrayReader arrayReader;

    @BeforeMethod
    public void setUp() {
        arrayReader = new ArrayReaderImpl();
    }

    @Test
    public void testReadLinesFromFileSuccess() throws IOException, CustomArrayException {
        Path tempFile = Files.createTempFile("testArrayData", ".txt");
        String content = "1 2 3\n   \n4 5 6\n\n7 8 9";
        Files.writeString(tempFile, content);

        List<String> expected = Arrays.asList("1 2 3", "4 5 6", "7 8 9");
        List<String> actual = arrayReader.readLinesFromFile(tempFile.toString());

        Assert.assertEquals(actual, expected);
        Files.deleteIfExists(tempFile);
    }

    @DataProvider(name = "invalidPathsData")
    public Object[][] invalidPathsData() {
        return new Object[][]{
                {null, "File path cannot be null or empty"},
                {"", "File path cannot be null or empty"},
                {"   ", "File path cannot be null or empty"},
                {"non_existent_file_12345.txt", "Invalid file: non_existent_file_12345.txt"}
        };
    }

    @Test(dataProvider = "invalidPathsData")
    public void testReadLinesFromFileInvalidPaths(String filePath, String expectedMessage) {
        // ИСПОЛЬЗУЕМ expectThrows ВМЕСТО assertThrows
        Exception exception = Assert.expectThrows(CustomArrayException.class, () -> {
            arrayReader.readLinesFromFile(filePath);
        });
        String actualMessage = exception.getMessage();
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testReadLinesFromFileIsDirectory() throws IOException {
        Path tempDir = Files.createTempDirectory("testDirectory");
        String expectedMessage = "Invalid file: " + tempDir.toString();

        // ИСПОЛЬЗУЕМ expectThrows ВМЕСТО assertThrows
        Exception exception = Assert.expectThrows(CustomArrayException.class, () -> {
            arrayReader.readLinesFromFile(tempDir.toString());
        });
        String actualMessage = exception.getMessage();
        Assert.assertEquals(actualMessage, expectedMessage);
        Files.deleteIfExists(tempDir);
    }
}