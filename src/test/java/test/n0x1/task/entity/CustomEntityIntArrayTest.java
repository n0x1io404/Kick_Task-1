package test.n0x1.task.entity;

import me.n0x1.task.entity.CustomEntityIntArray;
import me.n0x1.task.exception.CustomArrayException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

class CustomEntityIntArrayTest {

    @Test
    void constructor_ValidSize_CreatesArray() throws CustomArrayException {
        CustomEntityIntArray entity = new CustomEntityIntArray(5);

        int expected = 5;
        int actual = entity.getLength();

        assertEquals(actual, expected);
    }

    @Test
    void constructor_InvalidSize_ThrowsException() {
        assertThrows(CustomArrayException.class, () -> new CustomEntityIntArray(0));
        assertThrows(CustomArrayException.class, () -> new CustomEntityIntArray(-5));
    }

    @Test
    void constructor_VarArgs_CreatesArray() throws CustomArrayException {
        CustomEntityIntArray entity = new CustomEntityIntArray(1, 2, 3);

        int expectedSize = 3;
        int actualSize = entity.getLength();
        assertEquals(actualSize, expectedSize);

        int expectedElement = 2;
        int actualElement = entity.getElement(1);
        assertEquals(actualElement, expectedElement);
    }

    @Test
    void constructor_NullVarArgs_ThrowsException() {
        int[] nullArray = null;
        assertThrows(CustomArrayException.class, () -> new CustomEntityIntArray(nullArray));
    }

    @Test
    void constructor_CloneEntity_CreatesDeepCopy() throws CustomArrayException {
        CustomEntityIntArray expected = new CustomEntityIntArray(1, 2, 3);
        CustomEntityIntArray actual = new CustomEntityIntArray(expected);

        assertEquals(expected, actual);
        assertNotSame(expected.getData(), actual.getData());
    }

    @Test
    void setElement_ValidIndex_UpdatesValue() throws CustomArrayException {
        CustomEntityIntArray entity = new CustomEntityIntArray(3);
        entity.setElement(1, 42);

        int expected = 42;
        int actual = entity.getElement(1);

        assertEquals(actual, expected);
    }

    @Test
    void setElement_OutOfBounds_ThrowsException() throws CustomArrayException {
        CustomEntityIntArray entity = new CustomEntityIntArray(3);
        assertThrows(CustomArrayException.class, () -> entity.setElement(5, 42));
        assertThrows(CustomArrayException.class, () -> entity.setElement(-1, 42));
    }
}